package tk.xmfaly.zhihu_server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.ServerSocket;
import java.net.Socket;

@Component
public class SockService {

    private static Socket socket = new Socket();

    @Autowired
    private ServerSocket serverSocket;

    public static Socket getSocket() {
        return socket;
    }

    public static void setSocket(Socket socket) {
        SockService.socket = socket;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
}
