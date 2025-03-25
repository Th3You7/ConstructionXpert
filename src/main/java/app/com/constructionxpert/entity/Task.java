package app.com.constructionxpert.entity;

import app.com.constructionxpert.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NamedEntityGraph
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDate startDate;
    private LocalDate endDate;
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Assignment> assignments = new HashSet<>();
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<AllocatedResource> allocatedResources;
    @ManyToOne()
    @JoinColumn(name = "projectId")
    private Project project;
}
