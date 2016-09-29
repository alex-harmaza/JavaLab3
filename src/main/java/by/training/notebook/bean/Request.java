package by.training.notebook.bean;

import by.training.notebook.CommandEnum;

/**
 * Created by Aliaksandr_Harmaza on 9/27/2016.
 */
public class Request {

    private CommandEnum commandName;


    public Request(CommandEnum commandName){
        this.commandName = commandName;
    }


    public CommandEnum getCommandName() {
        return commandName;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Request{commandName=")
                .append(commandName).append("}").toString();
    }
}
