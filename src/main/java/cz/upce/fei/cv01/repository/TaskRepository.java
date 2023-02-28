package cz.upce.fei.cv01.repository;

import cz.upce.fei.cv01.domain.Task;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {
}