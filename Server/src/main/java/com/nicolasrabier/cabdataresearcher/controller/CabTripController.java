package com.nicolasrabier.cabdataresearcher.controller;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nicolasrabier.cabdataresearcher.bean.MedallionCounterOutputBean;
import com.nicolasrabier.cabdataresearcher.bean.MedallionPickupDateInputBean;
import com.nicolasrabier.cabdataresearcher.bean.MedallionRefreshInputBean;
import com.nicolasrabier.cabdataresearcher.business.CabTripService;

@RestController
public class CabTripController {
	
	@Autowired
	private CabTripService service;

	@GetMapping("/status")
	public String runningService() throws URISyntaxException, MalformedURLException {
		return "The API is up and running!";
	}

	@PostMapping(path="/v1/trips/count/by/medallion/and/pickupdate", consumes="application/json")
	public MedallionCounterOutputBean countTripsByMedallionAndPickupDate(@RequestBody MedallionPickupDateInputBean inputBean) {
		return new MedallionCounterOutputBean(inputBean.getMedallion(), service.countTripsByMedallionAndPickupDate(inputBean.getMedallion(), inputBean.getPickupDate()));
	}

	// TODO: add limitation that the list of integer
	// or use another way to pass the list of driver id
	// TODO: find a way to cache the database
	@PostMapping(path = "/v1/trips/count/by/medallions", consumes = "application/json")
	public List<MedallionCounterOutputBean> countTripsByMedallions(@RequestBody MedallionRefreshInputBean inputBean) {
		return service.countTripsByMedallions(inputBean.getMedallions(), inputBean.getRefresh());
	}

	@GetMapping("/v1/clear/cache")
	public void clearCache() {
		service.clearCache();
	}

}
