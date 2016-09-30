package by.training.notebook.controller;

import by.training.notebook.bean.Request;
import by.training.notebook.bean.Response;
import by.training.notebook.bean.ResponseWithMessage;
import by.training.notebook.command.ICommand;
import by.training.notebook.command.exception.CommandException;

/**
 * Created by Aliaksandr_Harmaza on 9/27/2016.
 */
public class Controller {

    private CommandFactory commandFactory;


    public Controller(){
        commandFactory = new CommandFactory();
    }


    public Response doRequest(Request request){
        ICommand command = commandFactory.getCommand(request.getCommandName());
        Response response = null;

        if (command != null){
            try {
                response = command.execute(request);
            }
            catch (CommandException ex){
                response = new ResponseWithMessage(false, ex.getMessage());
            }
        }
        else {
            response = new ResponseWithMessage(false, "Command not found");
        }

        return response;
    }

}
