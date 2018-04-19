package tk.xmfaly.zhihu_server.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "zhihu_server_fence_point")
public class FencePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //经度
    private String longitude;

    //纬度
    private String latitude;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fence_id")
    private Fence fence;

    public FencePoint(String longitude, String latitude, Fence fence) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.fence = fence;
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

    public Fence getFence() {
        return fence;
    }

    public void setFence(Fence fence) {
        this.fence = fence;
    }
}
