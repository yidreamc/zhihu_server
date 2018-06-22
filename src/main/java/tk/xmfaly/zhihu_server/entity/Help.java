package tk.xmfaly.zhihu_server.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Help {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //经度
    private String longitude;

    //纬度
    private String latitude;

    private String detail;

    public Help() {
    }

    public Help( String longitude, String latitude, String detail) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
