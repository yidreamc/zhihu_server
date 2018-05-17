package tk.xmfaly.zhihu_server.controller;

import com.aliyuncs.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.xmfaly.zhihu_server.dto.Response;
import tk.xmfaly.zhihu_server.entity.Message;
import tk.xmfaly.zhihu_server.entity.UserInfo;
import tk.xmfaly.zhihu_server.repository.MessageRepository;
import tk.xmfaly.zhihu_server.repository.UserInfoRepository;
import tk.xmfaly.zhihu_server.service.SendMessageService;

import java.util.Iterator;

@RestController
public class MessageController {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private SendMessageService sendMessageService;

    @Autowired
    private MessageRepository messageRepository;


    @PostMapping("/getCode")
    public Object getCode(String phone,String uname) throws ClientException {
        UserInfo userInfo = userInfoRepository.findByUserName(uname);
        if(userInfo != null){
            return new Response(10006,"用户名已存在");
        }
        userInfo = userInfoRepository.findFirstByTel(phone);
        if(userInfo != null){
            return new Response(10007,"该手机号已经被注册");
        }

        String code = sendMessageService.gencode();
        String mes = sendMessageService.sendMessage(phone, code);
        if (mes.equals("OK") == false) {
            return new Response(10008,mes);
        }

        //删除已经有的验证码 防止查找异常
        Iterable<Message> messages = messageRepository.findByUsername(uname);
        Iterator<Message> messageIterator = messages.iterator();
        while(messageIterator.hasNext()){
            Message message = messageIterator.next();
            messageRepository.delete(message);
        }

        Message message = new Message(uname,code);
        messageRepository.save(message);
        return new Response(0, "发送成功");

    }
}
