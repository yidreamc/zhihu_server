package tk.xmfaly.zhihu_server.repository;

import org.springframework.data.repository.CrudRepository;
import tk.xmfaly.zhihu_server.entity.Exception;

public interface ExceptionRepository extends CrudRepository<Exception,Integer>{
}
