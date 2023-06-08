package boomerank.service;

import boomerank.dto.JunseRateDto;
import boomerank.repository.*;
import boomerank.repository.queryresponse.AvgpResponse;
import boomerank.repository.queryresponse.AvgpResponseImpl;
import boomerank.repository.queryresponse.IncResponse;
import boomerank.repository.queryresponse.JunseRateResponse;
import boomerank.response.IncRateAmountDto;
import boomerank.response.PageResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@Slf4j
public class RankingService {
    private final ApartRepository apartRepository;
    private final JunseRepository junseRepository;

    public RankingService(ApartRepository apartRepository, JunseRepository junseRepository) {
        this.apartRepository = apartRepository;
        this.junseRepository = junseRepository;
    }

    public PageResponseDto getAvgPriceCountRankWithFilters(String type, int geo, LocalDate date, Pageable pageable) {
        Page<AvgpResponse> maps = null;
        if (type.equals("m")) {
            if (geo == 1) maps = apartRepository.avgPriceGroupByGeo1(date, pageable);
            else if (geo == 2) maps = apartRepository.avgPriceGroupByGeo2(date, pageable);
            else maps = apartRepository.avgPriceGroupByGeo3(date, pageable);
        }
        else {
            if (geo == 1) maps = junseRepository.avgPriceGroupByGeo1(date, pageable);
            else if (geo == 2) maps = junseRepository.avgPriceGroupByGeo2(date, pageable);
            else maps = junseRepository.avgPriceGroupByGeo3(date, pageable);
        }
        List<AvgpResponseImpl> retList = getMaps(pageable, maps, false);
        int totalPages = maps.getTotalPages();
        long totalElements = maps.getTotalElements();

        return new PageResponseDto(totalPages, totalElements, retList);
    }

    public PageResponseDto getAvgPriceCountRankWithNameFilters(String type, int geo, String geo1Name, String geo2Name, String geo3Name, LocalDate date, Pageable pageable) {
        Page<AvgpResponse> maps = null;
        if (type.equals("m")) {
            if (geo2Name == null && geo3Name == null)
                maps = apartRepository.avgPriceGroupByGeo1Name(geo1Name, date, pageable);
            else if (geo3Name == null)
                maps = apartRepository.avgPriceGroupByGeo2Name(geo1Name, geo2Name, date, pageable);
            else maps = apartRepository.avgPriceGroupByGeo3Name(geo1Name, geo2Name, geo3Name, date, pageable);
        }
        else {
            if (geo2Name == null && geo3Name == null)
                maps = junseRepository.avgPriceGroupByGeo1Name(geo1Name, date, pageable);
            else if (geo3Name == null)
                maps = junseRepository.avgPriceGroupByGeo2Name(geo1Name, geo2Name, date, pageable);
            else maps = junseRepository.avgPriceGroupByGeo3Name(geo1Name, geo2Name, geo3Name, date, pageable);
        }
        List<AvgpResponseImpl> retList = getMaps(pageable, maps, false);
        int totalPages = maps.getTotalPages();
        long totalElements = maps.getTotalElements();

        return new PageResponseDto(totalPages, totalElements, retList);
    }

    private List<AvgpResponseImpl> getMaps(Pageable pageable, Page<AvgpResponse> maps, Boolean isPrice) {
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        Integer rank = pageSize * pageNumber + 1;
        List<AvgpResponseImpl> collect = new ArrayList<>();
        for (AvgpResponse map : maps){
            if (isPrice == true){
                collect.add(new AvgpResponseImpl(
                        rank, map.getGeo1(), map.getGeo2(), map.getGeo3(), map.getAptName(),
                        map.getAvgp())
                );
            } else {
                collect.add(new AvgpResponseImpl(
                        rank, map.getGeo1(), map.getGeo2(), map.getGeo3(), map.getAptName(),
                        map.getAvgp(), map.getTranscount(), map.getAvgp() * 25, map.getAvgp() * 32)
                );
            }
            rank++;
        }
        return collect;
    }

    public PageResponseDto getApartPriceRankWithFilters(String type, int geo, String geo1Name, String geo2Name, String geo3Name, int area, LocalDate date, Pageable pageable) {
        Page<AvgpResponse> maps = null;
        if (type.equals("m")){
            if (geo == 1) maps = apartRepository.avgApartPriceGroupByGeo1Name(area, geo1Name, date, pageable);
            else if (geo == 2) maps = apartRepository.avgApartPriceGroupByGeo2Name(area, geo1Name, geo2Name, date, pageable);
            else maps = apartRepository.avgApartPriceGroupByGeo3Name(area, geo1Name, geo2Name, geo3Name, date, pageable);
        }
        else {
            if (geo == 1) maps = junseRepository.avgApartPriceGroupByGeo1Name(area, geo1Name, date, pageable);
            else if (geo == 2) maps = junseRepository.avgApartPriceGroupByGeo2Name(area, geo1Name, geo2Name, date, pageable);
            else maps = junseRepository.avgApartPriceGroupByGeo3Name(area, geo1Name, geo2Name, geo3Name, date, pageable);
        }

        List<AvgpResponseImpl> retList = getMaps(pageable, maps, true);
        int totalPages = maps.getTotalPages();
        long totalElements = maps.getTotalElements();

        return new PageResponseDto(totalPages, totalElements, retList);

    }

    public List<IncRateAmountDto> getInc(String type, int geo, String geo1, String geo2, String geo3, LocalDate now, LocalDate before, String sort) {

        LocalDate nowStart = now.minusMonths(1);
        LocalDate befStart = before.minusMonths(1);

        List<IncResponse> curList = null;
        List<IncResponse> beforeList = null;

        if (type.equals("m")) {
            if (geo1 == null && geo2 == null && geo3 == null) {
                curList = apartRepository.avgPriceInc0(nowStart, now);
                beforeList = apartRepository.avgPriceInc0(befStart, before);
            } else if (geo2 == null && geo3 == null) {
                curList = apartRepository.avgPriceInc1(geo1, nowStart, now);
                beforeList = apartRepository.avgPriceInc1(geo1, befStart, before);
            } else if (geo3 == null) {
                curList = apartRepository.avgPriceInc2(geo1, geo2, nowStart, now);
                beforeList = apartRepository.avgPriceInc2(geo1, geo2, befStart, before);
            } else {
                curList = apartRepository.avgPriceInc3(geo1, geo2, geo3, nowStart, now);
                beforeList = apartRepository.avgPriceInc3(geo1, geo2, geo3, befStart, before);
            }
        }
        else {
            if (geo1 == null && geo2 == null && geo3 == null) {
                curList = junseRepository.avgPriceInc0(nowStart, now);
                beforeList = junseRepository.avgPriceInc0(befStart, before);
            } else if (geo2 == null && geo3 == null) {
                curList = junseRepository.avgPriceInc1(geo1, nowStart, now);
                beforeList = junseRepository.avgPriceInc1(geo1, befStart, before);
            } else if (geo3 == null) {
                curList = junseRepository.avgPriceInc2(geo1, geo2, nowStart, now);
                beforeList = junseRepository.avgPriceInc2(geo1, geo2, befStart, before);
            } else {
                curList = junseRepository.avgPriceInc3(geo1, geo2, geo3, nowStart, now);
                beforeList = junseRepository.avgPriceInc3(geo1, geo2, geo3, befStart, before);
            }
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

    public List<JunseRateResponse> getJunseRates(JunseRateDto junseRateDto) {
        int geo = junseRateDto.getGeo();
        String geo1 = junseRateDto.getGeo1Name();
        String geo2 = junseRateDto.getGeo2Name();
        String geo3 = junseRateDto.getGeo3Name();
        LocalDate date = LocalDate.now().minusMonths(junseRateDto.getDate());

        List<AvgpResponse> mm = null;
        List<AvgpResponse> js = null;

        if (geo == 0){
            mm = apartRepository.getAvg20Prices0(date);
            js = junseRepository.getAvg20Prices0(date);
        } else if (geo == 1){
            mm = apartRepository.getAvg20Prices1(date, geo1);
            js = junseRepository.getAvg20Prices1(date, geo1);
        } else if (geo == 2){
            mm = apartRepository.getAvg20Prices2(date, geo1, geo2);
            js = junseRepository.getAvg20Prices2(date, geo1, geo2);
        } else if (geo == 3){
            mm = apartRepository.getAvg20Prices3(date, geo1, geo2, geo3);
            js = junseRepository.getAvg20Prices3(date, geo1, geo2, geo3);
        }
        List<JunseRateResponse> ret = new ArrayList<>();
        for (AvgpResponse m : mm){
            for (AvgpResponse j : js){
                double gap = m.getAvgp() - j.getAvgp();
                double junseRate = j.getAvgp() / m.getAvgp() * 100;
                if (geo == 0 && m.getGeo1().equals(j.getGeo1())){
                    ret.add(new JunseRateResponse(m.getGeo1(), null, null, null, m.getAvgp(), j.getAvgp(), gap, junseRate));
                    break;
                } else if (geo == 1 && m.getGeo2().equals(j.getGeo2())){
                    ret.add(new JunseRateResponse(m.getGeo1(), m.getGeo2(), null, null, m.getAvgp(), j.getAvgp(), gap, junseRate));
                    break;
                } else if (geo == 2 && m.getGeo3().equals(j.getGeo3())){
                    ret.add(new JunseRateResponse(m.getGeo1(), m.getGeo2(), m.getGeo3(), null, m.getAvgp(), j.getAvgp(), gap, junseRate));
                    break;
                } else if (geo == 3 && m.getAptName().equals(j.getAptName())) {
                    ret.add(new JunseRateResponse(m.getGeo1(), m.getGeo2(), m.getGeo3(), m.getAptName(), m.getAvgp(), j.getAvgp(), gap, junseRate));
                    break;
                }
            }
        }
        String orderType = junseRateDto.getOrderType();
        String order = junseRateDto.getOrder();
        if (orderType.equals("rate")){
            ret.sort((o1, o2) -> {
                if (o1.getJunseRate() < o2.getJunseRate()) return 1;
                else return -1;
            });
        }
        else if (orderType.equals("gap")){
            ret.sort((o1, o2) -> {
                if (o1.getGap() < o2.getGap()) return 1;
                else return -1;
            });
        }
        if (order.equals("asc"))
            Collections.reverse(ret);
        return ret;
    }
}
