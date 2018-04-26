package tk.xmfaly.zhihu_server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.xmfaly.zhihu_server.service.LocalcationService;
import tk.xmfaly.zhihu_server.uitl.ApplicationContextProvider;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ZhihuServerApplication {


	@Autowired
	private ServerSocket serverSocket;

	public static void main(String[] args) {
		SpringApplication.run(ZhihuServerApplication.class, args);
	}

}
