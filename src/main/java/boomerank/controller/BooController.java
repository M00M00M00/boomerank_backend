package boomerank.controller;

import boomerank.service.ApartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class BooController {
    private final ApartService apartService;

    public BooController(ApartService apartService) {
        this.apartService = apartService;
    }

    @GetMapping("/rank")
    @ResponseBody
    public List<Map<String, Object>> getRank() {
        List<Map<String, Object>> rank = apartService.getRank();

        return rank;
    }
}
