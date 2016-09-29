package by.training.notebook.view;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.Request;
import by.training.notebook.bean.Response;
import by.training.notebook.exception.ViewException;

import java.util.Scanner;

/**
 * Created by Aliaksandr_Harmaza on 9/28/2016.
 */
public class CreateNewNoteBook extends View {

    @Override
    public Request createRequest(Scanner scanner) throws ViewException {
        return new Request(CommandEnum.CREATE_NEW_NOTEBOOK);
    }
}
