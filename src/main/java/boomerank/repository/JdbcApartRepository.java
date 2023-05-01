package boomerank.repository;

import boomerank.dto.RankingFilterDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface JdbcApartRepository {
    List<Map<String, Object>> getRankingWithFilters(String query);
}
