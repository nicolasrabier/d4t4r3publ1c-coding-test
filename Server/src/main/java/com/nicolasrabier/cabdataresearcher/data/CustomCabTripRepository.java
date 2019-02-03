package com.nicolasrabier.cabdataresearcher.data;

import java.util.List;

public interface CustomCabTripRepository {
	
	List<Object[]> countTripsByMedallions(List<String> medallions, Boolean refresh);
	
	void clearCache();

}
