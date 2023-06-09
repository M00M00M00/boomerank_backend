package boomerank.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "junse")
public class Junse {
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
    @Column(name = "apt_area_pyeong")
    private int aptAreaPyeong;
    @Column(name = "avg_pyeong_price")
    private double avgPyeongPrice;


    public String toString(){
        return geo1 + geo2 + aptName + ":" + aptPrice;
    }
}
