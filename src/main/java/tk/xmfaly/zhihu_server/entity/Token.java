package tk.xmfaly.zhihu_server.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Token {


    @Id
    @GeneratedValue
    private int id;

    private String t;
}
