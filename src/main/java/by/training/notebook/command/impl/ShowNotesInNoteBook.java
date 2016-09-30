package by.training.notebook.command.impl;

import by.training.notebook.bean.Request;
import by.training.notebook.bean.Response;
import by.training.notebook.bean.ResponseWithNoteArray;
import by.training.notebook.bean.entity.Note;
import by.training.notebook.bean.entity.NoteBook;
import by.training.notebook.command.ICommand;
import by.training.notebook.exception.CommandException;
import by.training.notebook.source.NoteBookProvider;

/**
 * Created by alexh on 27.09.2016.
 */
public class ShowNotesInNoteBook implements ICommand {

    @Override
    public Response execute(Request request) throws CommandException {
        if (request.getClass() != Request.class){
            throw new CommandException("The request does not Request the class");
        }
        NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();
        return new ResponseWithNoteArray(true, noteBook.toArray(new Note[noteBook.size()]));
    }

}
