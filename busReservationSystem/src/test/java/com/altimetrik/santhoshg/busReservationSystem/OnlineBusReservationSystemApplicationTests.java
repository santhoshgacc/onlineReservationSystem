package com.altimetrik.santhoshg.busReservationSystem;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.altimetrik.santhoshg.busReservationSystem.model.Bus;
import com.altimetrik.santhoshg.busReservationSystem.model.BusRouteMapping;
import com.altimetrik.santhoshg.busReservationSystem.model.Seat;
import com.altimetrik.santhoshg.busReservationSystem.systemData.SortTypes;
import com.altimetrik.santhoshg.busReservationSystem.ui.model.BusSearchRequest;
import com.altimetrik.santhoshg.busReservationSystem.ui.model.BusSearchResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OnlineBusReservationSystemApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OnlineBusReservationSystemApplicationTests {
	
	@LocalServerPort
	private int port;

	@Autowired
	private ObjectMapper objectMapper;
	
	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();
	
	@Test
	public void getBusSearchResultsTest() throws Exception {

		BusSearchRequest searchReq = new BusSearchRequest("CHE", "BLR", LocalDate.now(), null);
		
		HttpEntity<BusSearchRequest> entity = new HttpEntity<BusSearchRequest>(searchReq, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("bus/search"),
				HttpMethod.POST, entity, String.class);

		String expected = buildBusSearchDefaultResponse();

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	private String buildBusSearchDefaultResponse() throws Exception {
		List<BusRouteMapping> busRouteList = Arrays.asList(new BusRouteMapping(1,1000L,LocalDate.now(),LocalTime.of(10, 00),LocalTime.of(15, 00), 5.0f, 1000),
				   new BusRouteMapping(1,1001L,LocalDate.now(),LocalTime.of(9, 00),LocalTime.of(13, 00), 4.0f, 1200));
		for(BusRouteMapping busRoute : busRouteList) {
			Bus busObj = busRoute.getBus();
			if(busObj.getBusId() == 1000L) {
				busObj.setBusNo("TN001234");
				busObj.setNoOfSeats(5);
				busObj.setOperName("ALT TRAVELS");
				
				List<Seat> seats = Arrays.asList(new Seat(1L,1, "Y", "N", "SS", "N", 1000L),
							  					 new Seat(2L,2, "Y", "N", "SS", "N", 1000L),
							  				     new Seat(3L,3, "Y", "Y", "SS", "N", 1000L),
							  				     new Seat(4L,4, "Y", "N", "SS", "N", 1000L),
							  				     new Seat(5L,5, "Y", "N", "SS", "N", 1000L));
				busObj.setSeats(seats);
				
			} else if(busObj.getBusId() == 1001L) {
				busObj.setBusNo("KA009876");
				busObj.setNoOfSeats(4);
				busObj.setOperName("ESC TRAVELS");
				
				List<Seat> seats = Arrays.asList(new Seat(1L,1, "Y", "N", "SL", "N", 1001L),
							  					 new Seat(2L,2, "Y", "Y", "SL", "N", 1001L),
							  				     new Seat(3L,3, "Y", "N", "SL", "N", 1001L),
							  				     new Seat(4L,4, "Y", "N", "SL", "N", 1001L));
				
				busObj.setSeats(seats);
				
			}
		}
		
		BusSearchResponse expectedResponse = new BusSearchResponse(busRouteList, SortTypes.PRI.getType(), busRouteList.size());
		return objectMapper.writeValueAsString(expectedResponse);
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
