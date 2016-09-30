package by.training.notebook.command;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.*;
import by.training.notebook.bean.entity.NoteBook;
import by.training.notebook.command.exception.CommandException;
import by.training.notebook.source.NoteBookProvider;
import org.junit.Test;

import java.util.Date;

/**
 * Created by Aliaksandr_Harmaza on 9/29/2016.
 */

public class AddNoteToNoteBookCommandTest extends CommandTest{

    public AddNoteToNoteBookCommandTest(){
        super(new by.training.notebook.command.impl.AddNoteToNoteBook());
    }


    @Override
    @Test(expected = CommandException.class)
    public void checkOnIncorrectRequestType() throws CommandException {
        getCommand().execute(new RequestWithCreatedDate(CommandEnum.ADD_NOTE, new Date()));
    }

    @Override
    @Test
    public void checkResponse() throws CommandException {
        Response response = getCommand()
                .execute(new RequestWithNoteContent(CommandEnum.ADD_NOTE, "test"));
        assertEquals("Incorrect response status", response.isStatus(), true);
        assertEquals("Incorrect response type", response.getClass(), ResponseWithMessage.class);
    }

    @Test
    public void checkAddedFileInNoteBook() throws CommandException{
        getCommand().execute(new RequestWithNoteContent(CommandEnum.ADD_NOTE, "test"));
        NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();
        assertTrue("Added note is missing", noteBook.size() == 1 && noteBook.get(0).getData().equals("test"));
    }
}
