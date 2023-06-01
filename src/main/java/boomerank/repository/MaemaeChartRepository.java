package boomerank.repository;

import boomerank.entity.Apart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MaemaeChartRepository extends JpaRepository<Apart, Long>, ChartRepository {
    @Query(
            nativeQuery = true,
            value = "select year(b.apt_trans_date) as year, avg(b.avg_pyeong_price) as avgPrice, count(b.apt_trans_date) as count " +
                    "from boo b " +
                    "where b.geo_1 = :geo1Name " +
                    "group by year(b.apt_trans_date) " +
                    "order by 1 desc"
    )
    List<ChartResponse> getChart1Year(String geo1Name);

    @Query(
            nativeQuery = true,
            value = "select year(b.apt_trans_date) as year, avg(b.avg_pyeong_price) as avgPrice, count(b.apt_trans_date) as count " +
                    "from boo b " +
                    "where b.geo_1 = :geo1Name and b.geo_2 = :geo2Name " +
                    "group by year(b.apt_trans_date) " +
                    "order by 1 desc"
    )
    List<ChartResponse> getChart2Year(String geo1Name, String geo2Name);

    @Query(
            nativeQuery = true,
            value = "select year(b.apt_trans_date) as year, avg(b.avg_pyeong_price) as avgPrice, count(b.apt_trans_date) as count " +
                    "from boo b " +
                    "where b.geo_1 = :geo1Name and b.geo_2 = :geo2Name and b.geo_3 = :geo3Name " +
                    "group by year(b.apt_trans_date) " +
                    "order by 1 desc"
    )
    List<ChartResponse> getChart3Year(String geo1Name, String geo2Name, String geo3Name);

    @Query(
            nativeQuery = true,
            value = "select year(b.apt_trans_date) as year, month(b.apt_trans_date) as month, avg(b.avg_pyeong_price) as avgPrice, count(b.apt_trans_date) as count " +
                    "from boo b " +
                    "where b.geo_1 = :geo1Name " +
                    "group by year(b.apt_trans_date), month(b.apt_trans_date) " +
                    "order by 1 desc, 2 desc"
    )
    List<ChartResponse> getChart1Month(String geo1Name);

    @Query(
            nativeQuery = true,
            value = "select year(b.apt_trans_date) as year, month(b.apt_trans_date) as month, avg(b.avg_pyeong_price) as avgPrice, count(b.apt_trans_date) as count " +
                    "from boo b " +
                    "where b.geo_1 = :geo1Name and b.geo_2 = :geo2Name " +
                    "group by year(b.apt_trans_date), month(b.apt_trans_date) " +
                    "order by 1 desc, 2 desc"
    )
    List<ChartResponse> getChart2Month(String geo1Name, String geo2Name);

    @Query(
            nativeQuery = true,
            value = "select year(b.apt_trans_date) as year, month(b.apt_trans_date) as month, avg(b.avg_pyeong_price) as avgPrice, count(b.apt_trans_date) as count " +
                    "from boo b " +
                    "where b.geo_1 = :geo1Name and b.geo_2 = :geo2Name and b.geo_3 = :geo3Name " +
                    "group by year(b.apt_trans_date), month(b.apt_trans_date) " +
                    "order by 1 desc, 2 desc"
    )
    List<ChartResponse> getChart3Month(String geo1Name, String geo2Name, String geo3Name);

    @Query(
            nativeQuery = true,
            value = "select year(b.apt_trans_date) as year, avg(b.avg_pyeong_price) as avgPrice " +
                    "from boo b " +
                    "where b.geo_1 = :geo1 and b.apt_area_pyeong between 20 and 30 " +
                    "group by year(b.apt_trans_date) " +
                    "order by 1 desc, 2 desc"
    )
    List<ChartResponse> getAvg20Year1(String geo1);

    @Query(
            nativeQuery = true,
            value = "select year(b.apt_trans_date) as year, avg(b.avg_pyeong_price) as avgPrice " +
                    "from boo b " +
                    "where b.geo_1 = :geo1 and b.geo_2 = :geo2 and b.apt_area_pyeong between 20 and 30 " +
                    "group by year(b.apt_trans_date) " +
                    "order by 1 desc"
    )
    List<ChartResponse> getAvg20Year2(String geo1, String geo2);

    @Query(
            nativeQuery = true,
            value = "select year(b.apt_trans_date) as year, avg(b.avg_pyeong_price) as avgPrice " +
                    "from boo b " +
                    "where b.geo_1 = :geo1 and b.geo_2 = :geo2 and b.geo_3 = :geo3 and b.apt_area_pyeong between 20 and 30 " +
                    "group by year(b.apt_trans_date) " +
                    "order by 1 desc"
    )
    List<ChartResponse> getAvg20Year3(String geo1, String geo2, String geo3);

    @Query(
            nativeQuery = true,
            value = "select year(b.apt_trans_date) as year, month(b.apt_trans_date) as month, avg(b.avg_pyeong_price) as avgPrice " +
                    "from boo b " +
                    "where b.geo_1 = :geo1 and b.apt_area_pyeong between 20 and 30 " +
                    "group by year(b.apt_trans_date), month(b.apt_trans_date) " +
                    "order by 1 desc, 2 desc"
    )
    List<ChartResponse> getAvg20Month1(String geo1);

    @Query(
            nativeQuery = true,
            value = "select year(b.apt_trans_date) as year, month(b.apt_trans_date) as month, avg(b.avg_pyeong_price) as avgPrice " +
                    "from boo b " +
                    "where b.geo_1 = :geo1 and b.geo_2 = :geo2 and b.apt_area_pyeong between 20 and 30 " +
                    "group by year(b.apt_trans_date), month(b.apt_trans_date) " +
                    "order by 1 desc, 2 desc"
    )
    List<ChartResponse> getAvg20Month2(String geo1, String geo2);

    @Query(
            nativeQuery = true,
            value = "select year(b.apt_trans_date) as year, month(b.apt_trans_date) as month, avg(b.avg_pyeong_price) as avgPrice " +
                    "from boo b " +
                    "where b.geo_1 = :geo1 and b.geo_2 = :geo2 and b.geo_3 = :geo3 and b.apt_area_pyeong between 20 and 30 " +
                    "group by year(b.apt_trans_date), month(b.apt_trans_date) " +
                    "order by 1 desc, 2 desc"
    )
    List<ChartResponse> getAvg20Month3(String geo1, String geo2, String geo3);
}
