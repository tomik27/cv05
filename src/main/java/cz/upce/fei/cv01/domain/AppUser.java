package cz.upce.fei.cv01.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class AppUser {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private Boolean active;

    @Column
    private LocalDateTime creationDate;

    @Column
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    private List<Task> tasks= Collections.emptyList();

    @ManyToMany(mappedBy = "users")
    private List<Role> roles = Collections.emptyList();
}
