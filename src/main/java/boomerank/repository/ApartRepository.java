package boomerank.repository;

import boomerank.entity.Apart;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Repository
public interface ApartRepository extends JpaRepository<Apart, Long> {

    @Query(value = "select rank() over (order by avg(avg_pyeong_price) desc) as ranking, geo_1, avg(avg_pyeong_price) from boo group by geo_1", nativeQuery = true)
    List<Map<String, Object>> getRankGeo1();

    @Query(value = "select rank() over (order by avg(avg_pyeong_price) desc) as ranking, geo_1, geo_2, avg(avg_pyeong_price) from boo group by geo_1, geo_2", nativeQuery = true)
    List<Map<String, Object>> getRankGeo2();


}
