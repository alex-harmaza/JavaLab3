package by.training.notebook.bean;

import by.training.notebook.bean.entity.Note;

import java.util.List;

/**
 * Created by alexh on 27.09.2016.
 */
public class ResponseWithNoteList extends Response {

    private List<Note> notes;


    public ResponseWithNoteList(boolean status, List<Note> notes){
        super(status);
        setNotes(notes);
    }


    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        if (notes == null){
            throw new IllegalArgumentException("Notes is null");
        }
        this.notes = notes;
    }
}
