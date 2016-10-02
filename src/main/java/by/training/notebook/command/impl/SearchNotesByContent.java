package by.training.notebook.command.impl;

import by.training.notebook.bean.Request;
import by.training.notebook.bean.Response;
import by.training.notebook.bean.RequestWithNoteContent;
import by.training.notebook.bean.ResponseWithNoteArray;
import by.training.notebook.bean.entity.Note;
import by.training.notebook.command.ICommand;
import by.training.notebook.command.exception.CommandException;
import by.training.notebook.source.NoteBookProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Aliaksandr_Harmaza on 9/27/2016.
 */
public class SearchNotesByContent implements ICommand {

    @Override
    public Response execute(Request request) throws CommandException {
        if (request == null || request.getClass() != RequestWithNoteContent.class){
            throw new CommandException("The request does not SearchNoteByContentRequest the class");
        }

        RequestWithNoteContent temp = (RequestWithNoteContent) request;
        List<Note> result = new ArrayList<>();
        Iterator<Note> iterator = NoteBookProvider.getInstance()
                .getNoteBook().iterator();

        while (iterator.hasNext()){
            Note note = iterator.next();
            if (note.getData().equals(temp.getContent())){
                result.add(note);
            }
        }

        return new ResponseWithNoteArray(true, result.toArray(new Note[result.size()]));
    }

}
