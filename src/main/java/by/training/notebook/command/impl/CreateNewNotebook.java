package by.training.notebook.command.impl;

import by.training.notebook.bean.Request;
import by.training.notebook.bean.Response;
import by.training.notebook.bean.ResponseWithMessage;
import by.training.notebook.command.ICommand;
import by.training.notebook.command.exception.CommandException;
import by.training.notebook.source.NoteBookProvider;

/**
 * Created by Aliaksandr_Harmaza on 9/27/2016.
 */
public class CreateNewNotebook implements ICommand {

    public Response execute(Request request) throws CommandException {
        if (request.getClass() != Request.class ){
            throw new CommandException("Incorrect request class");
        }
        NoteBookProvider.getInstance().getNoteBook().clear();
        return new ResponseWithMessage(true, "Notebook created");
    }

}
