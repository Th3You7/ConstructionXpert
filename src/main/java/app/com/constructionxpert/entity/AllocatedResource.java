package app.com.constructionxpert.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AllocatedResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate createdAt;
    @ManyToOne
    @JoinColumn(name = "taskId")
    private Task task;
    @ManyToOne
    @JoinColumn(name = "resourceId")
    private Resource resource;
}
