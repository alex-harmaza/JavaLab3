package by.training.notebook.command.impl;

import by.training.notebook.bean.*;
import by.training.notebook.bean.entity.Note;
import by.training.notebook.command.ICommand;
import by.training.notebook.command.exception.CommandException;
import by.training.notebook.source.NoteBookProvider;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * Created by alexh on 27.09.2016.
 */
public class SearchNotesByCreatedDate implements ICommand {

    @Override
    public Response execute(Request request) throws CommandException {
        if (request == null || request.getClass() != RequestWithCreatedDate.class){
            throw new CommandException("The request does not RequestWithCreatedDate the class");
        }

        RequestWithCreatedDate temp = (RequestWithCreatedDate) request;
        List<Note> result = new ArrayList<>();
        Iterator<Note> iterator = NoteBookProvider.getInstance()
                .getNoteBook().iterator();

        while (iterator.hasNext()){
            Note note = iterator.next();

            Calendar createdDate = Calendar.getInstance();
            Calendar askingDate = Calendar.getInstance();

            createdDate.setTime(note.getCreatedDate());
            askingDate.setTime(temp.getCreatedDate());

            if (createdDate.get(Calendar.YEAR) == askingDate.get(Calendar.YEAR)
                    && createdDate.get(Calendar.DAY_OF_YEAR) == askingDate.get(Calendar.DAY_OF_YEAR)){
                result.add(note);
            }
        }

        return new ResponseWithNoteArray(true, result.toArray(new Note[result.size()]));
    }

}
