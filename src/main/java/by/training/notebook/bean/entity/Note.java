package by.training.notebook.bean.entity;

import java.util.Date;

/**
 * Created by Aliaksandr_Harmaza on 9/27/2016.
 */
public class Note {

    private Date createdDate;
    private String data;


    public Note(Date createdDate, String data){
        setCreatedDate(createdDate);
        setData(data);
    }


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate){
        if (createdDate == null){
            throw new IllegalArgumentException("createdDate is null");
        }
        this.createdDate = createdDate;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        if (data == null || data.isEmpty()){
            throw new IllegalArgumentException("Incorrect data");
        }
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        if (createdDate != null ? !createdDate.equals(note.createdDate) : note.createdDate != null) return false;
        return data != null ? data.equals(note.data) : note.data == null;
    }

    @Override
    public int hashCode() {
        int result = createdDate != null ? createdDate.hashCode() : 0;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("\n");
        builder.append("date of creation: ").append(createdDate.toString()).append("\n");
        builder.append("content: ").append(data).append("\n");
        return builder.toString();
    }

    public String toCsvString(){
        StringBuilder builder = new StringBuilder();
        builder.append(createdDate.getTime()).append(";");
        builder.append(data).append(";");
        return builder.toString();
    }
}
