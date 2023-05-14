package boomerank.service;

import boomerank.response.PageResponseDto;
import boomerank.repository.ApartRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
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
        if (geo2Name == null && geo3Name == null)
            maps = apartRepository.avgIncPriceGroupByGeo1Name2(geo1Name, nowStart, now, befStart, before, pageable);
//        if (geo == 1) maps = apartRepository.avgIncPriceGroupByGeo1Name(geo1Name, date, pageable);
//        else if (geo == 2) maps = apartRepository.avgApartPriceGroupByGeo2Name(geo1Name, geo2Name, date, pageable);
//        else maps = apartRepository.avgApartPriceGroupByGeo3Name(geo1Name, geo2Name, geo3Name, date, pageable);

        List<Map<String, Object>> retList = getMaps2(pageable, maps);
        int totalPages = maps.getTotalPages();
        long totalElements = maps.getTotalElements();

        return new PageResponseDto(totalPages, totalElements, retList);
    }
}
