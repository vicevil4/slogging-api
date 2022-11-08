package io.vicevil4.slogging.api.module.controller;

import java.time.LocalDateTime;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.vicevil4.slogging.api.module.dto.HealthDto;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/health")
@Slf4j
public class HealthController {
  
  @RequestMapping(value = "/status", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<HealthDto> healthStatus() {
    HealthDto result = new HealthDto(LocalDateTime.now());
    log.debug("result : {}", result);
    return ResponseEntity.ok(result);
  }
}
