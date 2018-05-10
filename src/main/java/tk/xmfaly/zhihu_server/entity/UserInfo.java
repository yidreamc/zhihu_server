package tk.xmfaly.zhihu_server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "zhihu_server_user_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String userName;

    @JsonIgnore
    private String passWord;

    private String tel;

    private int age;

    private String addr;

    private String remarks;

    @OneToOne
    @JoinTable(name = "user_equipment", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "equipment_id"))
    private Equipment equipment;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")})
    @JsonIgnore
    private List<Authority> authorities = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "userinfo",fetch=FetchType.EAGER)
    private Set<Fence> fences = new HashSet<>();

    public UserInfo(String userName, String passWord, String tel, int age, String addr, String remarks, Equipment equipment, List<Authority> authorities, Set<Fence> fences) {
        this.userName = userName;
        this.passWord = passWord;
        this.tel = tel;
        this.age = age;
        this.addr = addr;
        this.remarks = remarks;
        this.equipment = equipment;
        this.authorities = authorities;
        this.fences = fences;
    }

    public UserInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public Set<Fence> getFences() {
        return fences;
    }

    public void setFences(Set<Fence> fences) {
        this.fences = fences;
    }
}
