package cz.upce.fei.cv01.Controller;

import cz.upce.fei.cv01.config.JwtRequest;
import cz.upce.fei.cv01.config.JwtResponse;
import cz.upce.fei.cv01.config.JwtTokenUtil;
import cz.upce.fei.cv01.domain.AppUser;
import cz.upce.fei.cv01.dto.AppUserDto;
import cz.upce.fei.cv01.dto.AppUserInputDto;
import cz.upce.fei.cv01.repository.AppUserRepository;
import cz.upce.fei.cv01.service.AppUserService;
import cz.upce.fei.cv01.service.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@RestController
@RequestMapping("/app-user")
@AllArgsConstructor
public class AppUserController {
    private final AppUserService appUserService;
    private final UserDetailsService jwtInMemoryUserDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final AppUserRepository appUserRepository;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable final Long userId) throws ResourceNotFoundException {
        return ResponseEntity.ok(toDto(appUserService.finById(userId)));
    }

    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody @Validated AppUserInputDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(appUserService.create(toEntity(userDto))));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId,@RequestBody AppUserInputDto userDto) {
        return ResponseEntity.ok(toDto(appUserService.update(toEntity(userId,userDto))));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> removeUser(@PathVariable Long userId) {
        appUserService.remove(userId);
        return ResponseEntity.ok().build();
    }


    private static AppUser toEntity(final AppUserInputDto inputDto){
        return new AppUser(inputDto.getUsername(),inputDto.getPassword(),inputDto.getActive(),inputDto.getCreationDate(),inputDto.getUpdateDate());
    }
    private static AppUser toEntity(Long id,final AppUserInputDto inputDto){
        return new AppUser(id,inputDto.getUsername(),inputDto.getPassword(),inputDto.getActive(),inputDto.getCreationDate(),inputDto.getUpdateDate());
    }

private static AppUserDto toDto(final AppUser appUser){
    return new AppUserDto(appUser.getId(),appUser.getUsername(),appUser.getPassword(),appUser.getActive(),appUser.getCreationDate(),appUser.getUpdateDate());
}

    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
            throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = jwtInMemoryUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final AppUser user = appUserRepository.findByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername(), user.getId(), userDetails.getAuthorities()));
    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}

