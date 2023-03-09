package cz.upce.fei.cv01.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.upce.fei.cv01.dto.TaskResponseDtoV1;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
public class Task {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @ToString.Exclude
    private AppUser author;

    @Column
    private LocalDateTime creationDate;

    @Column
    private LocalDateTime updateDate;

    public TaskResponseDtoV1 toDto() {
       return new TaskResponseDtoV1(this.id,this.title,this.creationDate,this.updateDate);
    }

}
