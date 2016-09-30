package by.training.notebook.bean;

import by.training.notebook.CommandEnum;

/**
 * Created by Aliaksandr_Harmaza on 9/27/2016.
 */
public class RequestWithNoteContent extends Request {

    private String content;


    public RequestWithNoteContent(CommandEnum commandEnum, String content){
        super(commandEnum);
        setContent(content);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (content == null || content.trim().isEmpty()){
            throw new IllegalArgumentException("Incorrect content");
        }
        this.content = content.trim();
    }


    @Override
    public String toString() {
        return new StringBuilder().append("RequestWithNoteContent{commandName=")
                .append(getCommandName()).append(";content= ")
                .append(content).append("}").toString();
    }
}
