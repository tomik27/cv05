package cz.upce.fei.cv01.repository;

import cz.upce.fei.cv01.domain.AppUser;
import cz.upce.fei.cv01.domain.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserRepository extends PagingAndSortingRepository<AppUser,Long> {
    List<AppUser> findByActiveEquals(boolean active);

    @Query("select au from AppUser au where au.id=?1")
    AppUser findByIdEqualOne(int id);

    @Query("select ap from AppUser ap inner join ap.roles r where r = :role ")
    List<AppUser> findAllByRolesContaining(final Role role);

    boolean existsByUsernameAndIdIsNot(String username, Long userId);

    AppUser findByUsername(String username);
}
