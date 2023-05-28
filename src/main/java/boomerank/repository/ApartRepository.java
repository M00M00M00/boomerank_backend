package boomerank.repository;

import boomerank.entity.Apart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface ApartRepository extends JpaRepository<Apart, Long> {
    @Query(
            nativeQuery = true,
            value = "select b.geo_1, avg(b.avg_pyeong_price) as avgp, count(b.geo_1) as transcount " +
                    "from boo b " +
                    "where b.apt_trans_date >= :date " +
                    "group by b.geo_1",
            countQuery = "select b.geo_1, avg(b.avg_pyeong_price) as avgp, count(b.geo_1) as transcount " +
                    "from boo b " +
                    "where b.apt_trans_date >= :date " +
                    "group by b.geo_1"
    )
    Page<Map<String, Object>> avgPriceGroupByGeo1(LocalDate date, Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "select b.geo_1, b.geo_2, avg(b.avg_pyeong_price) as avgp, count(b.geo_2) as transcount " +
                    "from boo b " +
                    "where b.apt_trans_date >= :date " +
                    "group by b.geo_1, b.geo_2",
            countQuery = "select b.geo_1, b.geo_2, avg(b.avg_pyeong_price) as avgp, count(b.geo_2) as transcount " +
                    "from boo b " +
                    "where b.apt_trans_date >= :date " +
                    "group by b.geo_1, b.geo_2"
    )
    Page<Map<String, Object>> avgPriceGroupByGeo2(LocalDate date, Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "select b.geo_1, geo_2, geo_3, avg(b.avg_pyeong_price) as avgp, count(b.geo_3) as transcount " +
                    "from boo b " +
                    "where b.apt_trans_date >= :date " +
                    "group by b.geo_1, geo_2, geo_3",
            countQuery = "select b.geo_1, geo_2, geo_3, avg(b.avg_pyeong_price) as avgp, count(b.geo_3) as transcount " +
                    "from boo b " +
                    "where b.apt_trans_date >= :date " +
                    "group by b.geo_1, geo_2, geo_3"
    )
    Page<Map<String, Object>> avgPriceGroupByGeo3(LocalDate date, Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "select b.geo_1, b.geo_2, avg(b.avg_pyeong_price) as avgp, count(b.geo_2) as transcount " +
                    "from boo b " +
                    "where b.apt_trans_date >= :date and b.geo_1 = :geo1Name " +
                    "group by b.geo_2 ",
            countQuery = "select b.geo_1, b.geo_2, avg(b.avg_pyeong_price) as avgp, count(b.geo_2) as transcount " +
                    "from boo b " +
                    "where b.apt_trans_date >= :date and b.geo_1 = :geo1Name " +
                    "group by b.geo_2 "
    )
    Page<Map<String, Object>> avgPriceGroupByGeo1Name(String geo1Name, LocalDate date, Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "select b.geo_1, b.geo_2, b.geo_3, avg(b.avg_pyeong_price) as avgp, count(b.geo_3) as transcount " +
                    "from boo b " +
                    "where b.apt_trans_date >= :date and b.geo_1 = :geo1Name and b.geo_2 = :geo2Name " +
                    "group by b.geo_3 ",
            countQuery = "select b.geo_1, b.geo_2, b.geo_3, avg(b.avg_pyeong_price) as avgp, count(b.geo_3) as transcount " +
                    "from boo b " +
                    "where b.apt_trans_date >= :date and b.geo_1 = :geo1Name and b.geo_2 = :geo2Name " +
                    "group by b.geo_3 "
    )
    Page<Map<String, Object>> avgPriceGroupByGeo2Name(String geo1Name, String geo2Name, LocalDate date, Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "select b.geo_1, b.geo_2, b.geo_3, b.apt_name, avg(b.avg_pyeong_price) as avgp, count(b.apt_name) as transcount " +
                    "from boo b " +
                    "where b.apt_trans_date >= :date and b.geo_1 = :geo1Name and b.geo_2 = :geo2Name and b.geo_3 = :geo3Name " +
                    "group by b.apt_name ",
            countQuery = "select b.geo_1, b.geo_2, b.geo_3, b.apt_name, avg(b.avg_pyeong_price) as avgp, count(b.apt_name) as transcount " +
                    "from boo b " +
                    "where b.apt_trans_date >= :date and b.geo_1 = :geo1Name and b.geo_2 = :geo2Name and b.geo_3 = :geo3Name " +
                    "group by b.apt_name "
    )
    Page<Map<String, Object>> avgPriceGroupByGeo3Name(String geo1Name, String geo2Name, String geo3Name, LocalDate date, Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "select b.geo_1, b.geo_2, b.geo_3, b.apt_name, b.apt_area_pyeong, avg(apt_price) as price " +
                    "from boo b " +
                    "where b.apt_trans_date >= :date and b.apt_area_pyeong >= :area and b.apt_area_pyeong < :area + 10 and b.geo_1 = :geo1Name " +
                    "group by b.geo_1, b.geo_1, b.geo_2, b.geo_3, b.apt_name, b.apt_area_pyeong"
    )
    Page<Map<String, Object>> avgApartPriceGroupByGeo1Name(double area, String geo1Name, LocalDate date, Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "select b.geo_1, b.geo_2, b.geo_3, b.apt_name, b.apt_area_pyeong, avg(apt_price) as price " +
                    "from boo b " +
                    "where b.apt_trans_date >= :date and b.apt_area_pyeong >= :area and b.apt_area_pyeong < :area + 10 and b.geo_1 = :geo1Name and b.geo_2 = :geo2Name " +
                    "group by b.geo_1, b.geo_1, b.geo_2, b.geo_3, b.apt_name, b.apt_area_pyeong"
    )
    Page<Map<String, Object>> avgApartPriceGroupByGeo2Name(double area, String geo1Name, String geo2Name, LocalDate date, Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "select b.geo_1, b.geo_2, b.geo_3, b.apt_name, b.apt_area_pyeong, avg(apt_price) as price " +
                    "from boo b " +
                    "where b.apt_trans_date >= :date and b.apt_area_pyeong >= :area and b.apt_area_pyeong < :area + 10 and b.geo_1 = :geo1Name and b.geo_2 = :geo2Name and b.geo_3 = :geo3Name " +
                    "group by b.geo_1, b.geo_1, b.geo_2, b.geo_3, b.apt_name, b.apt_area_pyeong"
    )
    Page<Map<String, Object>> avgApartPriceGroupByGeo3Name(double area, String geo1Name, String geo2Name, String geo3Name, LocalDate date, Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "select curr.geo_1, (cur - bef) / bef * 100 as inc_rate, cur, bef, cur - bef as inc_amount " +
                    "from (" +
                    "   select geo_1, avg(avg_pyeong_price) as cur from boo where apt_trans_date = :now group by geo_1" +
                    ") as curr " +
                    "join (" +
                    "   select geo_1, avg(avg_pyeong_price) as bef from boo where apt_trans_date = :before group by geo_1" +
                    ") as befo on curr.geo_1 = befo.geo_1 "
    )
    Page<Map<String, Object>> avgIncPriceGroupByGeo1(LocalDate now, LocalDate before, Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "select curr.geo_1, curr.geo_2, (curr.cur - befo.cur2) / befo.cur2 * 100 as inc_rate, curr.cur, befo.cur2, curr.cur - befo.cur2 as inc_amount " +
                    "from (" +
                    "select b.geo_1, b.geo_2, avg(b.avg_pyeong_price) as cur from boo b where b.geo_1 = :geo1Name and b.apt_trans_date >= :nowStart and b.apt_trans_date <= :now group by b.geo_1, b.geo_2 " +
                    ") as curr, (" +
                    "select c.geo_1, c.geo_2, avg(c.avg_pyeong_price) as cur2 from boo c where c.geo_1 = :geo1Name and c.apt_trans_date >= :befStart and c.apt_trans_date <= :before group by c.geo_1, c.geo_2 " +
                    ") as befo where curr.geo_2 = befo.geo_2 "
    )
    Page<Map<String, Object>> avgIncPriceGroupByGeo1Name(String geo1Name, LocalDate nowStart, LocalDate now, LocalDate befStart, LocalDate before, Pageable pageable);

    @Query(value = "SELECT curr.geo_1, curr.geo_2, (curr.cur - befo.cur2) / befo.cur2 * 100 AS inc_rate, curr.cur, befo.cur2, curr.cur - befo.cur2 AS inc_amount " +
            "FROM ( " +
            "    SELECT b.geo_1, b.geo_2, AVG(b.avg_pyeong_price) AS cur " +
            "    FROM Boo b " +
            "    WHERE b.geo_1 = :geo1Name AND b.apt_trans_date >= :nowStart AND b.apt_trans_date <= :now " +
            "    GROUP BY b.geo_1, b.geo_2 " +
            ") AS curr, (" +
            "    SELECT c.geo_1, c.geo_2, AVG(c.avg_pyeong_price) AS cur2 " +
            "    FROM Boo c " +
            "    WHERE c.geo_1 = :geo1Name AND c.apt_trans_date >= :befStart AND c.apt_trans_date <= :before " +
            "    GROUP BY c.geo_1, c.geo_2 " +
            ") AS befo " +
            "WHERE curr.geo_2 = befo.geo_2", nativeQuery = true)
    Page<Map<String, Object>> avgIncPriceGroupByGeo1Name2(@Param("geo1Name") String geo1Name, @Param("nowStart") LocalDate nowStart, @Param("now") LocalDate now, @Param("befStart") LocalDate befStart, @Param("before") LocalDate before, Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "select b.geo_1 as geo1, " +
                    "avg(b.avg_pyeong_price) as avgp " +
                    "from boo b " +
                    "where b.apt_trans_date >= :start and b.apt_trans_date <= :end " +
                    "group by b.geo_1"
    )
    List<IncResponse> avgPriceInc0(LocalDate start, LocalDate end);

    @Query(
            nativeQuery = true,
            value = "select b.geo_1 as geo1, " +
                    "b.geo_2 as geo2, " +
                    "avg(b.avg_pyeong_price) as avgp " +
                    "from boo b " +
                    "where b.geo_1 = :geo1 and b.apt_trans_date >= :start and b.apt_trans_date <= :end " +
                    "group by b.geo_1, b.geo_2"
    )
    List<IncResponse> avgPriceInc1(String geo1, LocalDate start, LocalDate end);
    @Query(
            nativeQuery = true,
            value = "select b.geo_1 as geo1, " +
                    "b.geo_2 as geo2, " +
                    "b.geo_3 as geo3, " +
                    "avg(b.avg_pyeong_price) as avgp " +
                    "from boo b " +
                    "where b.geo_1 = :geo1 and b.geo_2 = :geo2 and b.apt_trans_date >= :start and b.apt_trans_date <= :end " +
                    "group by b.geo_1, b.geo_2, b.geo_3"
    )
    List<IncResponse> avgPriceInc2(String geo1, String geo2, LocalDate start, LocalDate end);
    @Query(
            nativeQuery = true,
            value = "select b.geo_1 as geo1, " +
                    "b.geo_2 as geo2, " +
                    "b.geo_3 as geo3, " +
                    "b.apt_name as aptName, " +
                    "avg(b.avg_pyeong_price) as avgp " +
                    "from boo b " +
                    "where b.geo_1 = :geo1 and b.geo_2 = :geo2 and b.geo_3 = :geo3 and b.apt_trans_date >= :start and b.apt_trans_date <= :end " +
                    "group by b.geo_1, b.geo_2, b.geo_3, b.apt_name"
    )
    List<IncResponse> avgPriceInc3(String geo1, String geo2, String geo3, LocalDate start, LocalDate end);
}

