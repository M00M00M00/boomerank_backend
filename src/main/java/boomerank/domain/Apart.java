package boomerank.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "boo")
public class Apart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "geo_1")
    private String geo1;
    @Column(name = "geo_2")
    private String geo2;
    @Column(name = "geo_3")
    private String geo3;
    @Column(name = "apt_name")
    private String aptName;
    @Column(name = "apt_trans_date")
    private LocalDate aptTransDate;
    @Column(name = "apt_price")
    private int aptPrice;
    @Column(name = "apt_area")
    private double aptArea;
    @Column(name = "apt_area_pyeong")
    private int aptAreaPyeong;
    @Column(name = "apt_year")
    private int aptYear;
    @Column(name = "apt_floor")
    private int aptFloor;
    @Column(name = "avg_pyeong_price")
    private double avgPyeongPrice;

    public String toString(){
        return geo1 + geo2 + aptName + ":" + aptPrice;
    }
}

