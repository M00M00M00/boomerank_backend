package boomerank.response;

import boomerank.dto.ChartDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ChartResponseDto {
    ChartDto chart1;
    ChartDto chart2;
}
