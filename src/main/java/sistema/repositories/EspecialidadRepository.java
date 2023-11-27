package sistema.repositories;

import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import sistema.entities.Especialidad;
import sistema.entities.EspecialidadProblema;
import sistema.entities.Problema;
import sistema.exceptions.NonexistentEntityException;

import java.io.Serializable;
import java.util.List;

public class EspecialidadRepository implements Serializable {

    private EntityManagerFactory emf = null;

    public EspecialidadRepository() {
        emf = Persistence.createEntityManagerFactory("JPA_PU");
    }

    public EspecialidadRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }


    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Especialidad especialidad) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(especialidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Especialidad especialidad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            especialidad = em.merge(especialidad);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = especialidad.getIdEspecialidad();
                if (findEspecialidad(id) == null) {
                    throw new NonexistentEntityException("The especialidad with id " + id + " no longer exists.");
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
            Especialidad especialidad;
            try {
                especialidad = em.getReference(Especialidad.class, id);
                especialidad.getIdEspecialidad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The especialidad with id " + id + " no longer exists.", enfe);
            }
            em.remove(especialidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Especialidad> findEspecialidadEntities() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Especialidad.class));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Especialidad findEspecialidad(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Especialidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getEspecialidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Especialidad> rt = cq.from(Especialidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Especialidad> findEspecialidadesByProblemas(List<Problema> problemas) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Especialidad> cq = cb.createQuery(Especialidad.class);

            Root<EspecialidadProblema> especialidadProblemaRoot = cq.from(EspecialidadProblema.class);
            Join<EspecialidadProblema, Problema> problemaJoin = especialidadProblemaRoot.join("problema");
            Join<EspecialidadProblema, Especialidad> especialidadJoin = especialidadProblemaRoot.join("especialidad");

            Subquery<Long> subquery = cq.subquery(Long.class);
            Root<EspecialidadProblema> subqueryRoot = subquery.correlate(especialidadProblemaRoot);
            subquery.select(cb.count(subqueryRoot.get("problema")))
                    .where(cb.equal(subqueryRoot.get("especialidad"), especialidadProblemaRoot.get("especialidad")),
                            subqueryRoot.get("problema").in(problemas));

            cq.select(especialidadJoin)
                    .where(cb.equal(cb.literal((long) problemas.size()), subquery));

            return em.createQuery(cq).getResultList();
        } finally {
            em.close();
        }
    }

    }




