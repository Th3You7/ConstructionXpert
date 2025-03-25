package app.com.constructionxpert.dao;

import app.com.constructionxpert.config.HibernateConfig;
import app.com.constructionxpert.dtos.ProjectDTO;
import app.com.constructionxpert.dtos.TaskDTO;
import app.com.constructionxpert.entity.Project;
import app.com.constructionxpert.entity.Task;
import app.com.constructionxpert.mapper.ProjectMapper;
import app.com.constructionxpert.mapper.TaskMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Set;
import java.util.stream.Collectors;

public class TaskDAO {

    public TaskDTO getTask(long id) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
           return TaskMapper.INSTANCE.toDTO(session.find(Task.class, id));

        }
    }

    public Task getTaskById(long id) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.find(Task.class, id);

        }
    }

    public Set<TaskDTO> getAllTasks() {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Task", Task.class)
                    .getResultStream()
                    .map(TaskMapper.INSTANCE::toDTO)
                    .collect(Collectors.toSet());
        }
    }

    public Set<Task> getTasks() {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Task", Task.class)
                    .getResultStream()
                    //.map(TaskMapper.INSTANCE::toDTO)
                    .collect(Collectors.toSet());
        }
    }

    public void addTask(Task task) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(task);
            tx.commit();
        }
    }

    public Set<TaskDTO> getTasksByProject(String projectId) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Task t where t.project.id = :projectId", Task.class)
                    .setParameter("projectId", projectId)
                    .getResultStream()
                    .map(TaskMapper.INSTANCE::toDTO)
                    .collect(Collectors.toSet());
        }
    }

    public Set<TaskDTO> getTasksByEmployer(String employerId) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Task t " +
                            "inner join Assignment on Assignment.task.id = t.id " +
                            "inner join Employer on Assignment.employer.id = Employer.id " +
                            "where Employer.id = :employerId ", Task.class)
                    .setParameter("employerId", employerId)
                    .getResultStream()
                    .map(TaskMapper.INSTANCE::toDTO)
                    .collect(Collectors.toSet());
        }
    }

    public long getAllTasksCount() {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("select count(*) from Task", Long.class).getSingleResult();
        }
    }

    public void updateTask(Task task) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(task);
            tx.commit();
        }
    }

    public void deleteTask(long id) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Task task = session.find(Task.class, id);
            if (task != null) {
                Project project = task.getProject();
                if (project != null) {
                    project.getTasks().remove(task); // Trigger orphanRemoval
                }
                session.remove(task);
                tx.commit();
            }
        }
    }

    public Set<TaskDTO> searchProjectByName(String name) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            return  session.createQuery("from Task where title like :name", Task.class)
                    .setParameter("name", "%" + name.trim() + "%")
                    .getResultStream()
                    .map(TaskMapper.INSTANCE::toDTO)
                    .collect(Collectors.toSet());
        }
    }

    public Set<Object[]> getTaskCountGroupedByStatus() {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("select t.status, count(t) from Task t group by t.status", Object[].class)
                    .getResultStream()
                    .collect(Collectors.toSet());
        }
    }


}
