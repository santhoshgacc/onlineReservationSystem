package com.altimetrik.santhoshg.busReservationSystem.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.altimetrik.santhoshg.busReservationSystem.ui.model.SeatBookingRequest;
import com.altimetrik.santhoshg.busReservationSystem.ui.model.SeatBookingResponse;

@SpringBootTest
public class SeatBookingServiceTest {

	@InjectMocks
	private SeatBookingService seatBookingService;

	@Test
	public void bookRandomSeat() {
		SeatBookingRequest seatReq = new SeatBookingRequest(1000L, 0, 1000);
		Optional<SeatBookingResponse> seatResponse = seatBookingService.bookRandomSeat(seatReq);
		
		assertThat(seatResponse).isNotEmpty();
		
	}
	
	@Test
	public void bookRandomSeatEmptyTest() {
		SeatBookingRequest seatReq = new SeatBookingRequest(1001L, 0, 1200);
		Optional<SeatBookingResponse> seatResponse = seatBookingService.bookRandomSeat(seatReq);
		seatResponse = seatBookingService.bookRandomSeat(seatReq);
		seatResponse = seatBookingService.bookRandomSeat(seatReq);
		seatResponse = seatBookingService.bookRandomSeat(seatReq);
		seatResponse = seatBookingService.bookRandomSeat(seatReq);
		
		assertThat(seatResponse).isEmpty();
		
	}

	@Test
	public void bookSelectedSeatTest() {
		SeatBookingRequest seatReq = new SeatBookingRequest(1001L, 3, 1200);
		Optional<SeatBookingResponse> seatResponse = seatBookingService.bookSelectedSeat(seatReq);

		SeatBookingResponse expectedResponse = new SeatBookingResponse(1001L, 3, 1200 + (1200 * 0.1));
		
		assertEquals(expectedResponse, seatResponse.get());
	}


	@Test
	public void bookSelectedSeatEmptyTest() {
		SeatBookingRequest seatReq = new SeatBookingRequest(1001L, 3, 1200);
		Optional<SeatBookingResponse> seatResponse = seatBookingService.bookSelectedSeat(seatReq);
		seatResponse = seatBookingService.bookSelectedSeat(seatReq);
		
		assertThat(seatResponse).isEmpty();
	}
	
}
