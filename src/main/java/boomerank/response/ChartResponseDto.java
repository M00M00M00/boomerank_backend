package boomerank.response;

import boomerank.dto.ChartDto;
import boomerank.dto.ChartFilterDto;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ChartResponseDto {
    @ApiParam(value = "차트 정보")
    ChartFilterDto chartFilterDto;
    @ApiParam(value = "차트")
    ChartDto chart1;
}
