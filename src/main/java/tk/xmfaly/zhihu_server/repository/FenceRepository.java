package tk.xmfaly.zhihu_server.repository;

import org.springframework.data.repository.CrudRepository;
import tk.xmfaly.zhihu_server.entity.Fence;

public interface FenceRepository extends CrudRepository<Fence,Integer>{

    Fence findByUserinfoId(int id);
}
