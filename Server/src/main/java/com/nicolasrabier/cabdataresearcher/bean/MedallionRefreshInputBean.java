package com.nicolasrabier.cabdataresearcher.bean;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MedallionRefreshInputBean {

	private List<String> medallions;
	private Boolean refresh = false;
	
}
