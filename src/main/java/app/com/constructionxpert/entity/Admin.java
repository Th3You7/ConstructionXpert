package app.com.constructionxpert.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

import java.lang.reflect.Member;

@Entity
@PrimaryKeyJoinColumn(name = "adminId")
public class Admin extends User {
    public Admin() {  }
}
