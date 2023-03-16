package cz.upce.fei.cv01.service;

import cz.upce.fei.cv01.config.WebSecurityConfig;
import cz.upce.fei.cv01.domain.AppUser;
import cz.upce.fei.cv01.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

//neni potreba konstruktor, vytvori construktor injection
@AllArgsConstructor
@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private final WebSecurityConfig webSecurityConfig;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    public AppUser findById(final Long id) throws ResourceNotFoundException {
       return appUserRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
    }

    public AppUser create(AppUser entity) {
        entity.setPassword(webSecurityConfig.passwordEncoder().encode(entity.getPassword()));
        return appUserRepository.save(entity);

    }

    @Transactional
    public AppUser update( AppUser toEntity) {
        if (appUserRepository.existsByUsernameAndIdIsNot(toEntity.getUsername(), toEntity.getId())) {
            throw new IllegalArgumentException("The username already exists.");
        }
        AppUser user = appUserRepository.findById(toEntity.getId()).orElseThrow(() -> new NoSuchElementException("User not found!"));
        AppUser save = appUserRepository.save(toEntity);
        return save;

    }

    public void remove(Long userId) {
        AppUser appUser = appUserRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found!"));
        appUserRepository.deleteById(userId);
    }
/*
    public AppUser removeUser(final Long id){
        AppUser appUser = appUserRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found!"));
        appUserRepository.deleteById(userId);
        return ResponseEntity.ok(toDto(appUser));    }
    public AppUser addUser(final Long id){
        return appUserRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found!"));
    }*/
}
