package tk.xmfaly.zhihu_server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "zhihu_server_user_info")
@Data
@NoArgsConstructor
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
    private List<Authority> authorities = new ArrayList<>();


}
