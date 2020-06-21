package com.altimetrik.santhoshg.busReservationSystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.altimetrik.santhoshg.busReservationSystem.model.BusRouteMapping;
import com.altimetrik.santhoshg.busReservationSystem.model.Route;
import com.altimetrik.santhoshg.busReservationSystem.repository.BusRouteMappingRepository;
import com.altimetrik.santhoshg.busReservationSystem.repository.RouteRepository;
import com.altimetrik.santhoshg.busReservationSystem.systemData.SortTypes;
import com.altimetrik.santhoshg.busReservationSystem.ui.model.BusSearchRequest;
import com.altimetrik.santhoshg.busReservationSystem.ui.model.BusSearchResponse;

@SpringBootTest
public class BusSearchServiceTest {

		@InjectMocks
		private BusSearchService busSearchService;

		@Mock
		private RouteRepository routeRepository;

		@Mock
		private BusRouteMappingRepository busRouteMappingRepository;
		
		@Test
		public void getBusSearchResultsTest() {
			
			BusSearchRequest searchReq = new BusSearchRequest("CHE", "BLR", LocalDate.now(), null);
			BusSearchResponse response = busSearchService.getBusSearchResults(searchReq);
			
			List<BusRouteMapping> busRouteList = Arrays.asList(new BusRouteMapping(1,1000L,LocalDate.now(),LocalTime.of(10, 00),LocalTime.of(15, 00), 5.0f, 1000),
					   new BusRouteMapping(1,1001L,LocalDate.now(),LocalTime.of(9, 00),LocalTime.of(13, 00), 4.0f, 1200));
			
			BusSearchResponse expectedResponse = new BusSearchResponse(busRouteList, SortTypes.PRI.getType(), busRouteList.size());
			assertEquals(expectedResponse, response);
		}
		
//		@Test
//		public void getSortedBusSearchResultsTest() {
//			BusSearchRequest searchReq = new BusSearchRequest("CHE", "BLR", LocalDate.now(), null);
//			BusSearchResponse response = busSearchService.getBusSearchResults(searchReq);
//			
//			response = busSearchService.getSortedBusSearchResults(response, SortTypes.DEPTIME.getType());
//			
//			List<BusRouteMapping> busRouteList = Arrays.asList(new BusRouteMapping(1,1001L,LocalDate.now(),LocalTime.of(9, 00),LocalTime.of(13, 00), 4.0f, 1200),
//					   new BusRouteMapping(1,1000L,LocalDate.now(),LocalTime.of(10, 00),LocalTime.of(15, 00), 5.0f, 1000));
//			
//			BusSearchResponse expectedResponse = new BusSearchResponse(busRouteList, SortTypes.DEPTIME.getType(), busRouteList.size());
//			assertEquals(expectedResponse, response);
//			
//		}
		
}
