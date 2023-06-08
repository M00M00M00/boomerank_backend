package boomerank.repository.queryresponse;

import boomerank.repository.queryresponse.IncResponse;

public class IncResponseImpl {
    String geo1;
    Double avgp;

    public IncResponseImpl(IncResponse incResponse){
        geo1 = incResponse.getGeo1();
        avgp = incResponse.getAvgp();
    }

    public String getGeo1() {
        return geo1;
    }

    public Double getAvgp() {
        return avgp;
    }
}
