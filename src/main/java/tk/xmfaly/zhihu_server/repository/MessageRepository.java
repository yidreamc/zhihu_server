package tk.xmfaly.zhihu_server.repository;

import org.springframework.data.repository.CrudRepository;
import tk.xmfaly.zhihu_server.entity.Message;

import java.util.Collection;

public interface MessageRepository extends CrudRepository<Message,Integer> {
    Message findFirstByUsername(String username);
    Iterable<Message> findByUsername(String username);
    Collection<Message> findByUsernameAndCode(String username, String code);
}
