package app.com.constructionxpert.dao;

import app.com.constructionxpert.config.HibernateConfig;
import app.com.constructionxpert.dtos.AllocatedResourceDTO;
import app.com.constructionxpert.entity.AllocatedResource;
import app.com.constructionxpert.mapper.AllocatedResourceMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Set;
import java.util.stream.Collectors;

public class AllocatedResourceDAO {

    public AllocatedResourceDTO getAllocatedResourceBy(long id) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            return AllocatedResourceMapper.INSTANCE.toDTO(session.find(AllocatedResource.class, id));
        }
    }

    public Set<AllocatedResourceDTO> getAllAllocatedResources() {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("From AllocatedResource", AllocatedResource.class)
                    .getResultStream()
                    .map(AllocatedResourceMapper.INSTANCE::toDTO)
                    .collect(Collectors.toSet());
        }
    }

    public void registerAllocatedResource(AllocatedResource allocatedResource) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(allocatedResource);
            tx.commit();

        }
    }

    public void updateAllocatedResource(AllocatedResource allocatedResource) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(allocatedResource);
            tx.commit();
        }
    }

    public void deleteAllocatedResource(AllocatedResource allocatedResource) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.remove(allocatedResource);
            tx.commit();
        }
    }

    public void deleteAllocatedResource(long id) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            AllocatedResource allocatedResource = session.find(AllocatedResource.class, id);
            if (allocatedResource != null) {
                session.remove(allocatedResource);
                tx.commit();
            }
        }
    }

    public long getAllocatedResourceCount() {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("select count(*) from AllocatedResource", Long.class).uniqueResult();
        }
    }

}
