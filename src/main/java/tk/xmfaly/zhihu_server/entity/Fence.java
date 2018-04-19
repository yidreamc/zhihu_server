package tk.xmfaly.zhihu_server.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "zhihu_server_fence")
public class Fence {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userinfo_id")
    private UserInfo userinfo;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "fence",fetch=FetchType.EAGER)
    private Set<FencePoint> fencePoints = new HashSet<>();

    public Fence(UserInfo userinfo, Set<FencePoint> fencePoints) {
        this.userinfo = userinfo;
        this.fencePoints = fencePoints;
    }

    public Fence() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserInfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserInfo userinfo) {
        this.userinfo = userinfo;
    }

    public Set<FencePoint> getFencePoints() {
        return fencePoints;
    }

    public void setFencePoints(Set<FencePoint> fencePoints) {
        this.fencePoints = fencePoints;
    }
}
