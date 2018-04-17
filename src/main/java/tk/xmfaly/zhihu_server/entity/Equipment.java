package tk.xmfaly.zhihu_server.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "zhihu_server_equipment")
@Data
@NoArgsConstructor
public class Equipment {

    @Id
    private String id;

    @OneToOne(mappedBy = "equipment")
    private UserInfo userInfo;

}
