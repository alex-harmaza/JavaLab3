package by.training.notebook;

import by.training.notebook.bean.Request;
import by.training.notebook.bean.Response;
import by.training.notebook.controller.Controller;
import by.training.notebook.exception.ExitViewException;
import by.training.notebook.exception.ViewException;
import by.training.notebook.view.View;
import by.training.notebook.view.ViewFactory;

import java.util.Scanner;

/**
 * Created by Aliaksandr_Harmaza on 9/28/2016.
 */
public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ViewFactory viewFactory = new ViewFactory();
        Controller controller = new Controller();

        while (true){
            System.out.println("\n=============================");
            System.out.print("Enter the command: ");
            try {
                CommandEnum command = CommandEnum.getEnum(scanner.nextLine().toUpperCase());
                View view = viewFactory.getView(command);
                Request request = view.createRequest(scanner);
                Response response = controller.doRequest(request);
                view.showResponse(response);
            }
            catch (IllegalArgumentException ex){
                System.out.println("Error: incorrect command name");
            } catch (ViewException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

}
