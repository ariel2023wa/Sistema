package sistema.repositories;

import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import sistema.entities.Especialidad;
import sistema.entities.Tecnico;
import sistema.repositories.exceptions.NonexistentEntityException;

import java.io.Serializable;
import java.util.List;

public class TecnicoRepository implements Serializable {

    private EntityManagerFactory emf = null;

    public TecnicoRepository() {
        emf = Persistence.createEntityManagerFactory("JPA_PU");
    }

    public TecnicoRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tecnico tecnico) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tecnico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tecnico tecnico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tecnico = em.merge(tecnico);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = tecnico.getIdTecnico();
                if (findTecnico(id) == null) {
                    throw new NonexistentEntityException("The tecnico with id " + id + " no longer exists.");
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
            Tecnico tecnico;
            try {
                tecnico = em.getReference(Tecnico.class, id);
                tecnico.getIdTecnico();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tecnico with id " + id + " no longer exists.", enfe);
            }
            em.remove(tecnico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tecnico> findTecnicoEntities() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tecnico.class));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tecnico findTecnico(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tecnico.class, id);
        } finally {
            em.close();
        }
    }

    public int getTecnicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tecnico> rt = cq.from(Tecnico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Tecnico> findTecnicosByEspecialidades(List<Especialidad> especialidades) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Tecnico> cq = cb.createQuery(Tecnico.class);

            Root<Tecnico> tecnicoRoot = cq.from(Tecnico.class);
            Join<Tecnico, Especialidad> especialidadJoin = tecnicoRoot.join("especialidad");

            ParameterExpression<List> especialidadesParameter = cb.parameter(List.class);
            cq.select(tecnicoRoot)
                    .distinct(true)
                    .where(especialidadJoin.in(especialidadesParameter));

            TypedQuery<Tecnico> q = em.createQuery(cq);
            q.setParameter(especialidadesParameter, especialidades);

            return q.getResultList();
        } finally {
            em.close();
        }
    }

}




