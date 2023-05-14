package boomerank.dto;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChartFilterDto {
    @ApiParam(value = "시군구", required = true, example = "1/2/3")
    private int geo;
    @ApiParam(value = "매매/전세", required = true, example = "m/j")
    private String type;
    @ApiParam(value = "연/월", required = true, example = "month/year")
    private String monthYear;

    @ApiParam(value = "첫번째 geo1", required = true, example = "서울특별시")
    private String geo1Name1;
    @ApiParam(value = "두번째 geo1", required = true, example = "경상북도")
    private String geo1Name2;
    @ApiParam(value = "첫번째 geo2", example = "강남구")
    private String geo2Name1;
    @ApiParam(value = "두번째 geo2", example = "포항북구")
    private String geo2Name2;
    @ApiParam(value = "첫번째 geo3", example = "개포동")
    private String geo3Name1;
    @ApiParam(value = "두번째 geo3", example = "양덕동")
    private String geo3Name2;
}