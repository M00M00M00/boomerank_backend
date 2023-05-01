package boomerank.controller;

import boomerank.dto.RankingFilterDto;
import boomerank.service.ApartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ranking")
public class BooController {
    private final ApartService apartService;

    public BooController(ApartService apartService) {
        this.apartService = apartService;
    }

//    @GetMapping("/rank")
//    @ResponseBody
//    public List<Map<String, Object>> getRank() {
//        List<Map<String, Object>> rank = apartService.getRank();
//
//        return rank;
//    }

    @GetMapping("/pyeong")
    @ResponseBody
    public List<Map<String, Object>> getRankWithParams(RankingFilterDto filterDto) {
        List<Map<String, Object>> rankWithFilters = apartService.getRankWithFilters(filterDto);
        return rankWithFilters;
    }

    @GetMapping("/trans")
    @ResponseBody
    public List<Map<String, Object>> getTransWithParams(RankingFilterDto filterDto){
        List<Map<String, Object>> rankWithFilters = apartService.getTransRankWithFilters(filterDto);
        return null;
    }
}
