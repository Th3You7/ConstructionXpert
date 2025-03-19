package app.com.constructionxpert.entity;

import app.com.constructionxpert.enums.ResourceType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Resource  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private ResourceType type;
    @ManyToOne
    @JoinColumn(name = "supplierId")
    Supplier supplier;
}
