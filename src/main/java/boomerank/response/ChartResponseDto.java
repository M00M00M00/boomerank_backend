package boomerank.response;

import boomerank.dto.ChartDto;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ChartResponseDto {
    @ApiParam(value = "첫번째 차트")
    ChartDto chart1;
    @ApiParam(value = "두번째 차트")
    ChartDto chart2;
}
