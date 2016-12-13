package com.humanbooster.todolist.database;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by martin on 02/01/16.
 */
public class Task implements Serializable{

    private Integer id;
    private String title;
    private String desc;

    public Task(){
    }

    public Task(Integer id, String title, String desc) {
        this.id = id;
        this.title = title;
        this.desc = desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}