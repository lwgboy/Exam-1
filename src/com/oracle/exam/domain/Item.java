package com.oracle.exam.domain;

import java.io.Serializable;

/**
 * Created by Jason on 2016/12/2.
 */
public class Item implements Serializable {

    private int id;
    private String title;
    private String a;
    private String b;
    private String c;
    private String d;
    private String answer;

    public Item() {
    }

    public Item(int id, String title, String a, String b, String c, String d, String answer) {
        this.id = id;
        this.title = title;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return
                id +". "+ title + '\n' + a + '\n' + b + '\n' + c + '\n' + d;
    }
}
