package boomerank.service;

import boomerank.dto.ChartDto;
import boomerank.dto.ChartFilterDto;
import boomerank.repository.ChartRepository;
import boomerank.repository.MaemaeChartRepository;
import boomerank.repository.JunseChartRepository;
import boomerank.repository.queryresponse.ChartResponse;
import boomerank.response.JunseRateChartReponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChartService {
    private final MaemaeChartRepository maemaeChartRepository;
    private final JunseChartRepository junseChartRepository;

    public ChartService(MaemaeChartRepository maemaeChartRepository, JunseChartRepository junseChartRepository) {
        this.maemaeChartRepository = maemaeChartRepository;
        this.junseChartRepository = junseChartRepository;
    }

    public ChartDto getPriceCountChart(ChartFilterDto chartFilterDto) {
        List<ChartResponse> chart = null;
        if (("m".equals(chartFilterDto.getType())) || chartFilterDto.getType() == null)
            chart = getChartWithDto(chartFilterDto, maemaeChartRepository);
        else chart = getChartWithDto(chartFilterDto, junseChartRepository);

        ChartDto dto = new ChartDto(
                chartFilterDto.getGeo1Name(),
                chartFilterDto.getGeo2Name(),
                chartFilterDto.getGeo3Name());
        dto.setPriceChart(chart);

        return dto;
    }

    private List<ChartResponse> getChartWithDto(ChartFilterDto filter, ChartRepository chartRepository) {
        if (filter.getMonthYear().equals("year")){
            if (filter.getGeo() == 1)
                return chartRepository.getChart1Year(filter.getGeo1Name());
            else if (filter.getGeo() == 2)
                return chartRepository.getChart2Year(filter.getGeo1Name(), filter.getGeo2Name());
            else
                return chartRepository.getChart3Year(filter.getGeo1Name(), filter.getGeo2Name(), filter.getGeo3Name());
        }
        if (filter.getMonthYear().equals("month")){
            if (filter.getGeo() == 1)
                return chartRepository.getChart1Month(filter.getGeo1Name());
            else if (filter.getGeo() == 2)
                return chartRepository.getChart2Month(filter.getGeo1Name(), filter.getGeo2Name());
            else
                return chartRepository.getChart3Month(filter.getGeo1Name(), filter.getGeo2Name(), filter.getGeo3Name());

        }
        return null;
    }

    public ChartDto getJunseRateChart(ChartFilterDto chartFilterDto) {
        // 매매가 평균가 차트 + 전세가 평균가 차트 -> 연월별 전세가율 차트 구하기 -> dto와 함께 감싸서 반환
        List<ChartResponse> mmChart = null;
        List<ChartResponse> jsChart = null;

        if (chartFilterDto.getMonthYear().equals("year")) {
            if (chartFilterDto.getGeo() == 1) {
                mmChart = maemaeChartRepository.getAvg20Year1(chartFilterDto.getGeo1Name());
                jsChart = junseChartRepository.getAvg20Year1(chartFilterDto.getGeo1Name());
            } else if (chartFilterDto.getGeo() == 2) {
                mmChart = maemaeChartRepository.getAvg20Year2(chartFilterDto.getGeo1Name(), chartFilterDto.getGeo2Name());
                jsChart = junseChartRepository.getAvg20Year2(chartFilterDto.getGeo1Name(), chartFilterDto.getGeo2Name());
            } else if (chartFilterDto.getGeo() == 3) {
                mmChart = maemaeChartRepository.getAvg20Year3(chartFilterDto.getGeo1Name(), chartFilterDto.getGeo2Name(), chartFilterDto.getGeo3Name());
                jsChart = junseChartRepository.getAvg20Year3(chartFilterDto.getGeo1Name(), chartFilterDto.getGeo2Name(), chartFilterDto.getGeo3Name());
            }
        }
        else {
            if (chartFilterDto.getGeo() == 1) {
                mmChart = maemaeChartRepository.getAvg20Month1(chartFilterDto.getGeo1Name());
                jsChart = junseChartRepository.getAvg20Month1(chartFilterDto.getGeo1Name());
            } else if (chartFilterDto.getGeo() == 2) {
                mmChart = maemaeChartRepository.getAvg20Month2(chartFilterDto.getGeo1Name(), chartFilterDto.getGeo2Name());
                jsChart = junseChartRepository.getAvg20Month2(chartFilterDto.getGeo1Name(), chartFilterDto.getGeo2Name());
            } else if (chartFilterDto.getGeo() == 3) {
                mmChart = maemaeChartRepository.getAvg20Month3(chartFilterDto.getGeo1Name(), chartFilterDto.getGeo2Name(), chartFilterDto.getGeo3Name());
                jsChart = junseChartRepository.getAvg20Month3(chartFilterDto.getGeo1Name(), chartFilterDto.getGeo2Name(), chartFilterDto.getGeo3Name());
            }
        }

        List<JunseRateChartReponse> chart = new ArrayList<>();
        for (int i=0;i<Math.min(mmChart.size(), jsChart.size());i++){
            double junseRate = jsChart.get(i).getAvgPrice() / mmChart.get(i).getAvgPrice();
            chart.add(new JunseRateChartReponse(jsChart.get(i).getYear(), jsChart.get(i).getMonth(), junseRate));
        }

        ChartDto dto = new ChartDto(
                chartFilterDto.getGeo1Name(),
                chartFilterDto.getGeo2Name(),
                chartFilterDto.getGeo3Name());
        dto.setJunseChart(chart);
        return dto;
    }
}
