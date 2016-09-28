package by.training.notebook;

import by.training.notebook.bean.Request;
import by.training.notebook.command.ICommand;
import by.training.notebook.command.impl.*;
import by.training.notebook.exception.CommandException;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

/**
 * Created by Aliaksandr_Harmaza on 9/28/2016.
 */
@RunWith(Parameterized.class)
public class CommandTest extends Assert {

    private ICommand command;
    private boolean isExpectedCommandException;


    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][] {
                {new AddNoteToNoteBook(), true},
                {new CreateNewNotebook(), false},
                {new LoadNoteBookFromFile(), true}, //File not exist
                {new SearchNotesByContent(), true},
                {new SearchNotesByCreatedDate(), true},
                {new ShowNotesInNoteBook(), false},
                {new WriteNoteBookInFile(), false}
        });
    }


    public CommandTest(ICommand  command, boolean isExpectedCommandException){
        this.command = command;
        this.isExpectedCommandException = isExpectedCommandException;
    }


    @Before @After
    public void beforeTest() throws IOException{
        InputStream configFileStream = this.getClass()
                .getClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        properties.load(configFileStream);

        File file = new File(properties.getProperty("file.path"));
        if (file.exists()){
            file.delete();
        }
    }


    @Test
    public void checkCommandOnIncorrectRequestType(){
        try {
            command.execute(new Request(CommandEnum.ADD_NOTE));
            assertFalse(command.getClass().toString(), isExpectedCommandException);
        }
        catch (CommandException ex){
            assertTrue(command.getClass().toString(), isExpectedCommandException);
        }
    }

}
