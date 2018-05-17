package tk.xmfaly.zhihu_server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.xmfaly.zhihu_server.entity.Message;
import tk.xmfaly.zhihu_server.repository.MessageRepository;

import java.util.Collection;


@Service
public class TestMessage {

    @Autowired
    private MessageRepository messageRepository;

    public Boolean testMessage(String username,String code){
        Collection<Message> messages = messageRepository.findByUsernameAndCode(username,code);
        if(!messages.isEmpty()){
            return true;
        }
        return false;
    }
}
