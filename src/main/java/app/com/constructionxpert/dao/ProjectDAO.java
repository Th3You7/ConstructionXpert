package app.com.constructionxpert.dao;

import app.com.constructionxpert.Project;
import app.com.constructionxpert.config.HibernateConfig;
import app.com.constructionxpert.dtos.ProjectDTO;
import app.com.constructionxpert.mapper.ProjectMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Set;
import java.util.stream.Collectors;

public class ProjectDAO {

    public ProjectDTO getProjectById(long id) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.find(ProjectDTO.class, id);
        }
    }

    public Set<ProjectDTO> getAllProjects() {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Project", Project.class)
                    .getResultStream()
                    .map(ProjectMapper.INSTANCE::toDto)
                    .collect(Collectors.toSet());
        }
    }

    public Set<ProjectDTO> searchProjectsByName(String name) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Project p where p.name like :name", Project.class)
                    .setParameter("name", "%" + name.trim() + "%")
                    .getResultStream()
                    .map(ProjectMapper.INSTANCE::toDto)
                    .collect(Collectors.toSet());
        }
    }

    public void addProject(Project project) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(project);
            tx.commit();
        }
    }

    public void updateProject(Project project) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(project);
            tx.commit();
        }
    }

    public void deleteProject(long id) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Project project = session.find(Project.class, id);
            if(project != null) {
                session.remove(project);
                tx.commit();
            }

        }
    }

    public long getProjectCount() {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("select count(*) from Project", Long.class).uniqueResult();
        }
    }
}
