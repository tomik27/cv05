package cz.upce.fei.cv01.service;

import cz.upce.fei.cv01.domain.AppUser;
import cz.upce.fei.cv01.repository.AppUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
    @SpringBootTest
class AppUserServiceTest {
    //vezme z dependencz injection, pouzivalo sedriv, kdyz jeste nbylo construction injection. Stale se pouziva v testech
    //nepouzivat nap rodukcni kod v novejsich projektech
    @Autowired
    private AppUserService appUserService;
        //spirng za nas do dependencz inj vlozi namockovanou instanci
    @Autowired
    private AppUserRepository appUserRepository;


    @BeforeEach
    void setUp() {
     //   appUserRepository.save(Example.EXISTING);
    }

    @Test
    @Transactional
    void findById() throws ResourceNotFoundException {
        var expected =appUserRepository.save( Example.EXISTING);
        var actual=appUserService.findById(expected.getId());

        assertEquals(expected,actual);
    }

    @AfterEach
    void tearDown() {
        appUserRepository.deleteAll();
    }
}