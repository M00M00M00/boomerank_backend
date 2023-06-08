package boomerank.repository.queryresponse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JunseRateResponse {
    String geo1;
    String geo2;
    String geo3;
    String aptName;
    Double mmAvgp;
    Double jsAvgp;
    Double gap;
    Double junseRate;
}
