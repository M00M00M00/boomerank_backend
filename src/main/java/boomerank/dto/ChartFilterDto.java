package boomerank.dto;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChartFilterDto {
    @ApiParam(value = "시군구 - 1/2/3", required = true, example = "1/2/3")
    private int geo;
    @ApiParam(value = "매매/전세 - m/j", example = "m/j")
    private String type;
    @ApiParam(value = "연/월 - year/month", required = true, example = "month/year")
    private String monthYear;

    @ApiParam(value = "geo1 - 서울특별시", required = true, example = "서울특별시")
    private String geo1Name;
    @ApiParam(value = "geo2 - 강남구", example = "강남구")
    private String geo2Name;
    @ApiParam(value = "geo3 - 개포동", example = "개포동")
    private String geo3Name;
}
