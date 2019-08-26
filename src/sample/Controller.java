package sample;

import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller implements ClientRead.ClientReadListener {

    public TextArea clientServerTextArea;
    public TextArea clientClientTextArea;
    private ClientWrite clientWrite;
    private ClientRead clientRead;

    public void initialize(){
        clientServerTextArea.setEditable(false);
        clientWrite = new ClientWrite();
        clientRead = new ClientRead(this);
        new Thread(clientRead).start();
        clientClientTextArea.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER) {
                    clientWrite.setMessage(clientClientTextArea.getText());
                    clientWrite.run();
                    clientClientTextArea.clear();
                }
            }
        });
    }

    @Override
    public void messageReceived(String message) {
        clientServerTextArea.appendText(message);
    }
}
