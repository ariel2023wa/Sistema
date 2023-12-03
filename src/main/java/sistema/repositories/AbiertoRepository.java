package sistema.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import sistema.entities.Cliente;
import sistema.entities.state.Abierto;

import java.io.Serializable;

public class AbiertoRepository implements Serializable {

    private EntityManagerFactory emf = null;

    public AbiertoRepository() {
        emf = Persistence.createEntityManagerFactory("JPA_PU");
    }

    public AbiertoRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Abierto abierto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Abierto abiertoEx = em.find(Abierto.class, abierto.getNombre());
            if (abiertoEx==null){
                em.getTransaction().begin();
                em.persist(abierto);
                em.getTransaction().commit();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
