package by.training.notebook;

/**
 * Created by Aliaksandr_Harmaza on 9/27/2016.
 */
public enum  CommandEnum {
    CREATE_NEW_NOTEBOOK ("CNN"),
    ADD_NOTE("AN"),
    SHOW_NOTES("SN"),

    SEARCH_BY_CONTENT("SBC"),
    SEARCH_BY_DATE("SBD"),

    WRITE("W"),
    LOAD("L"),
    HELP("H"),
    EXIT("E");

    private String reducedCommand;


    CommandEnum(String reducedCommand){
        this.reducedCommand = reducedCommand;
    }


    public String getReducedCommand() {
        return reducedCommand;
    }


    public static CommandEnum getEnum(String name){
        CommandEnum result = getReducedCommand(name);
        return (result != null) ? result : CommandEnum.valueOf(name);
    }

    private static CommandEnum getReducedCommand(String name){
        CommandEnum commandEnum = null;
        for (CommandEnum e : CommandEnum.values()){
            if (e.getReducedCommand().equals(name)){
                commandEnum = e;
                break;
            }
        }
        return commandEnum;
    }
}
