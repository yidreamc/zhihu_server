package tk.xmfaly.zhihu_server.repository;

import org.springframework.data.repository.CrudRepository;
import tk.xmfaly.zhihu_server.entity.Token;

public interface TokenRepository extends CrudRepository<Token,Integer>{
}
