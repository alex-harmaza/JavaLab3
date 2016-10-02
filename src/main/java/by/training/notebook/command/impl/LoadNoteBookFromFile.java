package by.training.notebook.command.impl;

import by.training.notebook.source.ConfigProvider;
import by.training.notebook.bean.Request;
import by.training.notebook.bean.Response;
import by.training.notebook.bean.ResponseWithMessage;
import by.training.notebook.bean.entity.Note;
import by.training.notebook.bean.entity.NoteBook;
import by.training.notebook.command.ICommand;
import by.training.notebook.command.exception.CommandException;
import by.training.notebook.source.NoteBookProvider;

import java.io.*;
import java.util.Date;

/**
 * Created by Aliaksandr_Harmaza on 9/28/2016.
 */
public class LoadNoteBookFromFile implements ICommand {

    @Override
    public Response execute(Request request) throws CommandException {
        if (request == null || request.getClass() != Request.class){
            throw new CommandException("The request does not Request the class");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(ConfigProvider.getInstance()
                .getProperty("file.path")))) {

            NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();
            noteBook.clear();

            String line;
            while ((line = reader.readLine()) != null){
                String[] fields = line.split(";");
                noteBook.add(new Note(new Date(Long.valueOf(fields[0])), fields[1]));
            }
        }
        catch (IllegalArgumentException | IOException ex){
            throw new CommandException(ex.getMessage(), ex);
        }

        return new ResponseWithMessage(true, "Notebook was loaded");
    }

}
