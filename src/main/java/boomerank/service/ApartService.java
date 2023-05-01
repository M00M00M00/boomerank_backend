package boomerank.service;

import boomerank.dto.RankingFilterDto;
import boomerank.repository.ApartRepository;
import boomerank.repository.JdbcApartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ApartService {
    private final ApartRepository apartRepository;
    private final JdbcApartRepository jdbcApartRepository;

    public ApartService(ApartRepository apartRepository, JdbcApartRepository jdbcApartRepository) {
        this.apartRepository = apartRepository;
        this.jdbcApartRepository = jdbcApartRepository;
    }

    public List<Map<String, Object>> getRankWithFilters(RankingFilterDto filterDto) {
        String query = makeQuery(filterDto);
        List<Map<String, Object>> rankingWithFilters = jdbcApartRepository.getRankingWithFilters(query);
        return rankingWithFilters;
    }

    private static String makeQuery(RankingFilterDto filterDto){
        String select = "select ";
        String rank = String.format("rank() over (order by avg(avg_pyeong_price) %s) as ranking, ", filterDto.getOrder());

        String selectGeo = "geo_1";
        if (filterDto.getGeo() == 2) selectGeo += ", geo_2";
        if (filterDto.getGeo() == 3) selectGeo += ", geo_2, geo_3";
        String avg = ", avg(avg_pyeong_price) as averagePrice ";
        String avg2532 = ", avg(avg_pyeong_price) * 25 as avg25, avg(avg_pyeong_price) * 32 as avg32 ";
        String from = "from boo ";
        String where = String.format("where apt_trans_date BETWEEN DATE_ADD(NOW(), INTERVAL -%d MONTH) AND NOW() ", filterDto.getDate());
        String group = "group by geo_1";
        if (filterDto.getGeo() == 2) group += ", geo_2";
        if (filterDto.getGeo() == 3) group += ", geo_2, geo_3";
        String query = select + rank + selectGeo + avg + avg2532 + from + where + group;
        return query;
    }


    public List<Map<String, Object>> getTransRankWithFilters(RankingFilterDto filterDto) {
        return null;
    }
}
