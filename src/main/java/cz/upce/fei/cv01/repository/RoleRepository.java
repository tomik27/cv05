package cz.upce.fei.cv01.repository;

import cz.upce.fei.cv01.domain.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
}
