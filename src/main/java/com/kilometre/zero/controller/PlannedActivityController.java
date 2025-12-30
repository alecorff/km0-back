package com.kilometre.zero.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kilometre.zero.dto.PlannedActivityDto;
import com.kilometre.zero.entities.PlannedActivity;
import com.kilometre.zero.service.PlannedActivityService;

@RestController
@RequestMapping("/api/planned-activity")
public class PlannedActivityController {
	
	private final PlannedActivityService plannedActivityService;
	private final JwtDecoder jwtDecoder;
	
	public PlannedActivityController(PlannedActivityService plannedActivityService, JwtDecoder jwtDecoder) {
		this.plannedActivityService = plannedActivityService;
		this.jwtDecoder = jwtDecoder;
	}
	
	@PostMapping("/create")
	public ResponseEntity<PlannedActivity> savePlannedActivity(@RequestHeader("Authorization") String authorizationHeader, @RequestBody PlannedActivityDto dto) {

		String jwt = authorizationHeader.replace("Bearer ", "");
		Jwt decodedJwt = jwtDecoder.decode(jwt);
		Long athleteId = decodedJwt.getClaim("athleteId");

		PlannedActivity savedActivity = plannedActivityService.createPlannedActivity(dto, athleteId).getBody();

        return ResponseEntity.status(HttpStatus.CREATED).body(savedActivity);
		
	}
	
	@GetMapping("/getPlannedActivitiesForPlan")
	public ResponseEntity<List<PlannedActivity>> getPlannedActivitiesForPlan(@RequestHeader("Authorization") String authorizationHeader, @RequestParam String planId) {

		String jwt = authorizationHeader.replace("Bearer ", "");
		Jwt decodedJwt = jwtDecoder.decode(jwt);
		Long athleteId = decodedJwt.getClaim("athleteId");
		
		List<PlannedActivity> plannedActivities = plannedActivityService.getPlannedActivitiesForPlan(UUID.fromString(planId), athleteId);
		
	    return ResponseEntity.ok(plannedActivities);
	}

}
