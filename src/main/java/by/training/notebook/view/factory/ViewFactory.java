package by.training.notebook.view.factory;

import by.training.notebook.CommandEnum;
import by.training.notebook.view.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aliaksandr_Harmaza on 9/28/2016.
 */
public class ViewFactory {

    private Map<CommandEnum, View> viewMap;


    public ViewFactory() {
        this.viewMap = new HashMap<>();
        viewMap.put(CommandEnum.CREATE_NEW_NOTEBOOK, new CreateNewNoteBook());
        viewMap.put(CommandEnum.ADD_NOTE, new AddNoteToNoteBookView());
        viewMap.put(CommandEnum.SEARCH_BY_CONTENT, new SearchNotesByContent());
        viewMap.put(CommandEnum.SEARCH_BY_DATE, new SearchNotesByCreatedDate());
        viewMap.put(CommandEnum.SHOW_NOTES, new ShowNotesInNoteBook());
        viewMap.put(CommandEnum.WRITE, new WriteNoteBookInFile());
        viewMap.put(CommandEnum.LOAD, new LoadNoteBookFromFile());
        viewMap.put(CommandEnum.HELP, new ShowHelp());
        viewMap.put(CommandEnum.EXIT, new CloseProgram());
    }


    public View getView(CommandEnum commandEnum){
        return viewMap.get(commandEnum);
    }
}
