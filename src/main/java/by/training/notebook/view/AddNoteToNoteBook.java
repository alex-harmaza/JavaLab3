package by.training.notebook.view;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.Request;
import by.training.notebook.bean.RequestWithNoteContent;
import by.training.notebook.bean.Response;
import by.training.notebook.exception.ViewException;

import java.util.Scanner;

/**
 * Created by Aliaksandr_Harmaza on 9/28/2016.
 */
public class AddNoteToNoteBook extends View {

    @Override
    public Request createRequest(Scanner scanner) throws ViewException {
        Request request;
        try {
            System.out.print("Enter the note: ");
            request = new RequestWithNoteContent(CommandEnum.ADD_NOTE, scanner.nextLine());
        }
        catch (IllegalArgumentException ex){
            throw new ViewException(ex.getMessage(), ex);
        }
        return request;
    }

    @Override
    public void showResponse(Response response) throws ViewException {
        super.showResponse(response);
    }
}
