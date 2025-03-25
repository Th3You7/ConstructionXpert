package app.com.constructionxpert.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class AllocatedResourceDTO {
    private long id;
    private LocalDate createdAt;
    private TaskDTO taskDTO;
    private long size;
    private ResourceDTO resourceDTO;
}
