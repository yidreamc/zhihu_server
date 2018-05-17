package tk.xmfaly.zhihu_server.repository;

import org.springframework.data.repository.CrudRepository;
import tk.xmfaly.zhihu_server.entity.Equipment;
import tk.xmfaly.zhihu_server.entity.UserInfo;

public interface UserInfoRepository extends CrudRepository<UserInfo,Integer>{

    UserInfo findByUserName(String username);

    UserInfo findByTel(String tel);

    UserInfo findFirstByUserName(String username);

    UserInfo findFirstByTel(String tel);
}
