package tk.xmfaly.zhihu_server.service;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import tk.xmfaly.zhihu_server.entity.*;
import tk.xmfaly.zhihu_server.repository.EquipmentRepository;
import tk.xmfaly.zhihu_server.repository.FencePointRepository;
import tk.xmfaly.zhihu_server.repository.FenceRepository;
import tk.xmfaly.zhihu_server.repository.UserInfoRepository;
import tk.xmfaly.zhihu_server.uitl.PolyUtils;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Component("location")
@Scope("prototype")
@Log
public class LocalcationService extends Thread {

    public interface OnDisconnectedListener {
        void onDisonnected(LocalcationService connection);
    }

    private Socket socket;
    private OnDisconnectedListener listener;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private FenceRepository fenceRepository;

    @Autowired
    private FencePointRepository fencePointRepository;

    @Override
    public void run() {
        socket = SockService.getSocket();
        while (socket.isConnected()) {
            InputStream in;
            try {
                in = socket.getInputStream();
                if (in.available() > 0) {
                    OutputStream out = socket.getOutputStream();
                    byte[] recData = new byte[1024];
                    in.read(recData);
                    String data = new String(recData);

                    String[] locations = data.split(",");
                    String eid = locations[0];
                    String timestamp = locations[1];
                    String longitude = locations[2];
                    String latitude = locations[3];
                    Location location = new Location(eid, timestamp, longitude, latitude);
                    mongoTemplate.save(location);


                    //根据设备id找到用户
                    Equipment equipment = equipmentRepository.findById(eid);
                    if (equipment != null) {

                        log.info("设备: " + eid);
                        UserInfo userInfo = userInfoRepository.findOne(equipment.getUserInfoId());
                        if (userInfo != null) {
                            log.info("用户: " + userInfo.getId());

                            //获取围栏
                            Fence fence = fenceRepository.findByUserinfoId(userInfo.getId());

                            //获取围栏点
                            Iterable<FencePoint> fencePoints = fencePointRepository.findByFenceId(fence.getId());


                            List<Point2D.Double> pts = new ArrayList<>();

                            Iterator<FencePoint> fencePointIterator = fencePoints.iterator();
                            while (fencePointIterator.hasNext()) {
                                FencePoint fencePoint = fencePointIterator.next();
                                Point2D.Double point = new Point2D.Double(Double.valueOf(fencePoint.getLongitude()), Double.valueOf(fencePoint.getLatitude()));
                                pts.add(point);
                            }
                            Boolean isFence = PolyUtils.IsPtInPoly(new Point2D.Double(Double.valueOf(longitude), Double.valueOf(latitude)), pts);
                            if (isFence == false) {
                                log.info("用户走出围栏");
                                break;
                            } else {
                                log.info("未出围栏");
                            }
                        }
                    }

                    out.write(("Server received:" + data + "\r\n").getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        listener.onDisonnected(this);
    }
}