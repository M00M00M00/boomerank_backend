package boomerank.dto;

import boomerank.repository.ChartResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ChartDto {

    String geo1;
    String geo2;
    String geo3;
    List<ChartResponse> chart;
}
