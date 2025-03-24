package app.com.constructionxpert.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ProjectSummaryDTO {
    private long id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
}
