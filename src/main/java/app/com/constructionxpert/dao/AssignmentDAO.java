package app.com.constructionxpert.dao;

import app.com.constructionxpert.config.HibernateConfig;
import app.com.constructionxpert.dtos.AssignmentDTO;
import app.com.constructionxpert.entity.Assignment;
import app.com.constructionxpert.mapper.AssignmentMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Set;
import java.util.stream.Collectors;

public class AssignmentDAO {

    public Assignment getAssignment(long id) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
           return session.find(Assignment.class, id);
        }
    }

    public Set<Assignment> getAssignments() {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Assignment", Assignment.class)
                    .getResultStream()
                   // .map(AssignmentMapper.INSTANCE::toDto)
                    .collect(Collectors.toSet());
        }
    }

    public Set<AssignmentDTO> getAssignmentsByTask(long taskId) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Assignment e where task.id = :taskId", Assignment.class)
                    .setParameter("taskId", taskId)
                    .getResultStream()
                    .map(AssignmentMapper.INSTANCE::toDto)
                    .collect(Collectors.toSet());
        }
    }

    public Set<AssignmentDTO> getAssignmentsByEmployer(long employerId) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Assignment where employer.id = :employerId", Assignment.class)
                    .setParameter("employerId", employerId)
                    .getResultStream()
                    .map(AssignmentMapper.INSTANCE::toDto)
                    .collect(Collectors.toSet());
        }
    }

    public void updateAssignment(Assignment assignment) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(assignment);
            tx.commit();
        }
    }

    public void saveAssignment(Assignment assignment) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(assignment);
            tx.commit();
        }
    }

    public void deleteAssignment(long id) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Assignment assignment = session.get(Assignment.class, id);
            if(assignment != null) {
                session.remove(assignment);
                tx.commit();
            }
        }
    }
}
