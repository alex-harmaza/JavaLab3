package by.training.notebook.command;

import by.training.notebook.CommandEnum;
import by.training.notebook.ConfigProperties;
import by.training.notebook.bean.*;
import by.training.notebook.command.impl.LoadNoteBookFromFile;
import by.training.notebook.exception.CommandException;
import org.junit.*;

import java.io.*;
import java.util.Date;

/**
 * Created by Aliaksandr_Harmaza on 9/29/2016.
 */
public class LoadNoteBookFromFileCommandTest extends CommandTest {

    public LoadNoteBookFromFileCommandTest(){
        super(new LoadNoteBookFromFile());
    }


    @Override
    @Test(expected = CommandException.class)
    public void checkOnIncorrectRequestType() throws CommandException {
        getCommand().execute(new RequestWithCreatedDate(CommandEnum.LOAD, new Date()));
    }

    @Test(expected = CommandException.class)
    public void checkOfTheAbsenceFile() throws CommandException, IOException {
        getCommand().execute(new Request(CommandEnum.LOAD));
    }

    @Test(expected = CommandException.class)
    public void checkOnTheNotCorrectnessOfTheDataInFile() throws IOException, CommandException {
        FileWriter writer = new FileWriter(ConfigProperties.getInstance().getProperty("file.path"));
        writer.write("test;test");
        writer.close();
        getCommand().execute(new Request(CommandEnum.LOAD));
    }

    @Override
    @Test()
    public void checkResponse() throws IOException, CommandException {
        FileWriter writer = new FileWriter(ConfigProperties.getInstance().getProperty("file.path"));
        writer.write("0;test");
        writer.close();

        Response response = getCommand().execute(new Request(CommandEnum.LOAD));
        assertEquals("Incorrect response status", response.isStatus(), true);
        assertEquals("Incorrect response type", response.getClass(), ResponseWithMessage.class);
    }


    @Before @After
    public void deleteSourceFile() throws IOException {
        new File(ConfigProperties.getInstance().getProperty("file.path")).delete();
    }
}
