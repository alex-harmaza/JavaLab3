package by.training.notebook.command;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.*;
import by.training.notebook.bean.entity.Note;
import by.training.notebook.bean.entity.NoteBook;
import by.training.notebook.command.impl.CreateNewNotebook;
import by.training.notebook.exception.CommandException;
import by.training.notebook.source.NoteBookProvider;
import org.junit.*;

import java.util.Date;


/**
 * Created by Aliaksandr_Harmaza on 9/28/2016.
 */
public class CreateNewNoteBookCommandTest extends CommandTest {

    public CreateNewNoteBookCommandTest(){
        super(new CreateNewNotebook());
    }


    @Override
    @Test(expected = CommandException.class)
    public void checkOnIncorrectRequestType() throws CommandException{
        getCommand().execute(new RequestWithNoteContent(CommandEnum
                .CREATE_NEW_NOTEBOOK, "test"));
    }

    @Override
    @Test
    public void checkResponse() throws CommandException{
        Response response = getCommand().execute(new Request(CommandEnum.CREATE_NEW_NOTEBOOK));
        assertEquals("Incorrect response status", response.isStatus(), true);
        assertEquals("Incorrect response type", response.getClass(), ResponseWithMessage.class);
    }

    @Test
    public void checkTheAbsenceOfNotesInNoteBook() throws CommandException{
        NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();
        noteBook.add(new Note(new Date(), "test"));
        getCommand().execute(new Request(CommandEnum.CREATE_NEW_NOTEBOOK));
        assertTrue("The new notebook is not created", noteBook.size() == 0);
    }
}
