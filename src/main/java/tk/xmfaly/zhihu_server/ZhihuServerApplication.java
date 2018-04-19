package tk.xmfaly.zhihu_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.xmfaly.zhihu_server.controller.LocalcationService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ZhihuServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZhihuServerApplication.class, args);

		//tcp
		int count = 1;
		List<LocalcationService> list = new ArrayList<>();
		LocalcationService.OnDisconnectedListener listener = new LocalcationService.OnDisconnectedListener() {
			@Override
			public void onDisonnected(LocalcationService connection) {
				list.remove(connection);
			}
		};

		try {
			ServerSocket ss = new ServerSocket(8082);
			System.out.println("Server start, listening...");
			while (true) {
				Socket s = ss.accept();
				LocalcationService t = new LocalcationService(s, s.getInetAddress().toString(), listener);
				count++;
				list.add(t);
				t.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
