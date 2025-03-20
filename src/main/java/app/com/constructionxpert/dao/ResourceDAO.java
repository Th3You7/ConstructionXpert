package app.com.constructionxpert.dao;

import app.com.constructionxpert.config.HibernateConfig;
import app.com.constructionxpert.dtos.ResourceDTO;
import app.com.constructionxpert.entity.Resource;
import app.com.constructionxpert.mapper.ResourceMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Set;
import java.util.stream.Collectors;

public class ResourceDAO {

    public ResourceDTO getResourceById(long id) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            return ResourceMapper.INSTANCE.toDTO(session.find(Resource.class, id));
        }
    }

    public Set<ResourceDTO> getAllResources() {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("From Resource ", Resource.class)
                    .getResultList()
                    .stream()
                    .map(ResourceMapper.INSTANCE::toDTO)
                    .collect(Collectors.toSet());
        }
    }

    public void addResource(Resource resource) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(resource);
            tx.commit();
        }
    }

    public void updateResource(Resource resource) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(resource);
            tx.commit();
        }
    }

    public void deleteResource(Resource resource) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.remove(resource);
            tx.commit();
        }
    }

    public long getRessourcesCount() {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("select count(*) from Resource ", Long.class)
                    .getSingleResult();
        }
    }


}
