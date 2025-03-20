package app.com.constructionxpert.dao;

import app.com.constructionxpert.config.HibernateConfig;
import app.com.constructionxpert.dtos.TaskDTO;
import app.com.constructionxpert.entity.Task;
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

    public Set<TaskDTO> getAllTasks() {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Task", Task.class)
                    .getResultStream()
                    .map(TaskMapper.INSTANCE::toDTO)
                    .collect(Collectors.toSet());
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
                session.remove(task);
                tx.commit();
            }
        }
    }


}
