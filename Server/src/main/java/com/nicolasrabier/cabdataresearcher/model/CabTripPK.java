package com.nicolasrabier.cabdataresearcher.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
public class CabTripPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4884684642762972862L;

	@NonNull
	@Column(columnDefinition="TEXT")
	private String medallion;
	
	@NonNull
	@Column(columnDefinition="TEXT")
	private String hackLicense;
	
	private Date pickupDatetime;
	
	private Date dropoffDatetime;
}
