package sistema.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import sistema.entities.state.Abierto;
import sistema.entities.state.Cerrado;

import java.io.Serializable;

public class CerradoRepository implements Serializable {

    private EntityManagerFactory emf = null;

    public CerradoRepository() {
        emf = Persistence.createEntityManagerFactory("JPA_PU");
    }

    public CerradoRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cerrado cerrado) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Cerrado cerradoEx = em.find(Cerrado.class, cerrado.getNombre());
            if (cerradoEx==null){

                em.getTransaction().begin();
                em.persist(cerrado);
                em.getTransaction().commit();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
