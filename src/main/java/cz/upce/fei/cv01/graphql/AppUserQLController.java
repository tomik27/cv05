package cz.upce.fei.cv01.graphql;

import cz.upce.fei.cv01.dto.AppUserResponseDtoV1;
import cz.upce.fei.cv01.service.AppUserService;
import cz.upce.fei.cv01.service.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class AppUserQLController {
    private final AppUserService appUserService;

    @QueryMapping
    public AppUserResponseDtoV1 appUser(@Argument final Long id) throws ResourceNotFoundException {
        return appUserService.findById(id).toDto();
    }

}
