package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientRead implements Runnable{
    private int port = 8001;
    private DataInputStream in;
    private DataOutputStream out;
    private Socket socket;
    private String host = "localhost";
    private ClientReadListener listener;

    public interface ClientReadListener{
        void messageReceived(String message);
    }

    public ClientRead(ClientReadListener listener) {
        this.listener = listener;
        try {
            socket = new Socket(host, port);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true) {
            try {
                String message = in.readUTF();
                listener.messageReceived(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
