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
