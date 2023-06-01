package boomerank.repository;

import lombok.Data;
import lombok.Getter;

@Data
public class JunseRateChartDto {
    Integer year;
    Integer month;
    Double junseRate;
}
