package by.training.notebook.view;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.*;
import by.training.notebook.bean.entity.Note;
import by.training.notebook.exception.ViewException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by Aliaksandr_Harmaza on 9/28/2016.
 */
public class SearchNotesByCreatedDate extends View {

    @Override
    public Request createRequest(Scanner scanner) throws ViewException {
        Request request;
        try {
            DateFormat format = new SimpleDateFormat("d.M.y", Locale.ENGLISH);
            System.out.print("Enter the date [day.month.year]: ");
            request = new RequestWithCreatedDate(CommandEnum.SEARCH_BY_NOTE_CREATE_DATE,
                    format.parse(scanner.nextLine()));
        }
        catch (ParseException ex){
            throw new ViewException("Incorrect date", ex.getCause());
        }
        catch (IllegalArgumentException ex){
            throw new ViewException(ex.getMessage(), ex);
        }
        return request;
    }

    @Override
    public void showResponse(Response response) throws ViewException {
        if (response.getClass() == ResponseWithMessage.class){
            super.showResponse(response);
        }
        else if (response.getClass() != ResponseWithNoteList.class){
            throw new ViewException("Incorrect response type");
        }

        ResponseWithNoteList temp = (ResponseWithNoteList) response;
        System.out.println("Result: " + (temp.getNotes().size() == 0 ? "nothing" : ""));
        for (Note note : temp.getNotes()){
            System.out.println(note.toString());
        }
    }
}
