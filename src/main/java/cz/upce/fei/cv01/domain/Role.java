package cz.upce.fei.cv01.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
public class Role {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;


    @ManyToMany
    @JoinTable(
            name = "app_user_role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "app_user_id")
    )
    @ToString.Exclude
    @JsonIgnore
    private List<AppUser> users = Collections.emptyList();
}
