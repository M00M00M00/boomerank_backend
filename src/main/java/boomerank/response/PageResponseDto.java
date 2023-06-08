package boomerank.response;

import boomerank.repository.queryresponse.AvgpResponse;
import boomerank.repository.queryresponse.AvgpResponseImpl;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@ToString
@AllArgsConstructor
public class PageResponseDto {
    @ApiParam(value = "총 페이지 수")
    private int totalPage;
    @ApiParam(value = "결과값 전체 항목수")
    private long totalElements;
    @ApiParam(value = "리턴값")
    private List<AvgpResponseImpl> retList;

}
