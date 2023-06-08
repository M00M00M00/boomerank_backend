package boomerank.repository;

import boomerank.entity.Apart;
import boomerank.repository.queryresponse.AvgpResponse;
import boomerank.repository.queryresponse.IncResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface ApartRepository extends JpaRepository<Apart, Long> {
    @Query(
            nativeQuery = true,
            value = "select b.geo_1 as geo1, " +
                    "avg(b.avg_pyeong_price) as avgp, " +
                    "count(b.geo_1) as transcount " +
                    "from boo b " +
                    "where b.apt_trans_date >= :date " +
                    "group by b.geo_1",
            countQuery = "select b.geo_1 as geo1, " +
                    "avg(b.avg_pyeong_price) as avgp, " +
                    "count(b.geo_1) as transcount " +
                    "from boo b " +
                    "where b.apt_trans_date >= :date " +
                    "group by b.geo_1"
    )
    Page<AvgpResponse> avgPriceGroupByGeo1(LocalDate date, Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "select b.geo_1 as geo1, " +
                    "b.geo_2 as geo2, " +
                    "avg(b.avg_pyeong_price) as avgp, " +
                    "count(b.geo_1) as transcount " +
                    "from boo b " +
                    "where b.apt_trans_date >= :date " +
                    "group by b.geo_1, b.geo_2",
            countQuery = "select b.geo_1 as geo1, " +
                    "b.geo_2 as geo2, " +
                    "avg(b.avg_pyeong_price) as avgp, " +
                    "count(b.geo_1) as transcount " +
                    "from boo b " +
                    "where b.apt_trans_date >= :date " +
                    "group by b.geo_1, b.geo_2"
    )
    Page<AvgpResponse> avgPriceGroupByGeo2(LocalDate date, Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "select b.geo_1 as geo1, " +
                    "b.geo_2 as geo2, " +
                    "b.geo_3 as geo3, " +
                    "avg(b.avg_pyeong_price) as avgp, " +
                    "count(b.geo_1) as transcount " +
                    "from boo b " +
                    "where b.apt_trans_date >= :date " +
                    "group by b.geo_1, b.geo_2, b.geo_3",
            countQuery = "select b.geo_1 as geo1, " +
                    "b.geo_2 as geo2, " +
                    "b.geo_3 as geo3, " +
                    "avg(b.avg_pyeong_price) as avgp, " +
                    "count(b.geo_1) as transcount " +
                    "from boo b " +
                    "where b.apt_trans_date >= :date " +
                    "group by b.geo_1, b.geo_2, b.geo_3"
    )
    Page<AvgpResponse> avgPriceGroupByGeo3(LocalDate date, Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "select b.geo_1 as geo1, " +
                    "b.geo_2 as geo2, " +
                    "avg(b.avg_pyeong_price) as avgp, " +
                    "count(b.geo_1) as transcount " +
                    "from boo b " +
                    "where b.apt_trans_date >= :date and b.geo_1 = :geo1Name " +
                    "group by b.geo_2",
            countQuery = "select b.geo_1 as geo1, " +
                    "b.geo_2 as geo2, " +
                    "avg(b.avg_pyeong_price) as avgp, " +
                    "count(b.geo_1) as transcount " +
                    "from boo b " +
                    "where b.apt_trans_date >= :date and b.geo_1 = :geo1Name " +
                    "group by b.geo_2"
    )
    Page<AvgpResponse> avgPriceGroupByGeo1Name(String geo1Name, LocalDate date, Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "select b.geo_1 as geo1, " +
                    "b.geo_2 as geo2, " +
                    "b.geo_3 as geo3, " +
                    "avg(b.avg_pyeong_price) as avgp, " +
                    "count(b.geo_3) as transcount " +
                    "from boo b " +
                    "where b.apt_trans_date >= :date and b.geo_1 = :geo1Name and b.geo_2 = :geo2Name " +
                    "group by b.geo_3 ",
            countQuery = "select b.geo_1 as geo1, " +
                    "b.geo_2 as geo2, " +
                    "b.geo_3 as geo3, " +
                    "avg(b.avg_pyeong_price) as avgp, " +
                    "count(b.geo_3) as transcount " +
                    "from boo b " +
                    "where b.apt_trans_date >= :date and b.geo_1 = :geo1Name and b.geo_2 = :geo2Name " +
                    "group by b.geo_3 "
    )
    Page<AvgpResponse> avgPriceGroupByGeo2Name(String geo1Name, String geo2Name, LocalDate date, Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "select b.geo_1 as geo1, " +
                    "b.geo_2 as geo2, " +
                    "b.geo_3 as geo3, " +
                    "b.apt_name as aptName, " +
                    "avg(b.avg_pyeong_price) as avgp, " +
                    "count(b.apt_name) as transcount " +
                    "from boo b " +
                    "where b.apt_trans_date >= :date and b.geo_1 = :geo1Name and b.geo_2 = :geo2Name and b.geo_3 = :geo3Name " +
                    "group by b.apt_name ",
            countQuery = "select b.geo_1 as geo1, " +
                    "b.geo_2 as geo2, " +
                    "b.geo_3 as geo3, " +
                    "b.apt_name as aptName, " +
                    "avg(b.avg_pyeong_price) as avgp, " +
                    "count(b.apt_name) as transcount " +
                    "from boo b " +
                    "where b.apt_trans_date >= :date and b.geo_1 = :geo1Name and b.geo_2 = :geo2Name and b.geo_3 = :geo3Name " +
                    "group by b.apt_name "
    )
    Page<AvgpResponse> avgPriceGroupByGeo3Name(String geo1Name, String geo2Name, String geo3Name, LocalDate date, Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "select b.geo_1 as geo1, " +
                    "b.geo_2 as geo2, " +
                    "b.geo_3 as geo3, " +
                    "b.apt_name as aptName, " +
                    "avg(b.apt_price) as avgp " +
                    "from boo b " +
                    "where b.apt_trans_date >= :date and b.apt_area_pyeong >= :area and b.apt_area_pyeong < :area + 10 and b.geo_1 = :geo1Name " +
                    "group by b.geo_1, b.geo_2, b.geo_3, b.apt_name"
    )
    Page<AvgpResponse> avgApartPriceGroupByGeo1Name(double area, String geo1Name, LocalDate date, Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "select b.geo_1 as geo1, " +
                    "b.geo_2 as geo2, " +
                    "b.geo_3 as geo3, " +
                    "b.apt_name as aptName, " +
                    "avg(b.apt_price) as avgp " +
                    "from boo b " +
                    "where b.apt_trans_date >= :date and b.apt_area_pyeong >= :area and b.apt_area_pyeong < :area + 10 and b.geo_1 = :geo1Name and b.geo_2 = :geo2Name " +
                    "group by b.geo_1, b.geo_2, b.geo_3, b.apt_name"
    )
    Page<AvgpResponse> avgApartPriceGroupByGeo2Name(double area, String geo1Name, String geo2Name, LocalDate date, Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "select b.geo_1 as geo1, " +
                    "b.geo_2 as geo2, " +
                    "b.geo_3 as geo3, " +
                    "b.apt_name as aptName, " +
                    "avg(b.apt_price) as avgp " +
                    "from boo b " +
                    "where b.apt_trans_date >= :date and b.apt_area_pyeong >= :area and b.apt_area_pyeong < :area + 10 and b.geo_1 = :geo1Name and b.geo_2 = :geo2Name and b.geo_3 = :geo3Name " +
                    "group by b.geo_1, b.geo_2, b.geo_3, b.apt_name"
    )
    Page<AvgpResponse> avgApartPriceGroupByGeo3Name(double area, String geo1Name, String geo2Name, String geo3Name, LocalDate date, Pageable pageable);

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

    @Query(
            nativeQuery = true,
            value = "select b.geo_1 as geo1, " +
                    "avg(b.apt_price) as avgp " +
                    "from boo b " +
                    "where b.apt_trans_date > :date and b.apt_area_pyeong between 20 and 30 " +
                    "group by geo_1"
    )
    List<AvgpResponse> getAvg20Prices0(LocalDate date);

    @Query(
            nativeQuery = true,
            value = "select b.geo_1 as geo1, " +
                    "b.geo_2 as geo2, " +
                    "avg(b.apt_price) as avgp " +
                    "from boo b " +
                    "where b.apt_trans_date > :date and b.geo_1 = :geo1 and b.apt_area_pyeong between 20 and 30 " +
                    "group by b.geo_1, b.geo_2"
    )
    List<AvgpResponse> getAvg20Prices1(LocalDate date, String geo1);

    @Query(
            nativeQuery = true,
            value = "select b.geo_1 as geo1, " +
                    "b.geo_2 as geo2, " +
                    "b.geo_3 as geo3, " +
                    "avg(b.apt_price) as avgp " +
                    "from boo b " +
                    "where b.apt_trans_date > :date and b.geo_1 = :geo1 and b.geo_2 = :geo2 and b.apt_area_pyeong between 20 and 30 " +
                    "group by b.geo_1, b.geo_2, b.geo_3"
    )
    List<AvgpResponse> getAvg20Prices2(LocalDate date, String geo1, String geo2);

    @Query(
            nativeQuery = true,
            value = "select b.geo_1 as geo1, " +
                    "b.geo_2 as geo2, " +
                    "b.geo_3 as geo3, " +
                    "b.apt_name as aptName, " +
                    "avg(b.apt_price) as avgp " +
                    "from boo b " +
                    "where b.apt_trans_date > :date and b.geo_1 = :geo1 and b.geo_2 = :geo2 and b.geo_3 = :geo3 and b.apt_area_pyeong between 20 and 30 " +
                    "group by b.geo_1, b.geo_2, b.geo_3, b.apt_name"
    )
    List<AvgpResponse> getAvg20Prices3(LocalDate date, String geo1, String geo2, String geo3);

}

