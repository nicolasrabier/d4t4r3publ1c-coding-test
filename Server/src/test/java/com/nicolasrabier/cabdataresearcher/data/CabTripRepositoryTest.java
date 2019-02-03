package com.nicolasrabier.cabdataresearcher.data;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.persistence.EntityManagerFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigurationPackage
@ContextConfiguration(classes = {EntityManagerFactory.class})
@EntityScan("com.nicolasrabier.cabdataresearcher.*") 
@EnableJpaRepositories("com.nicolasrabier.cabdataresearcher.*")
@ComponentScan(basePackages = { "com.nicolasrabier.cabdataresearcher.*" })
public class CabTripRepositoryTest {

	@Autowired
	private CabTripRepository repo;
	
	@Test
	public void test_countTripsByMedallionAndPickupDate() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		
		when(repo.countTripsByMedallionAndPickupDate("sdfsda",formatter.parse("2018-05-05"))).thenReturn(35);
		
		Integer item = repo.countTripsByMedallionAndPickupDate("sdfsda", formatter.parse("2018-05-05"));
		assertEquals(35, item.intValue());
	}

}
