package by.training.notebook.source;

import by.training.notebook.bean.entity.NoteBook;

/**
 * Created by Aliaksandr_Harmaza on 9/27/2016.
 */
public class NoteBookProvider {

    private static NoteBookProvider ourInstance = new NoteBookProvider();

    private NoteBook noteBook;


    public static NoteBookProvider getInstance() {
        return ourInstance;
    }


    private NoteBookProvider() {
        noteBook = new NoteBook();
    }


    public NoteBook getNoteBook() {
        return noteBook;
    }
}
