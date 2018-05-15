package tk.xmfaly.zhihu_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.xmfaly.zhihu_server.dto.Response;
import tk.xmfaly.zhihu_server.entity.Equipment;
import tk.xmfaly.zhihu_server.entity.UserInfo;
import tk.xmfaly.zhihu_server.repository.EquipmentRepository;
import tk.xmfaly.zhihu_server.repository.UserInfoRepository;

import java.util.Map;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @PostMapping("/bind")
    public Response bind(String uid,String eid){
        UserInfo userInfo = userInfoRepository.findOne(Integer.valueOf(uid));
        Equipment equipment = equipmentRepository.findById(eid);
        if(userInfo ==null || equipment==null){
            return new Response(10001,"用户名或设备id不存在");
        }
        try {
            equipment.setUserInfoId(userInfo.getId());
            equipmentRepository.save(equipment);
            return new Response(0,"绑定成功",equipment.getPwd());
        }catch (Exception e){
            return new Response(10002,"绑定失败");
        }
    }

}
