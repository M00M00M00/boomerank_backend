package boomerank.dto;

import boomerank.repository.ChartResponse;
import boomerank.response.JunseRateChartReponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ChartDto {

    String geo1;
    String geo2;
    String geo3;
    List<ChartResponse> priceChart;
    List<JunseRateChartReponse> junseChart;

    public ChartDto(String geo1, String geo2, String geo3) {
        this.geo1 = geo1;
        this.geo2 = geo2;
        this.geo3 = geo3;
    }

    public void setPriceChart(List<ChartResponse> priceChart) {
        this.priceChart = priceChart;
    }

    public void setJunseChart(List<JunseRateChartReponse> junseChart) {
        this.junseChart = junseChart;
    }
}
