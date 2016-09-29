package by.training.notebook.controller;

import by.training.notebook.CommandEnum;
import by.training.notebook.command.ICommand;
import by.training.notebook.command.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aliaksandr_Harmaza on 9/27/2016.
 */
public class CommandFactory {

    private Map<CommandEnum, ICommand> commandMap;


    public CommandFactory(){
        commandMap = new HashMap<>();
        commandMap.put(CommandEnum.CREATE_NEW_NOTEBOOK, new CreateNewNotebook());
        commandMap.put(CommandEnum.ADD_NOTE, new AddNoteToNoteBook());
        commandMap.put(CommandEnum.SEARCH_BY_CONTENT, new SearchNotesByContent());
        commandMap.put(CommandEnum.SEARCH_BY_DATE, new SearchNotesByCreatedDate());
        commandMap.put(CommandEnum.SHOW_NOTES, new ShowNotesInNoteBook());
        commandMap.put(CommandEnum.WRITE, new WriteNoteBookInFile());
        commandMap.put(CommandEnum.LOAD, new LoadNoteBookFromFile());
        commandMap.put(CommandEnum.HELP, new ShowHelp());
    }


    public ICommand getCommand(CommandEnum commandEnum){
        return commandMap.get(commandEnum);
    }
}
