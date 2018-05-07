package tk.xmfaly.zhihu_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.xmfaly.zhihu_server.dto.Response;
import tk.xmfaly.zhihu_server.entity.UserInfo;
import tk.xmfaly.zhihu_server.repository.UserInfoRepository;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @PostMapping("/reg")
    public Response reg(@RequestBody Map<String, String> param) {
        String uname = param.get("uname");
        String pwd = param.get("pwd");
        String tel = param.get("tel");
        UserInfo userInfo = userInfoRepository.findByTel(tel);
        if (userInfo != null) {
            return new Response(1, "手机号已经被注册！");
        }
        userInfo = new UserInfo();
        userInfo.setPassWord(new BCryptPasswordEncoder().encode(pwd));
        userInfo.setUserName(uname);
        userInfo.setTel(tel);
        try {
            userInfoRepository.save(userInfo);
            return new Response(0, "注册成功！");
        } catch (Exception e) {
            return new Response(2, "注册失败！");
        }
    }
}
