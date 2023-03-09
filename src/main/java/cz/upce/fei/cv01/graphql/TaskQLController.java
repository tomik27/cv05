package cz.upce.fei.cv01.graphql;

import cz.upce.fei.cv01.dto.AppUserResponseDtoV1;
import cz.upce.fei.cv01.dto.TaskResponseDtoV1;
import cz.upce.fei.cv01.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class TaskQLController {
    private final TaskService taskService;

    @SchemaMapping(typeName = "AppUser")
    public List<TaskResponseDtoV1> tasks(AppUserResponseDtoV1 appUser){
       return taskService.findAllByAuthorId(appUser.getId());
    }
}
