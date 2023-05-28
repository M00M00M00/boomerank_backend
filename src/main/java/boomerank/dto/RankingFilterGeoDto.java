package boomerank.dto;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class RankingFilterGeoDto {
    @ApiParam(value = "매매/전세 - m/j", required = true)
    private String type; // 2
    @ApiParam(value = "시군구 - 1/2/3", required = true)
    private int geo; // 3
    @ApiParam(value = "최근 n개월전 - 1/3/6/12", required = true)
    private int date; // 3

    @ApiParam(value = "정렬 - desc/asc", required = true)
    private String order; // 2
    @ApiParam(value = "페이지 - 1")
    private Integer page;
    @ApiParam(value = "페이지 사이즈 - 25")
    private Integer size;

    @ApiParam(value = "geo1 - 서울특별시", required = true)
    private String geo1Name;
    @ApiParam(value = "geo2 - 강남구")
    private String geo2Name;
    @ApiParam(value = "geo3 - 개포동")
    private String geo3Name;

    @ApiParam(value = "/price 에서만 사용, n십평대 - 20(20 ~ 29평)")
    private int area;
}
