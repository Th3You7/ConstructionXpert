package app.com.constructionxpert.dtos;

import app.com.constructionxpert.enums.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;
@AllArgsConstructor
@Getter
@Setter
public class ProjectDTO {
    private long id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private ProjectStatus projectStatus;
    private Set<TaskDTO> tasks;
}
