package by.training.notebook.command.impl;

import by.training.notebook.ConfigProperties;
import by.training.notebook.bean.Request;
import by.training.notebook.bean.Response;
import by.training.notebook.bean.ResponseWithMessage;
import by.training.notebook.command.ICommand;
import by.training.notebook.exception.CommandException;
import by.training.notebook.source.NoteBookProvider;

import java.io.*;
import java.util.Properties;

/**
 * Created by alexh on 27.09.2016.
 */
public class WriteNoteBookInFile implements ICommand {

    @Override
    public Response execute(Request request) throws CommandException {
        if (request.getClass() != Request.class){
            throw new CommandException("The request does not Request the class");
        }

        try (Writer writer = new FileWriter(ConfigProperties.getInstance().getProperty("file.path"));) {
            writer.write(NoteBookProvider.getInstance().getNoteBook().toCsvString());
        }
        catch (IOException ex) {
            throw new CommandException(ex.getMessage(), ex);
        }

        return new ResponseWithMessage(true, "Notebook was written");
    }

}
