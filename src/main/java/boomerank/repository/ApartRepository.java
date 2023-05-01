package boomerank.repository;

import boomerank.dto.RankingFilterDto;
import boomerank.domain.Apart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ApartRepository extends JpaRepository<Apart, Long> {

//    @Query(value = "select rank() over (order by avg(avg_pyeong_price) desc) as ranking, geo_1, avg(avg_pyeong_price) from boo where apt_trans_date group by geo_1", nativeQuery = true)
//    List<Map<String, Object>> getRankGeo1desc();
//
//    @Query(value = "select rank() over (order by avg(avg_pyeong_price) desc) as ranking, geo_1, avg(avg_pyeong_price) from boo group by geo_1", nativeQuery = true)
//    List<Map<String, Object>> getRankGeo1desc();
//
//    @Query(value = "select rank() over (order by avg(avg_pyeong_price) desc) as ranking, geo_1, geo_2, avg(avg_pyeong_price) from boo group by geo_1, geo_2", nativeQuery = true)
//    List<Map<String, Object>> getRankGeo2();
//
//    @Query(value = "select rank() over (order by avg(avg_pyeong_price) desc) as ranking, geo_1, avg(avg_pyeong_price) from boo group by geo_1", nativeQuery = true)
//    List<Map<String, Object>> getRankGeo1();
//
//    @Query(value = "select rank() over (order by avg(avg_pyeong_price) desc) as ranking, geo_1, geo_2, avg(avg_pyeong_price) from boo group by geo_1, geo_2", nativeQuery = true)
//    List<Map<String, Object>> getRankGeo2();
//
//    List<Map<String, Object>> getRankGeo3(String ordering);



//    default List<Apart> getRankWithFilters(RankingFilterDto filterDto) {
//
//    };


}
