package boomerank.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class RankingFilterDto {
    @ApiParam(value = "매매/전세 - m/j", required = true)
    private String type; // 2
    @ApiParam(value = "시군구 - 1/2/3", required = true)
    private int geo; // 3
    @ApiParam(value = "최근 n개월전 - 1/3/6/12", required = true)
    private int date; // 3
    @ApiParam(value = "정렬 - desc/asc", required = true)
    private String order; // 2
    @ApiParam(value = "페이지 - 1")
    private Integer page = 1;
    @ApiParam(value = "페이지 사이즈 - 25")
    private Integer size = 25;
}
