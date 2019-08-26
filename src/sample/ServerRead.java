package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRead implements Runnable {
    private int port = 8000;
    private DataInputStream in;
    private DataOutputStream out;
    private Socket socket;
    private ServerSocket serverSocket;
    private ServerReadListener listener;

    public interface ServerReadListener{
        void messageReceived(String message);
    }

    public ServerRead(ServerReadListener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        try {
        serverSocket = new ServerSocket(port);
        socket = serverSocket.accept();
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());
        while(true){
                String msg = in.readUTF();
                listener.messageReceived(msg);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
