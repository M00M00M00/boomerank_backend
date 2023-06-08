package boomerank.controller;

import boomerank.dto.ChartDto;
import boomerank.dto.ChartFilterDto;
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

    @ApiOperation(value = "차트 조회", notes = "지역에 대한 연/월별 평당가, 거래량 차트를 반환한다. geo 파라미터에 따라 깊이가 나누어짐.")
    @GetMapping("/price-count")
    public ChartDto getPriceCountChart(ChartFilterDto chartFilterDto){
        return chartService.getPriceCountChart(chartFilterDto);
    }

    @ApiOperation(value = "차트 조회", notes = "특정지역 20평대 아파트에 대한 전세가율 차트를 반환한다. geo 파라미터에 따라 깊이가 나누어짐.")
    @GetMapping("/junse-rate")
    public ChartDto getJunseRateChart(ChartFilterDto chartFilterDto){
        return chartService.getJunseRateChart(chartFilterDto);
    }

}
