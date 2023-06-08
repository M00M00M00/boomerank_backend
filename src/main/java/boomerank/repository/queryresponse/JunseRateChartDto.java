package boomerank.repository.queryresponse;

import lombok.Data;
import lombok.Getter;

@Data
public class JunseRateChartDto {
    Integer year;
    Integer month;
    Double junseRate;
}
