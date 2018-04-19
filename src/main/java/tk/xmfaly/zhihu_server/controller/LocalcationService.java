package tk.xmfaly.zhihu_server.controller;

import com.mongodb.Mongo;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import tk.xmfaly.zhihu_server.entity.Location;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class LocalcationService extends Thread {

    public interface OnDisconnectedListener{
        void onDisonnected(LocalcationService connection);
    }

    private Socket socket;
    private String ip;
    private OnDisconnectedListener listener;

    public LocalcationService(Socket s, String ip, OnDisconnectedListener listener) {
        this.socket = s;
        this.ip = ip;
        this.listener = listener;
    }

    @Override
    public void run() {
        while(socket.isConnected()){
            InputStream in;
            try {
                in = socket.getInputStream();
                if(in.available() > 0){
                    OutputStream out = socket.getOutputStream();
                    byte[] recData = new byte[1024];
                    in.read(recData);
                    String data = new String(recData);

                    String[] locations = data.split(",");
                    String uid = locations[0];
                    String timestamp = locations[1];
                    String longitude = locations[2];
                    String latitude = locations[3];


                    MongoTemplate mongoTemplate = new MongoTemplate(new Mongo("120.78.149.248"),"zhihu_server");

                    Location location = new Location(uid,timestamp,longitude,latitude);
                    mongoTemplate.save(location);


                    System.out.println("Read from " + ip + " :" + data);
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
        System.out.println(ip + " disconnected.");
    }
}