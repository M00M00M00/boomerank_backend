package boomerank.dto;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class JunseRateDto {

    @ApiParam(value = "시군구 - 0/1/2/3", required = true)
    private int geo; // 3
    @ApiParam(value = "최근 n개월 평균 - 1/3/6/12", required = true)
    private int date; // 3

    @ApiParam(value = "정렬 - desc/asc", required = true)
    private String order; // 2
    @ApiParam(value = "정렬타입 - rate/gap", required = true)
    private String orderType; // 2

    @ApiParam(value = "geo1 - 서울특별시")
    private String geo1Name;
    @ApiParam(value = "geo2 - 강남구")
    private String geo2Name;
    @ApiParam(value = "geo3 - 개포동")
    private String geo3Name;
}
