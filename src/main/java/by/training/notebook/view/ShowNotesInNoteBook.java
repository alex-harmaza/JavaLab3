package by.training.notebook.view;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.Request;
import by.training.notebook.bean.Response;
import by.training.notebook.bean.ResponseWithMessage;
import by.training.notebook.bean.ResponseWithNoteArray;
import by.training.notebook.bean.entity.Note;
import by.training.notebook.exception.ViewException;

import java.util.Scanner;

/**
 * Created by Aliaksandr_Harmaza on 9/28/2016.
 */
public class ShowNotesInNoteBook extends View {

    @Override
    public Request createRequest(Scanner scanner) throws ViewException {
        return new Request(CommandEnum.SHOW_NOTES);
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
            System.out.println("Result: nothing");
        }
        else {
            System.out.println("Result: ");
            for (Note note : temp.getNotes()){
                System.out.println(note.toString());
            }
        }
    }

}
