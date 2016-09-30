package by.training.notebook.bean.entity;

import java.util.*;

/**
 * Created by Aliaksandr_Harmaza on 9/27/2016.
 */
public class NoteBook implements List<Note> {

    private List<Note> noteList;

    public NoteBook(){
        super();
        noteList = new ArrayList<>();
    }


    @Override
    public int size() {
        return noteList.size();
    }

    @Override
    public boolean isEmpty() {
        return noteList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return noteList.contains(o);
    }

    @Override
    public Iterator<Note> iterator() {
        return noteList.iterator();
    }

    @Override
    public Object[] toArray() {
        return noteList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return noteList.toArray(a);
    }

    @Override
    public boolean remove(Object o) {
        return noteList.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return noteList.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Note> c) {
        if (c.contains(null)){
            throw new IllegalArgumentException("Collection contains null");
        }
        return noteList.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Note> c) {
        if (c.contains(null)){
            throw new IllegalArgumentException("Collection contains null");
        }
        return noteList.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return noteList.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return noteList.retainAll(c);
    }

    @Override
    public Note get(int index) {
        return noteList.get(index);
    }

    @Override
    public Note set(int index, Note element) {
        if (element == null){
            throw new IllegalArgumentException("Element is null");
        }
        return noteList.set(index, element);
    }

    @Override
    public void add(int index, Note element) {
        if (element == null){
            throw new IllegalArgumentException("Element is null");
        }
        noteList.add(index, element);
    }

    @Override
    public boolean add(Note element){
        if (element == null){
            throw new IllegalArgumentException("Element is null");
        }
        return noteList.add(element);
    }

    @Override
    public Note remove(int index) {
        return noteList.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return noteList.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return noteList.lastIndexOf(o);
    }

    @Override
    public ListIterator<Note> listIterator() {
        return noteList.listIterator();
    }

    @Override
    public ListIterator<Note> listIterator(int index) {
        return noteList.listIterator(index);
    }

    @Override
    public List<Note> subList(int fromIndex, int toIndex) {
        return noteList.subList(fromIndex, toIndex);
    }

    @Override
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
