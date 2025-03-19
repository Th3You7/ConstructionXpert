package app.com.constructionxpert.entity;

import app.com.constructionxpert.enums.EmployerRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "employerId")
@Getter
@Setter
@NoArgsConstructor
public class Employer extends User{
    EmployerRole role;
    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Assignment> assignments;
}
