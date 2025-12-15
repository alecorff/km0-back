package com.strava.dashboard.controller;

import java.time.LocalDateTime;
import java.util.List;

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

import com.strava.dashboard.dto.TrainingPlanRequest;
import com.strava.dashboard.dto.TrainingPlanResponse;
import com.strava.dashboard.service.TrainingPlanService;

@RestController
@RequestMapping("/api/plan")
public class TrainingPlanController {

	private final TrainingPlanService trainingPlanService;
	private final JwtDecoder jwtDecoder;

	public TrainingPlanController(TrainingPlanService trainingPlanService, JwtDecoder jwtDecoder) {
		this.trainingPlanService = trainingPlanService;
		this.jwtDecoder = jwtDecoder;
	}

	@PostMapping("/createPlan")
	public ResponseEntity<TrainingPlanResponse> createPlan(@RequestHeader("Authorization") String authorizationHeader,
			@RequestBody TrainingPlanRequest request) {

		String jwt = authorizationHeader.replace("Bearer ", "");
		Jwt decodedJwt = jwtDecoder.decode(jwt);
		Long athleteId = decodedJwt.getClaim("athleteId");

		TrainingPlanResponse response = trainingPlanService.createPlan(request, athleteId);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/getAllPlans")
	public ResponseEntity<List<TrainingPlanResponse>> getAllPlans(@RequestHeader("Authorization") String authorizationHeader) {

		String jwt = authorizationHeader.replace("Bearer ", "");
		Jwt decodedJwt = jwtDecoder.decode(jwt);
		Long athleteId = decodedJwt.getClaim("athleteId");
		
		List<TrainingPlanResponse> plans = trainingPlanService.getAllPlans(athleteId);

	    return ResponseEntity.ok(plans);
	}

}
