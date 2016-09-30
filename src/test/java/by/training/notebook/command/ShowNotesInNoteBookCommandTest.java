package by.training.notebook.command;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.*;
import by.training.notebook.bean.entity.Note;
import by.training.notebook.command.impl.ShowNotesInNoteBook;
import by.training.notebook.command.exception.CommandException;
import by.training.notebook.source.NoteBookProvider;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Aliaksandr_Harmaza on 9/29/2016.
 */
public class ShowNotesInNoteBookCommandTest extends CommandTest {

    public ShowNotesInNoteBookCommandTest(){
        super(new ShowNotesInNoteBook());
    }


    @Override
    @Test(expected = CommandException.class)
    public void checkOnIncorrectRequestType() throws CommandException {
        getCommand().execute(new RequestWithCreatedDate(CommandEnum.SHOW_NOTES, new Date()));
    }

    @Override
    @Test
    public void checkResponse() throws CommandException, IOException {
        Note note = new Note(new Date(0), "test");
        NoteBookProvider.getInstance().getNoteBook().add(note);

        Response response = getCommand().execute(new Request(CommandEnum.SHOW_NOTES));

        assertEquals("Incorrect response status", response.isStatus(), true);
        assertEquals("Incorrect response type", response.getClass(), ResponseWithNoteArray.class);

        assertTrue("Incorrect response note in list",
                note == ((ResponseWithNoteArray) response).getNotes()[0]);
    }
}
