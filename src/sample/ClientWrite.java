package sample;

import javax.management.remote.JMXServerErrorException;
import java.io.*;
import java.net.Socket;

public class ClientWrite implements Runnable{
    private int port = 8000;
    private DataOutputStream out;
    private DataInputStream in;
    private Socket socket;
    private String host = "localhost";
    private String message;

    public ClientWrite() {
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
        try {
            out.writeUTF(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
