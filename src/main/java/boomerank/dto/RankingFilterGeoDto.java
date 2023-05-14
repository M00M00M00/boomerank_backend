package boomerank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class RankingFilterGeoDto {
    private String type; // 2
    private int geo;
    private String geo1Name;
    private String geo2Name;
    private String geo3Name;

    private int area;

    private int date; // 3


    private String order; // 2
    private int page;
    private int size;
}
