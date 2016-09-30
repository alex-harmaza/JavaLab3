package by.training.notebook.view;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.Request;
import by.training.notebook.bean.Response;
import by.training.notebook.view.exception.ViewException;

import java.util.Scanner;

/**
 * Created by alexh on 29.09.2016.
 */
public class CloseProgram extends View {

    @Override
    public Request createRequest(Scanner scanner) throws ViewException {
        System.out.print("Do you want to save the notebook in the file? (Y/N): ");
        String isWrite = scanner.nextLine().toUpperCase();

        if (!isWrite.equals("Y") && !isWrite.equals("N")){
            throw new ViewException("Incorrect answer");
        }

        Request request = null;
        if (isWrite.equals("Y")){
            request = new Request(CommandEnum.WRITE);
        }
        else {
            System.exit(0);
        }
        return request;
    }

    @Override
    public void showResponse(Response response) throws ViewException {
        super.showResponse(response);
        System.exit(0);
    }
}
