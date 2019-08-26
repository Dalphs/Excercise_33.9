package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerWrite implements Runnable {
    private int port = 8001;
    private DataOutputStream out;
    private DataInputStream in;
    private Socket socket;
    private ServerSocket serverSocket;

    public ServerWrite() {
    }

    @Override
    public void run() {
        System.out.println(socket == null);
        if(socket == null) {
            try {
                serverSocket = new ServerSocket(port);
                System.out.println("Venter på forbindelse");
                socket = serverSocket.accept();
                System.out.println("Forbindelse oprettet");
                out = new DataOutputStream(socket.getOutputStream());
                in = new DataInputStream(socket.getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void write(String message){
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
