package sistema.repositories;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import sistema.entities.MedioNotificacion;
import sistema.exceptions.NonexistentEntityException;

import java.util.List;

public class MedioNotificacionRepository {

    private EntityManagerFactory emf = null;

    public MedioNotificacionRepository() {
        emf = Persistence.createEntityManagerFactory("JPA_PU");
    }

    public MedioNotificacionRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MedioNotificacion medioNotificacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(medioNotificacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MedioNotificacion medioNotificacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            medioNotificacion = em.merge(medioNotificacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = medioNotificacion.getIdMedioNotificacion();
                if (findMedioNotificacion(id) == null) {
                    throw new NonexistentEntityException("The medioNotificacion with id " + id + " no longer exists.");
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
            MedioNotificacion medioNotificacion;
            try {
                medioNotificacion = em.getReference(MedioNotificacion.class, id);
                medioNotificacion.getIdMedioNotificacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The medioNotificacion with id " + id + " no longer exists.", enfe);
            }
            em.remove(medioNotificacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MedioNotificacion> findMedioNotificacionEntities() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MedioNotificacion.class));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public MedioNotificacion findMedioNotificacion(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MedioNotificacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getMedioNotificacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MedioNotificacion> rt = cq.from(MedioNotificacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
