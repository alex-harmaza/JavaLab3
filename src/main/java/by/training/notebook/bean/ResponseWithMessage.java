package by.training.notebook.bean;

/**
 * Created by Aliaksandr_Harmaza on 9/27/2016.
 */
public class ResponseWithMessage extends Response {

    private String message;


    public ResponseWithMessage(){
        super();
    }

    public ResponseWithMessage(boolean status, String message){
        super(status);
        setMessage(message);
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        if (message == null || message.trim().isEmpty()){
            throw new IllegalArgumentException("Incorrect message");
        }
        this.message = message.trim();
    }
}
