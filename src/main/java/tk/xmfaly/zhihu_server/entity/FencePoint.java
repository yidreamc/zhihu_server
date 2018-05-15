package tk.xmfaly.zhihu_server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "zhihu_server_fence_point")
public class FencePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private int id;

    //经度
    private String longitude;

    //纬度
    private String latitude;

    @JsonIgnore
    private int fenceId;

    public FencePoint(String longitude, String latitude, int fenceId) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.fenceId = fenceId;
    }

    public FencePoint() {
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

    public int getFenceId() {
        return fenceId;
    }

    public void setFenceId(int fenceId) {
        this.fenceId = fenceId;
    }
}
