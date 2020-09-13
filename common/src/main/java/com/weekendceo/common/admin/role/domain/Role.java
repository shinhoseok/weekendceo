package com.weekendceo.common.admin.role.domain;

public enum Role {
	AUTHENTICATED("ROL-000001"), ADMIN("ROL-000002"), UNAUTHENTICATED("ROL-000003");
	
	private final String value;
	
	Role(String value) {
		this.value = value;
	}
	
	public String stringValue() {
		return value;
	}
	
	public static Role enumValue(String value) {
		switch(value) {
			case "ROL-000001" : return AUTHENTICATED;
			case "ROL-000002" : return ADMIN;
			case "ROL-000003" : return UNAUTHENTICATED;
			default : throw new AssertionError("Unknown value : " + value);
		}
	}
}
