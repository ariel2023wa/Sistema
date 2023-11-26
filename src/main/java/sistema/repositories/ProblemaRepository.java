package sistema.repositories;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import sistema.entities.Problema;
import sistema.exceptions.NonexistentEntityException;

import java.io.Serializable;
import java.util.List;

public class ProblemaRepository implements Serializable {

    private EntityManagerFactory emf = null;

    public ProblemaRepository() {
        emf = Persistence.createEntityManagerFactory("JPA_PU");
    }

    public ProblemaRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Problema problema) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(problema);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Problema problema) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            problema = em.merge(problema);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = problema.getIdProblema();
                if (findProblema(id) == null) {
                    throw new NonexistentEntityException("The problema with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Problema problema;
            try {
                problema = em.getReference(Problema.class, id);
                problema.getIdProblema();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The problema with id " + id + " no longer exists.", enfe);
            }
            em.remove(problema);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Problema> findProblemaEntities() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Problema.class));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Problema findProblema(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Problema.class, id);
        } finally {
            em.close();
        }
    }

    public int getProblemaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Problema> rt = cq.from(Problema.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
