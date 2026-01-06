package com.kilometre.zero.dto;

public class LinkPlannedActivityDto {
	
	private Long plannedActivityId;
    private String sessionType;
	
    // ======== Getters & Setters ========
    public Long getPlannedActivityId() {
		return plannedActivityId;
	}
	public void setPlannedActivityId(Long plannedActivityId) {
		this.plannedActivityId = plannedActivityId;
	}
	public String getSessionType() {
		return sessionType;
	}
	public void setSessionType(String sessionType) {
		this.sessionType = sessionType;
	}
}
