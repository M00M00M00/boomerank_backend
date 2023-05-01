package boomerank;

import boomerank.repository.JdbcApartRepository;
import boomerank.repository.JdbcApartRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class Config {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Bean
    public JdbcApartRepository jdbcApartRepository(){
        return new JdbcApartRepositoryImpl(jdbcTemplate);
    }
}
