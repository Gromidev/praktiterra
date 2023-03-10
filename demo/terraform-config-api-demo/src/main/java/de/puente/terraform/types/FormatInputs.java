package de.puente.terraform.types;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Stellt Inputs des Users
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FormatInputs {
    @NotNull
    @Min(1)
    @Max(8)
    private Integer cpuCount;
    @NotNull
    @Min(1)
    @Max(16*1024)
    private Integer memorySize;
    @NotNull
    private String diskLabel;
    @NotNull
    @Min(20)
    @Max(100)
    private Integer diskSize;
    @Nullable
    private String secondaryDiskLabel;
    @Nullable
    @PositiveOrZero
    @Max(100)
    private Integer secondaryDiskSize;

}