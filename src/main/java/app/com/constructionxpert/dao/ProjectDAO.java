package app.com.constructionxpert.dao;

import app.com.constructionxpert.entity.Employer;
import app.com.constructionxpert.entity.Project;
import app.com.constructionxpert.config.HibernateConfig;
import app.com.constructionxpert.dtos.ProjectDTO;
import app.com.constructionxpert.enums.ProjectStatus;
import app.com.constructionxpert.mapper.ProjectMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProjectDAO {

    public ProjectDTO getProjectById(long id) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            return ProjectMapper.INSTANCE.toDto(session.find(Project.class, id));
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
            return session.createQuery("from Project p where p.title like :name", Project.class)
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
    public Set<Object[]> getProjectCountGroupedByStatus() {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("select p.projectStatus, count(p) from Project p group by p.projectStatus", Object[].class)
                    .getResultStream()
                    .collect(Collectors.toSet());
        }
    }
    public Set<Employer> getProjectEmployers(long projectId) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("select distinct a.employer from Assignment a " +
                            "INNER JOIN Task t on t.id = a.task.id " +
                            "INNER join Project p on p.id = t.project.id " +
                            "where p.id = :projectId", Employer.class)
                    .setParameter("projectId", projectId)
                    .getResultStream()
                    .collect(Collectors.toSet());
        }
    }

    public Set<Object[]> getProjectAllocatedResourcesGroupedByResourceType(long projectId) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("select distinct a.resource.type, SUM(a.size) from AllocatedResource a " +
                    "join a.task t " +
                    "where t.project.id = :projectId", Object[].class)
                    .setParameter("projectId", projectId)
                    .getResultStream()
                    .collect(Collectors.toSet());
        }
    }
}
