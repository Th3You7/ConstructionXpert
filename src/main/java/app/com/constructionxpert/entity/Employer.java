package app.com.constructionxpert.entity;

import app.com.constructionxpert.enums.EmployerRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;

import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "employerId")
public class Employer extends User{
    EmployerRole role;
    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Assignment> assignments;

    public EmployerRole getRole() {
        return role;
    }

    public void setRole(EmployerRole role) {
        this.role = role;
    }

    public Set<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(Set<Assignment> assignments) {
        this.assignments = assignments;
    }
}
