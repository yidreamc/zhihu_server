package tk.xmfaly.zhihu_server.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "zhihu_server_equipment")
public class Equipment {

    @Id
    private String id;

    private String pwd;

    private int userInfoId;


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

    public int getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(int userInfoId) {
        this.userInfoId = userInfoId;
    }
}
