package io.vicevil4.slogging.api.module.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
@ToString(includeFieldNames = true)
public class HealthDto {

    private @NonNull LocalDateTime serverTime;

    private @NonNull String gitBranch;

    private @NonNull String gitBuildVersion;

    private @NonNull String gitCommitId;
}
