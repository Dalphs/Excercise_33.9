package sample;

import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ServerController implements ServerRead.ServerReadListener {

    public TextArea serverClientTextArea;
    public TextArea serverServerTextArea;
    private ServerRead serverRead;
    private ServerWrite serverWrite;

    public void initialize(){
        serverClientTextArea.setEditable(false);
        serverRead = new ServerRead(this);
        serverWrite = new ServerWrite();
        System.out.println("Serverobject oprettet");
        new Thread(serverWrite).start();
        new Thread(serverRead).start();
        System.out.println("Forbindlese opnået");
        serverServerTextArea.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER){
                    serverWrite.write(serverServerTextArea.getText());
                    serverServerTextArea.clear();
                }
            }
        });
    }

    @Override
    public void messageReceived(String message) {
        serverClientTextArea.appendText(message);
    }
}
