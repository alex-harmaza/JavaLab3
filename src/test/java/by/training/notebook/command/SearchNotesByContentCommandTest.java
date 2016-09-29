package by.training.notebook.command;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.*;
import by.training.notebook.bean.entity.Note;
import by.training.notebook.command.impl.SearchNotesByContent;
import by.training.notebook.exception.CommandException;
import by.training.notebook.source.NoteBookProvider;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Aliaksandr_Harmaza on 9/29/2016.
 */
public class SearchNotesByContentCommandTest extends Assert {

    private ICommand command = new SearchNotesByContent();

    @Before @After
    public void beforeAfterTest(){
        NoteBookProvider.getInstance().getNoteBook().clear();
    }

    @Test(expected = CommandException.class)
    public void checkOnIncorrectRequestType() throws CommandException {
        command.execute(new Request(CommandEnum.SEARCH_BY_NOTE_CONTENT));
    }


    @Test
    public void checkResponse() throws CommandException, IOException {
        Note note = new Note(new Date(0), "test");
        NoteBookProvider.getInstance().getNoteBook().add(note);

        Response response = command
                .execute(new RequestWithNoteContent(CommandEnum.SEARCH_BY_NOTE_CONTENT, "test"));

        assertEquals("Incorrect response status", response.isStatus(), true);
        assertEquals("Incorrect response type", response.getClass(), ResponseWithNoteList.class);

        ResponseWithNoteList temp = (ResponseWithNoteList) response;
        assertTrue("Incorrect response note list size",
                temp.getNotes() != null && temp.getNotes().size() == 1);
        assertTrue("Incorrect response note", temp.getNotes().get(0) == note);
    }
}
