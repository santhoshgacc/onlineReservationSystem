package com.altimetrik.santhoshg.busReservationSystem.repository;

import java.util.List;



import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.altimetrik.santhoshg.busReservationSystem.model.Bus;
import com.altimetrik.santhoshg.busReservationSystem.model.Seat;

public interface SeatRepository extends CrudRepository<Seat, Long> {

	
	public List<Seat> findByBus(Bus bus);
	
	
	@Transactional(timeout=120)
	public Seat findByBusAndSeatNo(Bus bus, int seatNo);
	
	
}

