package tk.xmfaly.zhihu_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.xmfaly.zhihu_server.dto.Response;
import tk.xmfaly.zhihu_server.entity.UserInfo;
import tk.xmfaly.zhihu_server.repository.UserInfoRepository;
import tk.xmfaly.zhihu_server.security.JwtTokenUtil;

import java.util.Random;

@RestController
@RequestMapping("/qauth")
public class QAuthController {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("/qIsOurUser")
    public Response qIsourUser(String openId){

        if(openId == null||"".equals(openId)){
            return new Response(3,"openId不能为空");
        }
        UserInfo userInfo = userInfoRepository.findFirstByQOpenId(openId);

        //是我们的用户
        if(userInfo != null){
            String token = jwtTokenUtil.generateToken(userInfo);
            return new Response(0,"",token);
        }else {
            return new Response(1,"没有使用qq登陆过");
        }
    }

    @PostMapping("/qreg")
    public Response qreg(String tel,String openId,String uname){

        UserInfo userInfo = userInfoRepository.findByTel(tel);
        if (userInfo != null) {
            return new Response(1, "手机号已经被注册！");
        }
        userInfo = new UserInfo();
        userInfo.setUserName(uname + getRandomString(5));
        userInfo.setTel(tel);
        userInfo.setqOpenId(openId);
        try {
            userInfoRepository.save(userInfo);
            String token = jwtTokenUtil.generateToken(userInfo);
            return new Response(0,"注册成功",token);
        } catch (Exception e) {
            return new Response(2, "注册失败！");
        }
    }

    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
