package com.altimetrik.santhoshg.busReservationSystem.systemData;

public enum SeatTypes {

	SS("SS"), SL("SL");
	
	private String type;
	
	SeatTypes(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
