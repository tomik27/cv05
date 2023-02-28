package cz.upce.fei.cv01.Controller;

import cz.upce.fei.cv01.domain.AppUser;
import cz.upce.fei.cv01.repository.AppUserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/app-user")
public class AppUserController {
    private final AppUserRepository appUserRepository;

    public AppUserController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @GetMapping("")
    public List<AppUser> findAll(){
        return appUserRepository.findByActiveEquals(true);
    }

}
