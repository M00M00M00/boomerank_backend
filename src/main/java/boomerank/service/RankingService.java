package boomerank.service;

import boomerank.repository.IncResponse;
import boomerank.response.IncRateAmountDto;
import boomerank.response.PageResponseDto;
import boomerank.repository.ApartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class RankingService {
    private final ApartRepository apartRepository;

    public RankingService(ApartRepository apartRepository) {
        this.apartRepository = apartRepository;
    }

    public PageResponseDto getAvgPriceCountRankWithFilters(int geo, LocalDate date, Pageable pageable) {
        Page<Map<String, Object>> maps = null;
        if (geo == 1) maps = apartRepository.avgPriceGroupByGeo1(date, pageable);
        else if (geo == 2) maps = apartRepository.avgPriceGroupByGeo2(date, pageable);
        else maps = apartRepository.avgPriceGroupByGeo3(date, pageable);
        List<Map<String, Object>> retList = getMaps(pageable, maps);
        int totalPages = maps.getTotalPages();
        long totalElements = maps.getTotalElements();

        return new PageResponseDto(totalPages, totalElements, retList);
    }

    public PageResponseDto getAvgPriceCountRankWithNameFilters(int geo, String geo1Name, String geo2Name, String geo3Name, LocalDate date, Pageable pageable) {
        Page<Map<String, Object>> maps = null;

        if (geo2Name == null && geo3Name == null) maps = apartRepository.avgPriceGroupByGeo1Name(geo1Name, date, pageable);
        else if (geo3Name == null) maps = apartRepository.avgPriceGroupByGeo2Name(geo1Name, geo2Name, date, pageable);
        else maps = apartRepository.avgPriceGroupByGeo3Name(geo1Name, geo2Name, geo3Name, date, pageable);

        List<Map<String, Object>> retList = getMaps(pageable, maps);
        int totalPages = maps.getTotalPages();
        long totalElements = maps.getTotalElements();

        return new PageResponseDto(totalPages, totalElements, retList);
    }

    private List<Map<String, Object>> getMaps(Pageable pageable, Page<Map<String, Object>> maps) {
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int rank = pageSize * pageNumber + 1;
        List<Map<String, Object>> collect = new ArrayList<>();
        for (var map : maps){
            Map<String, Object> m = new HashMap<>();
            double avgprice = (double) map.get("avgp");
            m.put("avgp25", avgprice * 25);
            m.put("avgp32", avgprice * 32);
            m.put("rank", rank++);
            for (var key : map.keySet()){
                m.put(key, map.get(key));
            }
            collect.add(m);
        }
        return collect;
    }

    private List<Map<String, Object>> getMaps2(Pageable pageable, Page<Map<String, Object>> maps) {
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int rank = pageSize * pageNumber + 1;
        List<Map<String, Object>> collect = new ArrayList<>();
        for (var map : maps){
            Map<String, Object> m = new HashMap<>();
            m.put("rank", rank++);
            for (var key : map.keySet()){
                m.put(key, map.get(key));
            }
            collect.add(m);
        }
        return collect;
    }




    public PageResponseDto getApartPriceRankWithFilters(int geo, String geo1Name, String geo2Name, String geo3Name, int area, LocalDate date, Pageable pageable) {
        Page<Map<String, Object>> maps = null;
        if (geo == 1) maps = apartRepository.avgApartPriceGroupByGeo1Name(area, geo1Name, date, pageable);
        else if (geo == 2) maps = apartRepository.avgApartPriceGroupByGeo2Name(area, geo1Name, geo2Name, date, pageable);
        else maps = apartRepository.avgApartPriceGroupByGeo3Name(area, geo1Name, geo2Name, geo3Name, date, pageable);

        List<Map<String, Object>> retList = getMaps2(pageable, maps);
        int totalPages = maps.getTotalPages();
        long totalElements = maps.getTotalElements();

        return new PageResponseDto(totalPages, totalElements, retList);

    }

    public PageResponseDto getIncPriceCountRankWithFilters(LocalDate now, LocalDate before, Pageable pageable) {
        Page<Map<String, Object>> maps = null;
        maps = apartRepository.avgIncPriceGroupByGeo1(now, before, pageable);

        List<Map<String, Object>> retList = getMaps2(pageable, maps);
        int totalPages = maps.getTotalPages();
        long totalElements = maps.getTotalElements();

        return new PageResponseDto(totalPages, totalElements, retList);

    }


    public PageResponseDto getIncPriceCountRankWithNameFilters(int geo, String geo1Name, String geo2Name, String geo3Name, LocalDate now, LocalDate before, Pageable pageable) {
        Page<Map<String, Object>> maps = null;
        LocalDate nowStart = now.minusMonths(1);
        LocalDate befStart = before.minusMonths(1);
        if (geo == 1) {
            maps = apartRepository.avgIncPriceGroupByGeo1Name2(geo1Name, nowStart, now, befStart, before, pageable);
        }
//        if (geo == 1) maps = apartRepository.avgIncPriceGroupByGeo1Name(geo1Name, date, pageable);
//        else if (geo == 2) maps = apartRepository.avgApartPriceGroupByGeo2Name(geo1Name, geo2Name, date, pageable);
//        else maps = apartRepository.avgApartPriceGroupByGeo3Name(geo1Name, geo2Name, geo3Name, date, pageable);

        List<Map<String, Object>> retList = getMaps2(pageable, maps);
        int totalPages = maps.getTotalPages();
        long totalElements = maps.getTotalElements();

        return new PageResponseDto(totalPages, totalElements, retList);
    }

    public List<IncRateAmountDto> getInc(int geo, String geo1, String geo2, String geo3, LocalDate now, LocalDate before, String sort) {

        LocalDate nowStart = now.minusMonths(1);
        LocalDate befStart = before.minusMonths(1);

        List<IncResponse> curList = null;
        List<IncResponse> beforeList = null;
        if (geo1 == null && geo2 == null && geo3 == null) {
            curList = apartRepository.avgPriceInc0(nowStart, now);
            beforeList = apartRepository.avgPriceInc0(befStart, before);
        }
        else if (geo2 == null && geo3 == null) {
            curList = apartRepository.avgPriceInc1(geo1, nowStart, now);
            beforeList = apartRepository.avgPriceInc1(geo1, befStart, before);
        }
        else if (geo3 == null) {
            curList = apartRepository.avgPriceInc2(geo1, geo2, nowStart, now);
            beforeList = apartRepository.avgPriceInc2(geo1, geo2, befStart, before);
        }
        else {
            curList = apartRepository.avgPriceInc3(geo1, geo2, geo3, nowStart, now);
            beforeList = apartRepository.avgPriceInc3(geo1, geo2, geo3, befStart, before);
        }

        List<IncRateAmountDto> ret = getReturnList(geo, beforeList, curList);
        ret = sortBy(sort, ret);
        addRank(ret);
        return ret;
    }

    private void addRank(List<IncRateAmountDto> ret) {
        int rank = 1;
        for (var dto : ret){
            dto.setRank(rank++);
        }
    }

    private List<IncRateAmountDto> sortBy(String sort, List<IncRateAmountDto> ret) {
        if (sort.equals("rate"))
            ret.sort((o1, o2) -> {
                if (o1.getIncRate() < o2.getIncRate()) return 1;
                return -1;
            });
        else if (sort.equals("amount"))
            ret.sort((o1, o2) -> {
                if (o1.getIncAmount() < o2.getIncAmount()) return 1;
                return -1;
            });
        return ret;
    }

    private List<IncRateAmountDto> getReturnList(int geo, List<IncResponse> beforeList, List<IncResponse> curList){
        List<IncRateAmountDto> ret = new ArrayList<>();

        for (var cur : curList){
            double avgpCur = cur.getAvgp();

            for (var bef : beforeList){
                double avgpBefore = bef.getAvgp();

                if (geo == 0 && cur.getGeo1().equals(bef.getGeo1())){
                    double incRate = (avgpCur - avgpBefore) / avgpBefore * 100;
                    double incAmount = avgpCur - avgpBefore;
                    ret.add(new IncRateAmountDto(cur.getGeo1(), avgpBefore, avgpCur, incRate, incAmount));
                    break;
                }
                if (geo == 1 && cur.getGeo2().equals(bef.getGeo2())){
                    double incRate = (avgpCur - avgpBefore) / avgpBefore * 100;
                    double incAmount = avgpCur - avgpBefore;
                    IncRateAmountDto dtoToAdd = new IncRateAmountDto(cur.getGeo1(), avgpBefore, avgpCur, incRate, incAmount);
                    dtoToAdd.setGeo2(cur.getGeo2());
                    ret.add(dtoToAdd);
                    break;
                }
                if (geo == 2 && cur.getGeo3().equals(bef.getGeo3())){
                    double incRate = (avgpCur - avgpBefore) / avgpBefore * 100;
                    double incAmount = avgpCur - avgpBefore;
                    IncRateAmountDto dtoToAdd = new IncRateAmountDto(cur.getGeo1(), avgpBefore, avgpCur, incRate, incAmount);
                    dtoToAdd.setGeo2(cur.getGeo2());
                    dtoToAdd.setGeo3(cur.getGeo3());
                    ret.add(dtoToAdd);
                    break;
                }
                if (geo == 3 && cur.getAptName().equals(bef.getAptName())){
                    double incRate = (avgpCur - avgpBefore) / avgpBefore * 100;
                    double incAmount = avgpCur - avgpBefore;
                    IncRateAmountDto dtoToAdd = new IncRateAmountDto(cur.getGeo1(), avgpBefore, avgpCur, incRate, incAmount);
                    dtoToAdd.setGeo2(cur.getGeo2());
                    dtoToAdd.setGeo3(cur.getGeo3());
                    dtoToAdd.setAptName(cur.getAptName());
                    ret.add(dtoToAdd);
                    break;
                }
            }
        }
        return ret;
    }

}
