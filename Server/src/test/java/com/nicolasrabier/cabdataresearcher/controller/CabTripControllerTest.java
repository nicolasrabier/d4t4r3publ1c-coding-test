package com.nicolasrabier.cabdataresearcher.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;

import javax.persistence.EntityManagerFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.nicolasrabier.cabdataresearcher.bean.MedallionCounterOutputBean;
import com.nicolasrabier.cabdataresearcher.business.CabTripService;

@RunWith(SpringRunner.class)
@WebMvcTest(CabTripController.class)
@ContextConfiguration(classes = {EntityManagerFactory.class, CabTripController.class})
public class CabTripControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CabTripService service;
	
	
	@Test
	public void test_status() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/status")
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string("The API is up and running!"))
				.andReturn();
		
	}

	@Test
	public void test_countTripsByMedallionAndPickupDateV1() throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd",Locale.UK);
		
		when(service.countTripsByMedallionAndPickupDate("9150150894F4D1F06AB4A016AF410DB3", formatter.parse("2013-12-31"))).thenReturn(
				0
				);
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/v1/trips/count/by/medallion/and/pickupdate")
				//.accept(MediaType.APPLICATION_JSON)
				.contentType(new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8")))
				.content("{\"medallion\":\"9150150894F4D1F06AB4A016AF410DB3\",\"pickupDate\":\"2013-12-31\"}")
				;
		
		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json("{medallion:9150150894F4D1F06AB4A016AF410DB3,counter:0}"))
				.andReturn();
		
	}
	
	@Test
	public void test_countTripsByMedallionsV1() throws Exception {

		when(service.countTripsByMedallions(Arrays.asList("000318C2E3E6381580E5C99910A60668","00153E36140C5B2A84EA308F355A7925"), false)).thenReturn(
				Arrays.asList(
						new MedallionCounterOutputBean("000318C2E3E6381580E5C99910A60668",37),
						new MedallionCounterOutputBean("00153E36140C5B2A84EA308F355A7925",4)
						)
				);
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/v1/trips/count/by/medallions")
				//.accept(MediaType.APPLICATION_JSON)
				.contentType(new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8")))
				.content("{\"medallion\":[\"000318C2E3E6381580E5C99910A60668\",\"00153E36140C5B2A84EA308F355A7925\"],\"refresh\":\"false\"}")
				;
		
		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json("[{medallion:000318C2E3E6381580E5C99910A60668,counter:37},{medallion:00153E36140C5B2A84EA308F355A7925,counter:4}]"))
				.andReturn();
		
	}
		
}
