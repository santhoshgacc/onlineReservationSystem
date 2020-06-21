package com.altimetrik.santhoshg.busReservationSystem.repository;

import org.springframework.data.repository.CrudRepository;

import com.altimetrik.santhoshg.busReservationSystem.model.Route;

public interface RouteRepository extends CrudRepository<Route, Long> {

	public Route findBySourceAndDestination(String source, String destination);
	
}

