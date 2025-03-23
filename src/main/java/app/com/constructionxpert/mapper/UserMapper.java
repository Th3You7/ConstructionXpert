package app.com.constructionxpert.mapper;

import app.com.constructionxpert.dtos.UserDTO;
import app.com.constructionxpert.entity.Admin;
import app.com.constructionxpert.entity.Employer;
import app.com.constructionxpert.entity.Supplier;
import app.com.constructionxpert.entity.User;
import app.com.constructionxpert.enums.EmployerRole;
import app.com.constructionxpert.enums.UserRole;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.lang.reflect.Member;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @BeforeMapping
    default void beforeMapping(User user, @MappingTarget UserDTO userDTO) {
        if(user instanceof Admin) {
            userDTO.setRole(UserRole.ADMIN);
        }
        if(user instanceof Supplier) {
            userDTO.setRole(UserRole.SUPPLIER);
        }
        if(user instanceof Employer && ((Employer) user).getEmployerRole() == EmployerRole.MEMBER) {
           userDTO.setRole(UserRole.EMPLOYER_MEMBER);
        }

        if(user instanceof Employer && ((Employer) user).getEmployerRole() == EmployerRole.RESPONSIBLE) {
            userDTO.setRole(UserRole.EMPLOYER_RESPONSIBLE);
        }

    };
    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);
}
