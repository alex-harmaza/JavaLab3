package by.training.notebook.command;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.Request;
import by.training.notebook.bean.RequestWithCreatedDate;
import by.training.notebook.bean.entity.Note;
import by.training.notebook.command.impl.WriteNoteBookInFile;
import by.training.notebook.exception.CommandException;
import by.training.notebook.source.NoteBookProvider;
import org.junit.*;

import java.io.*;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Aliaksandr_Harmaza on 9/29/2016.
 */
public class WriteNoteBookInFileCommandTest extends Assert {

    private ICommand command = new WriteNoteBookInFile();


    @Before @After
    public void beforeAfterTest(){
        NoteBookProvider.getInstance().getNoteBook().clear();
    }

    @BeforeClass @AfterClass
    public static void deleteSourceFile() throws IOException {
        new File(getSourceFilePath()).delete();
    }


    @Test(expected = CommandException.class)
    public void checkOnIncorrectRequestType() throws CommandException {
        command.execute(new RequestWithCreatedDate(CommandEnum.WRITE_IN_FILE, new Date()));
    }

    @Test
    public void checkResponse() throws CommandException, IOException {
        Note note = new Note(new Date(0), "test");
        NoteBookProvider.getInstance().getNoteBook().add(note);

        command.execute(new Request(CommandEnum.WRITE_IN_FILE));

        String readLine = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(getSourceFilePath()))) {
            readLine = reader.readLine();
        }
        catch (FileNotFoundException ex){
            fail("The file has not been created");
        }

        assertEquals("Incorrect input note", note.toCsvString(), readLine);
    }


    private static String getSourceFilePath() throws IOException {
        InputStream configFileStream = LoadNoteBookFromFileCommandTest.class
                .getClassLoader().getResourceAsStream("config.properties");
        Properties configProperties = new Properties();
        configProperties.load(configFileStream);
        return configProperties.getProperty("file.path");
    }
}
