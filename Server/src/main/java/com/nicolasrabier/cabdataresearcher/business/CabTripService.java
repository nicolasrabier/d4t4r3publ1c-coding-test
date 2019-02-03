package com.nicolasrabier.cabdataresearcher.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicolasrabier.cabdataresearcher.bean.MedallionCounterOutputBean;
import com.nicolasrabier.cabdataresearcher.data.CabTripRepository;

@Service
public class CabTripService {

	@Autowired
	private CabTripRepository repo;

	public Integer countTripsByMedallionAndPickupDate(String medallion, Date pickupDate) {
		return repo.countTripsByMedallionAndPickupDate(medallion, pickupDate);
	}
	
	public List<MedallionCounterOutputBean> countTripsByMedallions(List<String> medallions, Boolean refresh) {
		List<Object[]> countList = repo.countTripsByMedallions(medallions, refresh);
		
		List<MedallionCounterOutputBean> res = new ArrayList<>();
		
		countList.stream().forEach(
				o -> res.add(new MedallionCounterOutputBean(String.valueOf(o[0]), ((Long) o[1]).intValue())));
		
		return res;
	}

	public void clearCache() {
		repo.clearCache();
	}

}
