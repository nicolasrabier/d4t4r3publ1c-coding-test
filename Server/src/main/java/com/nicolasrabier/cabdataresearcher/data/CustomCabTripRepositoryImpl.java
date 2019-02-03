package com.nicolasrabier.cabdataresearcher.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Cache;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public class CustomCabTripRepositoryImpl implements CustomCabTripRepository{
	
	@PersistenceContext
    EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="countTripsByMedallions")
	public List<Object[]> countTripsByMedallions(List<String> medallion, Boolean refresh) {
		StringBuilder qsb = new StringBuilder("SELECT ct.id.medallion, count(ct) FROM CabTrip as ct ");
				if(medallion != null && !medallion.isEmpty()) {
					qsb.append(" WHERE ct.id.medallion IN ?1");
				}
				qsb.append(" GROUP BY ct.id.medallion ORDER BY 1");
		
		return em
			.createQuery(qsb.toString())
			.setHint("org.hibernate.cacheable", !refresh.booleanValue())
			.setMaxResults(500)
			.setParameter(1, medallion)
			.getResultList();

	}

	@Override
	@CacheEvict(value="countTripsByMedallions", allEntries=true)
	public void clearCache() {
		
		SessionFactory sessionFactory = em.unwrap(Session.class).getSessionFactory();
		
		Session session = sessionFactory.openSession();

		if (session != null) {
		    session.clear(); // internal cache clear
		}

		Cache cache = sessionFactory.getCache();

		if (cache != null) {
		    cache.evictAllRegions(); // Evict data from all query regions.
		}
	}
	
}
