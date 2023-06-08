package boomerank.repository;

import boomerank.repository.queryresponse.ChartResponse;

import java.util.List;

public interface ChartRepository {
    List<ChartResponse> getChart1Year(String geo1Name);
    List<ChartResponse> getChart2Year(String geo1Name, String geo2Name);
    List<ChartResponse> getChart3Year(String geo1Name, String geo2Name, String geo3Name);
    List<ChartResponse> getChart1Month(String geo1Name);
    List<ChartResponse> getChart2Month(String geo1Name, String geo2Name);
    List<ChartResponse> getChart3Month(String geo1Name, String geo2Name, String geo3Name);
    List<ChartResponse> getAvg20Year1(String geo1);
    List<ChartResponse> getAvg20Year2(String geo1, String geo2);
    List<ChartResponse> getAvg20Year3(String geo1, String geo2, String geo3);
    List<ChartResponse> getAvg20Month1(String geo1);
    List<ChartResponse> getAvg20Month2(String geo1, String geo2);
    List<ChartResponse> getAvg20Month3(String geo1, String geo2, String geo3);
}
