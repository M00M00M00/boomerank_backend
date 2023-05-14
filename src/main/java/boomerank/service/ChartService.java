package boomerank.service;

import boomerank.dto.ChartDto;
import boomerank.dto.ChartFilterDto;
import boomerank.response.ChartResponseDto;
import boomerank.repository.ApartRepository;
import boomerank.repository.ChartResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChartService {
    private final ApartRepository apartRepository;

    public ChartService(ApartRepository apartRepository) {
        this.apartRepository = apartRepository;
    }

    public ChartResponseDto getCharts(ChartFilterDto chartFilterDto) {
        List<ChartResponse> chart1 = getChartWithDto(chartFilterDto, 1);
        List<ChartResponse> chart2 = getChartWithDto(chartFilterDto, 2);

        ChartDto dto1 = new ChartDto(
                chartFilterDto.getGeo1Name1(),
                chartFilterDto.getGeo2Name1(),
                chartFilterDto.getGeo3Name1(),
                chart1);
        ChartDto dto2 = new ChartDto(
                chartFilterDto.getGeo1Name2(),
                chartFilterDto.getGeo2Name2(),
                chartFilterDto.getGeo3Name2(),
                chart2);

        return new ChartResponseDto(dto1, dto2);
    }

    private List<ChartResponse> getChartWithDto(ChartFilterDto filter, int type) {
        if (type == 1) {
            if (filter.getGeo() == 1)
                return apartRepository.getChart1(filter.getGeo1Name1());
            else if (filter.getGeo() == 2)
                return apartRepository.getChart2(filter.getGeo1Name1(), filter.getGeo2Name1());
            else
                return apartRepository.getChart3(filter.getGeo1Name1(), filter.getGeo2Name1(), filter.getGeo3Name1());
        }
        else {
            if (filter.getGeo() == 1)
                return apartRepository.getChart1(filter.getGeo1Name2());
            else if (filter.getGeo() == 2)
                return apartRepository.getChart2(filter.getGeo1Name2(), filter.getGeo2Name2());
            else
                return apartRepository.getChart3(filter.getGeo1Name2(), filter.getGeo2Name2(), filter.getGeo3Name2());
        }
    }
}
