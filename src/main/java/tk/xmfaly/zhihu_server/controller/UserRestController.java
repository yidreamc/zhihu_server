package tk.xmfaly.zhihu_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tk.xmfaly.zhihu_server.dto.Response;
import tk.xmfaly.zhihu_server.entity.Equipment;
import tk.xmfaly.zhihu_server.entity.Fence;
import tk.xmfaly.zhihu_server.entity.FencePoint;
import tk.xmfaly.zhihu_server.entity.UserInfo;
import tk.xmfaly.zhihu_server.repository.EquipmentRepository;
import tk.xmfaly.zhihu_server.repository.FencePointRepository;
import tk.xmfaly.zhihu_server.repository.FenceRepository;
import tk.xmfaly.zhihu_server.repository.UserInfoRepository;
import tk.xmfaly.zhihu_server.security.JwtTokenUtil;
import tk.xmfaly.zhihu_server.service.upload.FileUploadService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private FenceRepository fenceRepository;

    @Autowired
    private FencePointRepository fencePointRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private FileUploadService fileUploadService;

    String host = "http://120.78.149.248:8080/";

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Response getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        UserInfo user = userInfoRepository.findByUserName(username);
        Equipment equipment = equipmentRepository.findFirstByUserInfoId(user.getId());

        Fence fence = fenceRepository.findByUserinfoId(user.getId());

        Iterable<FencePoint> fencePoints;
        if(fence != null){
            fencePoints = fencePointRepository.findByFenceId(fence.getId());
        }else {
            fencePoints = null;
        }

        Map<String,Object> res = new HashMap<>();
        res.put("id",user.getId());
        res.put("userName",user.getUserName());
        res.put("tel",user.getTel());
        res.put("age",user.getAge());
        res.put("addr",user.getAddr());
        res.put("remark",user.getRemarks());
        res.put("fence",fencePoints);
        res.put("equipment",equipment);
        res.put("height",user.getHeight());
        res.put("weight",user.getWeight());
        res.put("avatar",user.getAvatar());


        return new Response(0,"success",res);
    }

    @RequestMapping(value = "/user2", method = RequestMethod.GET)
    public Response getuser(String eid) {

        Equipment equipment = equipmentRepository.findById(eid);
        int uid = equipment.getUserInfoId();
        UserInfo user = userInfoRepository.findOne(uid);

        Fence fence = fenceRepository.findByUserinfoId(user.getId());

        Iterable<FencePoint> fencePoints;
        if(fence != null){
            fencePoints = fencePointRepository.findByFenceId(fence.getId());
        }else {
            fencePoints = null;
        }

        Map<String,Object> res = new HashMap<>();
        res.put("id",user.getId());
        res.put("userName",user.getUserName());
        res.put("tel",user.getTel());
        res.put("age",user.getAge());
        res.put("addr",user.getAddr());
        res.put("remark",user.getRemarks());
        res.put("fence",fencePoints);
        res.put("equipment",equipment);
        res.put("height",user.getHeight());
        res.put("weight",user.getWeight());
        res.put("avatar",user.getAvatar());


        return new Response(0,"success",res);
    }

    //头像、年龄、体重、身高、地址、手机、备注
    @PostMapping("/updateUserInfo")
    public Response updateUserInfo(MultipartFile avatar, int age, String weight, String height, String addr, String phone, String remark,int uid){
        try {
            UserInfo userInfo = userInfoRepository.findOne(uid);
            if(userInfo == null){
                return new Response(2,"用户不存在");
            }
            userInfo.setTel(phone);
            userInfo.setAge(age);
            userInfo.setWeight(weight);
            userInfo.setHeight(height);
            userInfo.setAddr(addr);
            userInfo.setRemarks(remark);
            String name = fileUploadService.upload(avatar);
            userInfo.setAvatar(host + name);
            userInfoRepository.save(userInfo);

            return new Response(0,"保存成功");

        }catch (Exception e){
            return new Response(-1,e.getMessage());
        }


    }

}
