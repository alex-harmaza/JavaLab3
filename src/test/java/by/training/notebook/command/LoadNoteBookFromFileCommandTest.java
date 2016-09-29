package by.training.notebook.command;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.*;
import by.training.notebook.command.impl.LoadNoteBookFromFile;
import by.training.notebook.exception.CommandException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Aliaksandr_Harmaza on 9/29/2016.
 */
public class LoadNoteBookFromFileCommandTest extends Assert {

    private ICommand command = new LoadNoteBookFromFile();


    @BeforeClass @AfterClass
    public static void deleteSourceFile() throws IOException {
        new File(getSourceFilePath()).delete();
    }


    @Test(expected = CommandException.class)
    public void checkOnIncorrectRequestType() throws CommandException {
        command.execute(new RequestWithCreatedDate(CommandEnum.LOAD_FROM_FILE, new Date()));
    }

    @Test(expected = CommandException.class)
    public void checkOfTheAbsenceFile() throws CommandException, IOException {
        command.execute(new Request(CommandEnum.LOAD_FROM_FILE));
    }

    @Test(expected = CommandException.class)
    public void checkOnTheNotCorrectnessOfTheDataInFile() throws IOException, CommandException {
        FileWriter writer = new FileWriter(getSourceFilePath());
        writer.write("test;test");
        writer.close();
        command.execute(new Request(CommandEnum.LOAD_FROM_FILE));
    }

    @Test()
    public void checkOnTheCorrectnessOfTheDataInFile() throws IOException, CommandException {
        FileWriter writer = new FileWriter(getSourceFilePath());
        writer.write("0;test");
        writer.close();
        try {
            Response response = command.execute(new Request(CommandEnum.LOAD_FROM_FILE));
            assertEquals("Incorrect response status", response.isStatus(), true);
            assertEquals("Incorrect response type", response.getClass(), ResponseWithMessage.class);
        }
        catch (CommandException ex){
            fail("Not should be create CommandException");
        }
    }


    private static String getSourceFilePath() throws IOException {
        InputStream configFileStream = LoadNoteBookFromFileCommandTest.class
                .getClassLoader().getResourceAsStream("config.properties");
        Properties configProperties = new Properties();
        configProperties.load(configFileStream);
        return configProperties.getProperty("file.path");
    }
}
