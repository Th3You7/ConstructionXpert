package app.com.constructionxpert.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Member;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate startDate;
    private LocalDate endDate;
    @ManyToOne
    @JoinColumn(name = "employerId")
    private Employer employer;
    @ManyToOne
    @JoinColumn(name = "taskId")
    private Task task;

}
