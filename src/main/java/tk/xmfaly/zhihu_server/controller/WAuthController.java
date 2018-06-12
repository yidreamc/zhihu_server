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
import tk.xmfaly.zhihu_server.service.TestMessage;

import java.util.Random;

@RestController
@RequestMapping("/wauth")
public class WAuthController {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private TestMessage testMessage;

    @GetMapping("/wIsOurUser")
    public Response wIsourUser(String wid){

        if(wid == null||"".equals(wid)){
            return new Response(3,"wid不能为空");
        }
        UserInfo userInfo = userInfoRepository.findFirstByWid(wid);

        //是我们的用户
        if(userInfo != null){
            String token = jwtTokenUtil.generateToken(userInfo);
            return new Response(0,"",token);
        }else {
            return new Response(1,"没有使用qq登陆过");
        }
    }

    @PostMapping("/wreg")
    public Response qreg(String tel,String wid,String uname,String code,String pwd){

        UserInfo userInfo = userInfoRepository.findByTel(tel);
        if (userInfo != null) {
            return new Response(1, "手机号已经被注册！");
        }

        if(!testMessage.testMessage(uname,code)){
            return new Response(10008,"验证码错误");
        }
        userInfo = new UserInfo();
        userInfo.setUserName(uname);
        userInfo.setTel(tel);
        userInfo.setWid(wid);
        userInfo.setPassWord(new BCryptPasswordEncoder().encode(pwd));
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
