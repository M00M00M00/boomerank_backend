package boomerank.repository;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class JdbcApartRepositoryImpl implements JdbcApartRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcApartRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Map<String, Object>> getRankingWithFilters(String query) {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(query);
        return maps;
    }
}
