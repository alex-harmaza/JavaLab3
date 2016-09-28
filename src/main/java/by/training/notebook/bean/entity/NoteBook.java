package by.training.notebook.bean.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aliaksandr_Harmaza on 9/27/2016.
 */
public class NoteBook {

    private List<Note> noteList;


    public NoteBook(){
        setNoteList(new ArrayList<Note>());
    }


    public void add(Note note){
        if (note == null){
            throw new IllegalArgumentException("Note is null");
        }
        noteList.add(note);
    }

    public Note getNoteList(int index){
        return noteList.get(index);
    }

    public void setNoteList(int index, Note note){
        if (note == null){
            throw new IllegalArgumentException("Note is null");
        }
        noteList.add(index, note);
    }

    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        if (noteList.contains(null)){
            throw new IllegalArgumentException("Note list contains null");
        }
        this.noteList = noteList;
    }

    public void clear(){
        noteList.clear();
    }

    public String toCsvString(){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < noteList.size(); i++) {
            builder.append(noteList.get(i).toCsvString()).append("\n");
        }
        return builder.toString();
    }
}
