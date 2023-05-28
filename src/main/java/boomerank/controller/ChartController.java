package boomerank.controller;

import boomerank.dto.ChartFilterDto;
import boomerank.response.ChartResponseDto;
import boomerank.service.ChartService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chart")
public class ChartController {
    private final ChartService chartService;

    public ChartController(ChartService chartService) {
        this.chartService = chartService;
    }

    @ApiOperation(value = "차트 조회", notes = "지역 2개에 대한 연/월별 차트를 반환한다. geo 파라미터에 따라 깊이가 나누어짐.")
    @GetMapping()
    public ChartResponseDto getCharts(ChartFilterDto chartFilterDto){
        return chartService.getCharts(chartFilterDto);
    }
}
