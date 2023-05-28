package boomerank.controller;

import boomerank.response.IncRateAmountDto;
import boomerank.response.PageResponseDto;
import boomerank.dto.RankingFilterDto;
import boomerank.dto.RankingFilterGeoDto;
import boomerank.service.RankingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rank")
public class RankingController {
    private final RankingService rankingService;

    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @ApiOperation(value = "지역별 평당가 순위 조회(전국)", notes = "각 지역별 평균 평당가 순위를 반환한다. geo 파라미터에 따라 깊이가 나누어짐.")
    @GetMapping("/avg-pyeong-price")
    public PageResponseDto getAvgPyeongRankWithFilters(RankingFilterDto filterDto) {
        int geo = filterDto.getGeo();
        LocalDate date = LocalDate.now().minusMonths(filterDto.getDate());

        Pageable pageable = getPageable(filterDto.getOrder(), filterDto.getPage(), filterDto.getSize(), "avgp");
        return rankingService.getAvgPriceCountRankWithFilters(geo, date, pageable);
    }
    @ApiOperation(value = "특정 지역별 평당가 순위 조회", notes = "특정 지역 내 평균 평당가 순위를 반환한다. geo 파라미터에 따라 깊이가 나누어짐.")
    @GetMapping("/avg-pyeong-price/name")
    public PageResponseDto getAvgPyeongRankWithNameFilters(RankingFilterGeoDto filterDto) {
        int geo = filterDto.getGeo();
        String geo1Name = filterDto.getGeo1Name();
        String geo2Name = filterDto.getGeo2Name();
        String geo3Name = filterDto.getGeo3Name();
        LocalDate date = LocalDate.now().minusMonths(filterDto.getDate());

        Pageable pageable = getPageable(filterDto.getOrder(), filterDto.getPage(), filterDto.getSize(), "avgp");
        return rankingService.getAvgPriceCountRankWithNameFilters(geo, geo1Name, geo2Name, geo3Name, date, pageable);
    }

    @ApiOperation(value = "지역별 거래 건수 순위 조회(전국)", notes = "각 지역별 거래 건수별 순위를 반환한다. geo 파라미터에 따라 깊이가 나누어짐.")
    @GetMapping("/transactions")
    public PageResponseDto getTransactionsRankWithFilters(RankingFilterDto filterDto) {
        int geo = filterDto.getGeo();
        LocalDate date = LocalDate.now().minusMonths(filterDto.getDate());

        Pageable pageable = getPageable(filterDto.getOrder(), filterDto.getPage(), filterDto.getSize(), "transcount");
        return rankingService.getAvgPriceCountRankWithFilters(geo, date, pageable);
    }
    @ApiOperation(value = "특정 지역별 거래 건수 순위 조회", notes = "특정 지역 내 평균 거래 건수 순위를 반환한다. geo 파라미터에 따라 깊이가 나누어짐.")
    @GetMapping("/transactions/name")
    public PageResponseDto getTransactionsRankWithNameFilters(RankingFilterGeoDto filterDto) {
        int geo = filterDto.getGeo();
        String geo1Name = filterDto.getGeo1Name();
        String geo2Name = filterDto.getGeo2Name();
        String geo3Name = filterDto.getGeo3Name();
        LocalDate date = LocalDate.now().minusMonths(filterDto.getDate());

        Pageable pageable = getPageable(filterDto.getOrder(), filterDto.getPage(), filterDto.getSize(), "transcount");
        return rankingService.getAvgPriceCountRankWithNameFilters(geo, geo1Name, geo2Name, geo3Name, date, pageable);
    }

    @ApiOperation(value = "지역별 n십평대 아파트 평균가 순위", notes = "특정 지역별 n십평대 아파트들의 매매가 순위를 반환, geo - geoName의 깊이를 맞춰주어야 합니다.")
    @GetMapping("/price")
    public PageResponseDto getApartPriceRankWithFilters(RankingFilterGeoDto filterDto) {
        int geo = filterDto.getGeo();
        String geo1Name = filterDto.getGeo1Name();
        String geo2Name = filterDto.getGeo2Name();
        String geo3Name = filterDto.getGeo3Name();
        LocalDate date = LocalDate.now().minusMonths(filterDto.getDate());
        int area = filterDto.getArea();

        Pageable pageable = getPageable(filterDto.getOrder(), filterDto.getPage(), filterDto.getSize(), "price");
        return rankingService.getApartPriceRankWithFilters(geo, geo1Name, geo2Name, geo3Name, area, date, pageable);
    }
    @ApiOperation(value = "지역별 n개월 전 대비 평당가 상승액/상승률 순위", notes = "지역별 아파트들의 n개월전 대비 상승액/상승률 순위를 반환")
    @GetMapping("/inc")
    public List<IncRateAmountDto> getInc(
            @ApiParam(value = "geoDepth - 0/1/2/3", required = true) Integer geo,
            @ApiParam(value = "geo1") String geo1,
            @ApiParam(value = "geo2") String geo2,
            @ApiParam(value = "geo3") String geo3,
            @ApiParam(value = "n개월 전", required = true) int date,
            @ApiParam(value = "rate/amount", required = true) String sort) {

        LocalDate nowMonth = LocalDate.now().minusMonths(3);
        LocalDate beforeMonth = nowMonth.minusMonths(date);

        List<IncRateAmountDto> incList = rankingService.getInc(geo, geo1, geo2, geo3, nowMonth, beforeMonth, sort);
        return incList;
    }

    private Pageable getPageable(String order, Integer page, Integer size, String sortKeyword){
        if (page == null) page = 1;
        if (size == null) size = 25;
        if (order.equals("desc"))
            return PageRequest.of(page - 1, size, Sort.by(sortKeyword).descending());
        else return PageRequest.of(page - 1, size, Sort.by(sortKeyword));
    }
}
