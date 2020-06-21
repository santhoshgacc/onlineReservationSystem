package com.altimetrik.santhoshg.busReservationSystem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.altimetrik.santhoshg.busReservationSystem.model.BusRouteMapping;
import com.altimetrik.santhoshg.busReservationSystem.model.Route;

public interface BusRouteMappingRepository extends CrudRepository<BusRouteMapping, Long> {

	public List<BusRouteMapping> findByRoute(Route route);
	
}

