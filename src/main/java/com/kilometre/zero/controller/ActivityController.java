package com.kilometre.zero.controller;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kilometre.zero.service.ActivityService;
import com.kilometre.zero.service.UserService;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {

  private final ActivityService activityService;	
  private final UserService userService;
  private final JwtDecoder jwtDecoder;

  public ActivityController(ActivityService activityService, UserService userService, JwtDecoder jwtDecoder) {
	  this.activityService = activityService;
	  this.userService = userService;
      this.jwtDecoder = jwtDecoder;
  }

  @GetMapping("/syncActivities")
  public ResponseEntity<LocalDateTime> syncAllActivities(@RequestHeader("Authorization") String authorizationHeader, @RequestParam(required = false) LocalDateTime lastSync) {

	  String jwt = authorizationHeader.replace("Bearer ", "");
      Jwt decodedJwt = jwtDecoder.decode(jwt);
      Long athleteId = decodedJwt.getClaim("athleteId");
      String accessToken = userService.getAccessTokenByAthleteId(athleteId);

      // Première synchronisation
      if (lastSync == null) {
    	  activityService.syncAllActivities(accessToken, athleteId);
      } else {
    	  activityService.syncLastActivities(accessToken, athleteId, lastSync);
      }
      
      
      lastSync = LocalDateTime.now();
      userService.updateLastSync(athleteId, lastSync);

      return ResponseEntity.ok(lastSync);
  }

}