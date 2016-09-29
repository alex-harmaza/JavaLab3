package by.training.notebook.command;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.*;
import by.training.notebook.command.impl.CreateNewNotebook;
import by.training.notebook.exception.CommandException;
import org.junit.*;


/**
 * Created by Aliaksandr_Harmaza on 9/28/2016.
 */
public class CreateNewNoteBookCommandTest extends Assert {

    private ICommand command = new CreateNewNotebook();


    @Test(expected = CommandException.class)
    public void checkOnIncorrectRequestType() throws CommandException{
        command.execute(new RequestWithNoteContent(CommandEnum
                .CREATE_NEW_NOTEBOOK, "test"));
    }

    @Test
    public void checkResponse() throws CommandException{
        Response response = command.execute(new Request(CommandEnum.CREATE_NEW_NOTEBOOK));
        assertEquals("Incorrect response status", response.isStatus(), true);
        assertEquals("Incorrect response type", response.getClass(), ResponseWithMessage.class);
    }
}
