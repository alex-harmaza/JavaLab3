package by.training.notebook.command.impl;

import by.training.notebook.CommandEnum;
import by.training.notebook.ConfigProperties;
import by.training.notebook.bean.Request;
import by.training.notebook.bean.Response;
import by.training.notebook.bean.ResponseWithMessage;
import by.training.notebook.command.ICommand;
import by.training.notebook.exception.CommandException;

/**
 * Created by alexh on 29.09.2016.
 */
public class ShowHelp implements ICommand {

    @Override
    public Response execute(Request request) throws CommandException {
        if (request.getClass() != Request.class){
            throw new CommandException("Incorrect request type");
        }

        StringBuilder builder = new StringBuilder();
        for (CommandEnum e : CommandEnum.values()){
            builder.append(ConfigProperties.getInstance().getProperty(e.name())).append("\n");
        }

        return new ResponseWithMessage(true, builder.toString());
    }
}
