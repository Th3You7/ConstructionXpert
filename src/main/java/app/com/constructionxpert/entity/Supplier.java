package app.com.constructionxpert.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "supplierId")
@Getter
@Setter
@NoArgsConstructor
public class Supplier extends User {
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Resource> resources;
}
