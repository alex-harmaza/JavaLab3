package by.training.notebook.command.impl;

import by.training.notebook.bean.Request;
import by.training.notebook.bean.Response;
import by.training.notebook.bean.ResponseWithMessage;
import by.training.notebook.bean.entity.Note;
import by.training.notebook.bean.entity.NoteBook;
import by.training.notebook.command.ICommand;
import by.training.notebook.exception.CommandException;
import by.training.notebook.source.NoteBookProvider;

import java.io.*;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Aliaksandr_Harmaza on 9/28/2016.
 */
public class LoadNoteBookFromFile implements ICommand {

    @Override
    public Response execute(Request request) throws CommandException {
        if (request.getClass() != Request.class){
            throw new CommandException("The request does not Request the class");
        }

        try (InputStream configStream = new FileInputStream(this.getClass().getClassLoader()
                .getResource("config.properties").getPath())){
            Properties properties = new Properties();
            properties.load(configStream);

            BufferedReader reader =
                    new BufferedReader(new FileReader(properties.getProperty("file.path")));

            String line;
            NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();
            noteBook.clear();

            try {
                while ((line = reader.readLine()) != null){
                    String[] fields = line.split(";");
                    noteBook.add(new Note(new Date(Long.valueOf(fields[0])), fields[1]));
                }
            }
            finally {
                reader.close();
            }
        }
        catch (IllegalArgumentException | IOException ex){
            throw new CommandException(ex.getMessage(), ex);
        }

        return new ResponseWithMessage(true, "Notebook was loaded");
    }

}
