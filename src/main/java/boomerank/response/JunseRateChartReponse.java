package boomerank.response;

import boomerank.repository.ChartResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JunseRateChartReponse {
    Integer year;
    Integer month;
    Double junseRate;
}
