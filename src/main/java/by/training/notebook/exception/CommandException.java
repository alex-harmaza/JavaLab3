package by.training.notebook.exception;

/**
 * Created by Aliaksandr_Harmaza on 9/27/2016.
 */
public class CommandException extends Exception {

    public CommandException(String message){
        super(message);
    }

    public CommandException(String message, Throwable cause){
        super(message, cause);
    }

}
