package by.training.notebook.command;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.*;
import by.training.notebook.exception.CommandException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Created by Aliaksandr_Harmaza on 9/29/2016.
 */

public class AddNoteToNoteBookCommandTest extends Assert{

    private ICommand command = new by.training.notebook.command.impl.AddNoteToNoteBook();


    @Test(expected = CommandException.class)
    public void checkOnIncorrectRequestType() throws CommandException {
        command.execute(new RequestWithCreatedDate(CommandEnum.ADD_NOTE, new Date()));
    }

    @Test
    public void checkResponse() throws CommandException {
        Response response = command
                .execute(new RequestWithNoteContent(CommandEnum.ADD_NOTE, "test"));
        assertEquals("Incorrect response status", response.isStatus(), true);
        assertEquals("Incorrect response type", response.getClass(), ResponseWithMessage.class);
    }
}
