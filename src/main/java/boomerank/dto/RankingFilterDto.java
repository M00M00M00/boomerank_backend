package boomerank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@AllArgsConstructor
public class RankingFilterDto {
    private String type; // 2
    private int geo; // 3
    private int date; // 3
    private String order; // 2
}
