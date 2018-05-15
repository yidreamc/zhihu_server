package tk.xmfaly.zhihu_server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "zhihu_server_fence")
public class Fence {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private int id;

    private int userinfoId;


    public Fence() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserinfoId() {
        return userinfoId;
    }

    public void setUserinfoId(int userinfoId) {
        this.userinfoId = userinfoId;
    }
}
