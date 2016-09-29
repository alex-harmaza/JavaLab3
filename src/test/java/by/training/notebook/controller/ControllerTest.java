package by.training.notebook.controller;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.*;
import by.training.notebook.source.NoteBookProvider;
import org.junit.*;
import org.junit.internal.runners.statements.FailOnTimeout;
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
 * Created by Aliaksandr_Harmaza on 9/29/2016.
 */
@RunWith(Parameterized.class)
public class ControllerTest extends Assert {

    private Controller controller = new Controller();;
    private Request request;
    private boolean expectedResponseStatus;
    private Class expectedResponseClass;


    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {
                        new Request(CommandEnum.CREATE_NEW_NOTEBOOK),
                        true,
                        ResponseWithMessage.class
                },
                {
                        new RequestWithCreatedDate(CommandEnum.CREATE_NEW_NOTEBOOK, new Date()),
                        false,
                        ResponseWithMessage.class
                },
                {
                        new RequestWithNoteContent(CommandEnum.ADD_NOTE, "test"),
                        true,
                        ResponseWithMessage.class
                },
                {
                        new Request(CommandEnum.ADD_NOTE),
                        false,
                        ResponseWithMessage.class
                },
                {
                        new Request(CommandEnum.WRITE_IN_FILE),
                        true,
                        ResponseWithMessage.class
                },
                {
                        new RequestWithNoteContent(CommandEnum.WRITE_IN_FILE, "test"),
                        false,
                        ResponseWithMessage.class
                },
                {
                        new Request(CommandEnum.LOAD_FROM_FILE),
                        true,
                        ResponseWithMessage.class
                },
                {
                        new RequestWithCreatedDate(CommandEnum.LOAD_FROM_FILE, new Date()),
                        false,
                        ResponseWithMessage.class
                },
                {
                        new RequestWithNoteContent(CommandEnum.SEARCH_BY_NOTE_CONTENT, "test"),
                        true,
                        ResponseWithNoteList.class
                },
                {
                        new RequestWithCreatedDate(CommandEnum.SEARCH_BY_NOTE_CONTENT, new Date()),
                        false,
                        ResponseWithMessage.class
                },
                {
                        new RequestWithCreatedDate(CommandEnum.SEARCH_BY_NOTE_CREATE_DATE, new Date()),
                        true,
                        ResponseWithNoteList.class
                },
                {
                        new RequestWithNoteContent(CommandEnum.SEARCH_BY_NOTE_CREATE_DATE, "test"),
                        false,
                        ResponseWithMessage.class
                },
                {
                        new Request(CommandEnum.SHOW_NOTES),
                        true,
                        ResponseWithNoteList.class
                },
                {
                        new RequestWithCreatedDate(CommandEnum.SHOW_NOTES, new Date()),
                        false,
                        ResponseWithMessage.class
                }
        });
    }

    @AfterClass
    public static void afterClass() throws IOException {
        InputStream configFileStream = ControllerTest.class
                .getClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        properties.load(configFileStream);

        File file = new File(properties.getProperty("file.path"));
        file.delete();

        NoteBookProvider.getInstance().getNoteBook().clear();
    }


    public ControllerTest(Request request, boolean expectedResponseStatus, Class expectedResponseClass){
        this.request = request;
        this.expectedResponseStatus = expectedResponseStatus;
        this.expectedResponseClass = expectedResponseClass;
    }


    @Test
    public void execute(){
        Response response = controller.doRequest(request);
        assertEquals("Not expected response status", expectedResponseStatus, response.isStatus());
        assertEquals("Not expected response class", expectedResponseClass, response.getClass());
    }
}
