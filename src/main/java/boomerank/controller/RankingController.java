package boomerank.controller;

import boomerank.response.PageResponseDto;
import boomerank.dto.RankingFilterDto;
import boomerank.dto.RankingFilterGeoDto;
import boomerank.service.RankingService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/rank")
public class RankingController {
    private final RankingService rankingService;

    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @GetMapping("/avg-pyeong-price")
    public PageResponseDto getAvgPyeongRankWithFilters(RankingFilterDto filterDto) {
        int geo = filterDto.getGeo();
        LocalDate date = LocalDate.now().minusMonths(filterDto.getDate());

        Pageable pageable;
        String order = filterDto.getOrder();
        if (order.equals("desc"))
            pageable = PageRequest.of(filterDto.getPage() - 1, filterDto.getSize(), Sort.by("avgp").descending());
        else pageable = PageRequest.of(filterDto.getPage() - 1, filterDto.getSize(), Sort.by("avgp"));

        return rankingService.getAvgPriceCountRankWithFilters(geo, date, pageable);
    }

    @GetMapping("/avg-pyeong-price/name")
    public PageResponseDto getAvgPyeongRankWithNameFilters(RankingFilterGeoDto filterDto) {
        int geo = filterDto.getGeo();
        String geo1Name = filterDto.getGeo1Name();
        String geo2Name = filterDto.getGeo2Name();
        String geo3Name = filterDto.getGeo3Name();
        LocalDate date = LocalDate.now().minusMonths(filterDto.getDate());

        Pageable pageable;
        String order = filterDto.getOrder();
        if (order.equals("desc"))
            pageable = PageRequest.of(filterDto.getPage() - 1, filterDto.getSize(), Sort.by("avgp").descending());
        else pageable = PageRequest.of(filterDto.getPage() - 1, filterDto.getSize(), Sort.by("avgp"));

        return rankingService.getAvgPriceCountRankWithNameFilters(geo, geo1Name, geo2Name, geo3Name, date, pageable);
    }

    @GetMapping("/transactions")
    public PageResponseDto getTransactionsRankWithFilters(RankingFilterDto filterDto) {
        int geo = filterDto.getGeo();
        LocalDate date = LocalDate.now().minusMonths(filterDto.getDate());

        Pageable pageable;
        String order = filterDto.getOrder();
        if (order.equals("desc"))
            pageable = PageRequest.of(filterDto.getPage() - 1, filterDto.getSize(), Sort.by("transcount").descending());
        else pageable = PageRequest.of(filterDto.getPage() - 1, filterDto.getSize(), Sort.by("transcount"));

        return rankingService.getAvgPriceCountRankWithFilters(geo, date, pageable);
    }

    @GetMapping("/transactions/name")
    public PageResponseDto getTransactionsRankWithNameFilters(RankingFilterGeoDto filterDto) {
        int geo = filterDto.getGeo();
        String geo1Name = filterDto.getGeo1Name();
        String geo2Name = filterDto.getGeo2Name();
        String geo3Name = filterDto.getGeo3Name();
        LocalDate date = LocalDate.now().minusMonths(filterDto.getDate());

        Pageable pageable;
        String order = filterDto.getOrder();
        if (order.equals("desc"))
            pageable = PageRequest.of(filterDto.getPage() - 1, filterDto.getSize(), Sort.by("transcount").descending());
        else pageable = PageRequest.of(filterDto.getPage() - 1, filterDto.getSize(), Sort.by("transcount"));

        return rankingService.getAvgPriceCountRankWithNameFilters(geo, geo1Name, geo2Name, geo3Name, date, pageable);
    }

    @GetMapping("/price")
    public PageResponseDto getApartPriceRankWithFilters(RankingFilterGeoDto filterDto) {
        int geo = filterDto.getGeo();
        String geo1Name = filterDto.getGeo1Name();
        String geo2Name = filterDto.getGeo2Name();
        String geo3Name = filterDto.getGeo3Name();
        LocalDate date = LocalDate.now().minusMonths(filterDto.getDate());
        int area = filterDto.getArea();

        Pageable pageable;
        String order = filterDto.getOrder();
        if (order.equals("desc"))
            pageable = PageRequest.of(filterDto.getPage() - 1, filterDto.getSize(), Sort.by("avgp").descending());
        else pageable = PageRequest.of(filterDto.getPage() - 1, filterDto.getSize(), Sort.by("avgp"));

        return rankingService.getApartPriceRankWithFilters(geo, geo1Name, geo2Name, geo3Name, area, date, pageable);
    }

    @GetMapping("/inc-rate")
    public PageResponseDto getIncRateRankWithFilters(RankingFilterDto filterDto) {
        LocalDate nowMonth = LocalDate.now().minusMonths(3);
        LocalDate beforeMonth = nowMonth.minusMonths(filterDto.getDate());

        Pageable pageable;
        String order = filterDto.getOrder();
        if (order.equals("desc"))
            pageable = PageRequest.of(filterDto.getPage() - 1, filterDto.getSize(), Sort.by("inc_rate").descending());
        else pageable = PageRequest.of(filterDto.getPage() - 1, filterDto.getSize(), Sort.by("inc_rate"));


        return rankingService.getIncPriceCountRankWithFilters(nowMonth, beforeMonth, pageable);
    }

    @GetMapping("/inc-rate/name")
    public PageResponseDto getIncRateRankWithNameFilters(RankingFilterGeoDto filterDto) {
        int geo = filterDto.getGeo();
        String geo1Name = filterDto.getGeo1Name();
        String geo2Name = filterDto.getGeo2Name();
        String geo3Name = filterDto.getGeo3Name();
        LocalDate nowMonth = LocalDate.now().minusMonths(3);
        LocalDate beforeMonth = nowMonth.minusMonths(filterDto.getDate());

        Pageable pageable;
        String order = filterDto.getOrder();
        if (order.equals("desc"))
            pageable = PageRequest.of(filterDto.getPage() - 1, filterDto.getSize(), Sort.by("inc_rate").descending());
        else pageable = PageRequest.of(filterDto.getPage() - 1, filterDto.getSize(), Sort.by("inc_rate"));

        return rankingService.getIncPriceCountRankWithNameFilters(geo, geo1Name, geo2Name, geo3Name, nowMonth, beforeMonth, pageable);
    }

    @GetMapping("/inc-amount")
    public PageResponseDto getIncAmountRankWithFilters(RankingFilterDto filterDto) {
        LocalDate nowMonth = LocalDate.now().minusMonths(3);
        LocalDate beforeMonth = nowMonth.minusMonths(filterDto.getDate());

        Pageable pageable;
        String order = filterDto.getOrder();
        if (order.equals("desc"))
            pageable = PageRequest.of(filterDto.getPage() - 1, filterDto.getSize(), Sort.by("inc_amount").descending());
        else pageable = PageRequest.of(filterDto.getPage() - 1, filterDto.getSize(), Sort.by("inc_amount"));


        return rankingService.getIncPriceCountRankWithFilters(nowMonth, beforeMonth, pageable);
    }

//    @GetMapping("/inc-amount/name")
//    public PageResponseDto getIncAmountRateRankWithNameFilters(RankingFilterGeoDto filterDto) {
//        int geo = filterDto.getGeo();
//        String geo1Name = filterDto.getGeo1Name();
//        String geo2Name = filterDto.getGeo2Name();
//        String geo3Name = filterDto.getGeo3Name();
//        LocalDate now = LocalDate.now().minusMonths(3);
//        LocalDate before = now.minusMonths(filterDto.getDate());
//
//        Pageable pageable;
//        String order = filterDto.getOrder();
//        if (order.equals("desc"))
//            pageable = PageRequest.of(filterDto.getPage() - 1, filterDto.getSize(), Sort.by("transcount").descending());
//        else pageable = PageRequest.of(filterDto.getPage() - 1, filterDto.getSize(), Sort.by("transcount"));
//
//        return apartService.getAvgPriceCountRankWithNameFilters(geo, geo1Name, geo2Name, geo3Name, now, before, pageable);
//    }

}
