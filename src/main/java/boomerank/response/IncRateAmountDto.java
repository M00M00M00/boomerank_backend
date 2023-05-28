package boomerank.response;

import lombok.Getter;

@Getter
public class IncRateAmountDto {
    String geo1;
    String geo2;
    String geo3;
    String aptName;
    Integer rank;
    double avgpBefore;
    double avgpCurrent;
    double incRate;
    double incAmount;

    public IncRateAmountDto(String geo1, double avgpBefore, double avgpCurrent, double incRate, double incAmount) {
        this.geo1 = geo1;
        this.avgpBefore = avgpBefore;
        this.avgpCurrent = avgpCurrent;
        this.incRate = incRate;
        this.incAmount = incAmount;
    }

    public void setGeo2(String geo2) {
        this.geo2 = geo2;
    }

    public void setGeo3(String geo3) {
        this.geo3 = geo3;
    }

    public void setAptName(String aptName) {
        this.aptName = aptName;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
