package io.vicevil4.slogging.api.module.controller;

import io.vicevil4.slogging.api.module.dto.HealthDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/health")
@Slf4j
public class HealthController {

    @Value("${git.branch}")
    private String gitBranch;

    @Value("${git.build.version}")
    private String gitBuildVersion;

    @Value("${git.commit.id.full}")
    private String gitCommitId;

    @RequestMapping(value = "/status", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HealthDto> healthStatus() {
        HealthDto result = new HealthDto(LocalDateTime.now(), gitBranch, gitBuildVersion, gitCommitId);
        log.debug("result : {}", result);
        return ResponseEntity.ok(result);
    }
}
