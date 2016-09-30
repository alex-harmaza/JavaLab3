package by.training.notebook.command;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.*;
import by.training.notebook.bean.entity.Note;
import by.training.notebook.command.impl.SearchNotesByContent;
import by.training.notebook.exception.CommandException;
import by.training.notebook.source.NoteBookProvider;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Aliaksandr_Harmaza on 9/29/2016.
 */
public class SearchNotesByContentCommandTest extends CommandTest {

    public SearchNotesByContentCommandTest(){
        super(new SearchNotesByContent());
    }


    @Test(expected = CommandException.class)
    public void checkOnIncorrectRequestType() throws CommandException {
        getCommand().execute(new Request(CommandEnum.SEARCH_BY_CONTENT));
    }

    @Test
    public void checkResponse() throws CommandException, IOException {
        Note note = new Note(new Date(0), "test");
        NoteBookProvider.getInstance().getNoteBook().add(note);

        Response response = getCommand()
                .execute(new RequestWithNoteContent(CommandEnum.SEARCH_BY_CONTENT, "test"));

        assertEquals("Incorrect response status", response.isStatus(), true);
        assertEquals("Incorrect response type", response.getClass(), ResponseWithNoteArray.class);

        ResponseWithNoteArray temp = (ResponseWithNoteArray) response;
        assertTrue("Incorrect response note list size",
                temp.getNotes() != null && temp.getNotes().length == 1);
        assertTrue("Incorrect response note", temp.getNotes()[0] == note);
    }
}
