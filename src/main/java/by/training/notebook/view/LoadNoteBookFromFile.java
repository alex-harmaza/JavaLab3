package by.training.notebook.view;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.Request;
import by.training.notebook.bean.Response;
import by.training.notebook.exception.ViewException;

import java.util.Scanner;

/**
 * Created by Aliaksandr_Harmaza on 9/28/2016.
 */
public class LoadNoteBookFromFile extends View {

    @Override
    public Request createRequest(Scanner scanner) throws ViewException {
        return new Request(CommandEnum.LOAD_FROM_FILE);
    }


    @Override
    public void showResponse(Response response) throws ViewException {
        super.showResponse(response);
    }
}
