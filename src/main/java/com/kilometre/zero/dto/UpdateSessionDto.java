package com.kilometre.zero.dto;

public class UpdateSessionDto {
	
	private Long activityId;
    private String sessionType;
	
    // ======== Getters & Setters ========
    public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	public String getSessionType() {
		return sessionType;
	}
	public void setSessionType(String sessionType) {
		this.sessionType = sessionType;
	}

}
