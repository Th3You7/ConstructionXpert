package app.com.constructionxpert.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class AssignmentDTO {
    private long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private UserDTO employer;
    private TaskDTO task;

}
