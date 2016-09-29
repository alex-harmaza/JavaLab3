package by.training.notebook.command;

import by.training.notebook.CommandEnum;
import by.training.notebook.ConfigProperties;
import by.training.notebook.bean.Request;
import by.training.notebook.bean.RequestWithCreatedDate;
import by.training.notebook.bean.entity.Note;
import by.training.notebook.command.impl.WriteNoteBookInFile;
import by.training.notebook.exception.CommandException;
import by.training.notebook.source.NoteBookProvider;
import org.junit.*;

import java.io.*;
import java.util.Date;

/**
 * Created by Aliaksandr_Harmaza on 9/29/2016.
 */
public class WriteNoteBookInFileCommandTest extends CommandTest {

    public WriteNoteBookInFileCommandTest(){
        super(new WriteNoteBookInFile());
    }


    @Test(expected = CommandException.class)
    public void checkOnIncorrectRequestType() throws CommandException {
        getCommand().execute(new RequestWithCreatedDate(CommandEnum.WRITE, new Date()));
    }

    @Test
    public void checkResponse() throws CommandException, IOException {
        Note note = new Note(new Date(0), "test");
        NoteBookProvider.getInstance().getNoteBook().add(note);

        getCommand().execute(new Request(CommandEnum.WRITE));

        String readLine = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(ConfigProperties
                .getInstance().getProperty("file.path")))) {
            readLine = reader.readLine();
        }
        catch (FileNotFoundException ex){
            fail("The file has not been created");
        }

        assertEquals("Incorrect input note", note.toCsvString(), readLine);
    }


    @Before @After
    public void deleteSourceFile() throws IOException {
        new File(ConfigProperties.getInstance().getProperty("file.path")).delete();
    }
}
