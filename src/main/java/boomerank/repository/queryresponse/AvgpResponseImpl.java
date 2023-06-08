package boomerank.repository.queryresponse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AvgpResponseImpl {
    Integer rank;
    String geo1;
    String geo2;
    String geo3;
    String aptName;
    Double avgp;
    Integer transcount;
    Double avgp25;
    Double avgp32;

    public AvgpResponseImpl(Integer rank, String geo1, String geo2, String geo3, String aptName, Double avgp) {
        this.rank = rank;
        this.geo1 = geo1;
        this.geo2 = geo2;
        this.geo3 = geo3;
        this.aptName = aptName;
        this.avgp = avgp;
    }

    public AvgpResponseImpl(Integer rank, String geo1, String geo2, String geo3, String aptName, Double avgp, Integer transcount, Double avgp25, Double avgp32) {
        this.rank = rank;
        this.geo1 = geo1;
        this.geo2 = geo2;
        this.geo3 = geo3;
        this.aptName = aptName;
        this.avgp = avgp;
        this.transcount = transcount;
        this.avgp25 = avgp25;
        this.avgp32 = avgp32;
    }

}
