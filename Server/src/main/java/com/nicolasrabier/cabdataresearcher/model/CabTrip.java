package com.nicolasrabier.cabdataresearcher.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="CAB_TRIP_DATA")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@NamedQueries(value= {
		@NamedQuery(name="findByMedallionAndPickupDate",query="SELECT ct FROM CabTrip as ct WHERE ct.id.medallion = ?1 AND date(ct.id.pickupDatetime) = date(?2)"),
		@NamedQuery(name="countByMedallionAndPickupDate",query="SELECT count(ct) FROM CabTrip as ct WHERE ct.id.medallion = ?1 AND date(ct.id.pickupDatetime) = date(?2)"),
})
public class CabTrip {

	@EmbeddedId
	@NonNull
	private CabTripPK id;
	
	@NonNull
	@Column(columnDefinition="TEXT")
	private String vendorId;
	
	private Integer rateCode;
	
	@NonNull
	@Column(columnDefinition="TEXT")
	private String storeAndFwdFlag;
	
	private Integer passengerCount;
	
	private Integer tripTimeInSecs;
	
	private Double tripDistance;
	
	private Double pickupLongitude;
	
	private Double pickupLatitude;
	
	private Double dropoffLongitude;
	
	private Double dropoffLatitude;
	
}
