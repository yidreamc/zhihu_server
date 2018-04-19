package tk.xmfaly.zhihu_server.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "zhihu_server_equipment")
public class Equipment {

    @Id
    private String id;

    private String pwd;

    @OneToOne(mappedBy = "equipment")
    private UserInfo userInfo;

    public Equipment(String id, UserInfo userInfo) {
        this.id = id;
        this.userInfo = userInfo;
    }

    public Equipment() {
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
