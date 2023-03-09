package cz.upce.fei.cv01.service;

import cz.upce.fei.cv01.domain.Task;
import cz.upce.fei.cv01.dto.TaskResponseDtoV1;
import cz.upce.fei.cv01.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<TaskResponseDtoV1> findAllByAuthorId(final Long authorId){
        return taskRepository.findAllByAuthorId(authorId)
                .stream()
                .map(t->t.toDto())
                .collect(Collectors.toList());
    }
}
