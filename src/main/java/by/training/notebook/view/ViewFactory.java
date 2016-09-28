package by.training.notebook.view;

import by.training.notebook.CommandEnum;

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
        viewMap.put(CommandEnum.ADD_NOTE, new AddNoteToNoteBook());
        viewMap.put(CommandEnum.SEARCH_BY_NOTE_CONTENT, new SearchNotesByContent());
        viewMap.put(CommandEnum.SEARCH_BY_NOTE_CREATE_DATE, new SearchNotesByCreatedDate());
        viewMap.put(CommandEnum.SHOW_NOTES, new ShowNotesInNoteBook());
        viewMap.put(CommandEnum.WRITE_IN_FILE, new WriteNoteBookInFile());
        viewMap.put(CommandEnum.LOAD_FROM_FILE, new LoadNoteBookFromFile());
    }


    public View getView(CommandEnum commandEnum){
        return viewMap.get(commandEnum);
    }
}
