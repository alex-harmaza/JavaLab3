package by.training.notebook;

import by.training.notebook.bean.Request;
import by.training.notebook.bean.RequestWithCreatedDate;
import by.training.notebook.bean.RequestWithNoteContent;
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
import java.util.Date;
import java.util.Properties;

/**
 * Created by Aliaksandr_Harmaza on 9/28/2016.
 */
@RunWith(Parameterized.class)
public class CommandTest extends Assert {

    private ICommand command;
    private Request[] requests;
    private boolean[] isExpectedCommandException;


    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][] {
                {
                        new AddNoteToNoteBook(),
                        new Request[]{
                                new Request(CommandEnum.ADD_NOTE),
                                new RequestWithNoteContent(CommandEnum.ADD_NOTE, "test")
                        },
                        new boolean[]{true, false}
                },
                {
                        new CreateNewNotebook(),
                        new Request[]{new Request(CommandEnum.ADD_NOTE)},
                        new boolean[]{false}
                },
                {
                        new LoadNoteBookFromFile(),
                        new Request[]{
                                new Request(CommandEnum.LOAD_FROM_FILE),
                                new RequestWithNoteContent(CommandEnum.LOAD_FROM_FILE, "test")
                        },
                        new boolean[]{true, true} //Default file not found
                },
                {
                        new SearchNotesByContent(),
                        new Request[]{
                                new Request(CommandEnum.SEARCH_BY_NOTE_CONTENT),
                                new RequestWithNoteContent(CommandEnum.SEARCH_BY_NOTE_CONTENT, "test")
                        },
                        new boolean[]{true, false}},
                {
                        new SearchNotesByCreatedDate(),
                        new Request[]{
                                new RequestWithCreatedDate(CommandEnum.SEARCH_BY_NOTE_CREATE_DATE, new Date()),
                                new Request(CommandEnum.SEARCH_BY_NOTE_CREATE_DATE)
                        },
                        new boolean[]{false, true}
                },
                {
                        new ShowNotesInNoteBook(),
                        new Request[]{
                                new Request(CommandEnum.SHOW_NOTES),
                                new RequestWithNoteContent(CommandEnum.SHOW_NOTES, "test")
                        },
                        new boolean[]{false, true}
                },
                {
                        new WriteNoteBookInFile(),
                        new Request[]{
                                new RequestWithCreatedDate(CommandEnum.WRITE_IN_FILE, new Date()),
                                new Request(CommandEnum.WRITE_IN_FILE)
                        },
                        new boolean[]{true, false}
                }
        });
    }


    public CommandTest(ICommand  command, Request[] requests, boolean[] isExpectedCommandException){
        this.command = command;
        this.requests = requests;
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
        for (int i = 0; i < requests.length; i++){
            StringBuilder builder = new StringBuilder();
            builder.append("\n\tCommand: ").append(command.getClass().toString());
            builder.append("\n\tInput request: ").append(requests[i].toString());
            builder.append("\n\tExpexted: ").append(isExpectedCommandException[i]);

            try {
                command.execute(requests[i]);
                builder.append("\n\tActual: ").append(false);
                assertFalse(builder.toString(), isExpectedCommandException[i]);
            }
            catch (CommandException ex){
                builder.append("\n\tActual: ").append(true);
                assertTrue(builder.toString(), isExpectedCommandException[i]);
            }
        }
    }

}
