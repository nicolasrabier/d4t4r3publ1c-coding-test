package com.nicolasrabier.cabdataresearcher.data;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nicolasrabier.cabdataresearcher.model.CabTrip;
import com.nicolasrabier.cabdataresearcher.model.CabTripPK;

@Repository
public interface CabTripRepository extends JpaRepository<CabTrip, CabTripPK>, CustomCabTripRepository {

	@Query(name="findByMedallionAndPickupDate")
	List<CabTrip> findByMedallionAndPickupDate(String medallion, Date pickupDate);
	
	@Query(name="countByMedallionAndPickupDate")
	Integer countTripsByMedallionAndPickupDate(String medallion, Date pickupDate);
	
}
