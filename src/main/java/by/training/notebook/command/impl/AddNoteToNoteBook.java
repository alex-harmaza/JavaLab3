package by.training.notebook.command.impl;

import by.training.notebook.bean.Request;
import by.training.notebook.bean.RequestWithNoteContent;
import by.training.notebook.bean.Response;
import by.training.notebook.bean.ResponseWithMessage;
import by.training.notebook.bean.entity.Note;
import by.training.notebook.command.ICommand;
import by.training.notebook.command.exception.CommandException;
import by.training.notebook.source.NoteBookProvider;

import java.util.Date;

/**
 * Created by Aliaksandr_Harmaza on 9/27/2016.
 */
public class AddNoteToNoteBook implements ICommand {

    @Override
    public Response execute(Request request) throws CommandException {
        if (request == null || request.getClass() != RequestWithNoteContent.class){
            throw new CommandException("The request does not AddNoteToNoteBookRequest the class");
        }

        RequestWithNoteContent temp = (RequestWithNoteContent) request;
        NoteBookProvider.getInstance().getNoteBook().add(new Note(new Date(), temp.getContent()));
        return new ResponseWithMessage(true, "Note added");
    }

}
