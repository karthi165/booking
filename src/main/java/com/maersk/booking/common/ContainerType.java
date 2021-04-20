package com.maersk.booking.common;

public enum ContainerType {
	DRY("DRY"), REEFER("REEFER");

	public final String code;

	ContainerType(String value) {
		this.code = value;
	}
}
