package app.com.constructionxpert.dtos;

import app.com.constructionxpert.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private long id;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDate startDate;
    private LocalDate endDate;
    private Set<AssignmentDTO> assignments = new HashSet<>();
    private Set<AllocatedResourceDTO> allocatedResources;
    private ProjectSummaryDTO project;

}
