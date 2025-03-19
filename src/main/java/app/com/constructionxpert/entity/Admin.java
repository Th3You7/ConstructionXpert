package app.com.constructionxpert.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.NoArgsConstructor;

import java.lang.reflect.Member;

@Entity
@PrimaryKeyJoinColumn(name = "adminId")
@NoArgsConstructor
public class Admin extends User {

}
