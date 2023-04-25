package dev.iitp.publisher.repository;

import dev.iitp.publisher.model.Latency;
import org.springframework.stereotype.Repository;

//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;

//@Repository
public class LatencyRepository {

//    @PersistenceContext
//    private EntityManager em;

    public Long save(Latency latency) {
//        em.persist(latency);
        return latency.getId();
    }
}
