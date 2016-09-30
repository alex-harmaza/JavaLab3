package by.training.notebook.view;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.*;
import by.training.notebook.bean.entity.Note;
import by.training.notebook.view.exception.ViewException;

import java.util.Scanner;

/**
 * Created by Aliaksandr_Harmaza on 9/28/2016.
 */
public class SearchNotesByContent extends View {

    @Override
    public Request createRequest(Scanner scanner) throws ViewException {
        Request request;
        try {
            System.out.print("Enter the content: ");
            request = new RequestWithNoteContent(CommandEnum.SEARCH_BY_CONTENT, scanner.nextLine());
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
        else if (response.getClass() != ResponseWithNoteArray.class){
            throw new ViewException("Incorrect response type");
        }

        ResponseWithNoteArray temp = (ResponseWithNoteArray) response;
        if (temp.getNotes().length == 0){
            System.out.println("Result: Nothing");
        }
        else {
            System.out.println("Result: ");
            for (Note note : temp.getNotes()){
                System.out.println(note.toString());
            }
        }
    }

}
