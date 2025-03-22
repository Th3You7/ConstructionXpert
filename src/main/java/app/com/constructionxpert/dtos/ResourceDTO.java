package app.com.constructionxpert.dtos;

import app.com.constructionxpert.enums.ResourceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResourceDTO {
    private long id;
    private String name;
    private String description;
    private ResourceType type;
    private UserDTO supplier;
    private long size;

}
