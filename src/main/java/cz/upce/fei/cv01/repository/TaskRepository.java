package cz.upce.fei.cv01.repository;

import cz.upce.fei.cv01.domain.Task;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {
    List<Task> findAllByAuthorId(final Long authorId);
}