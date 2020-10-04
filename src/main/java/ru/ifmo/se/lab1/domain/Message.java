package ru.ifmo.se.lab1.domain;

public class Message {
    public final String text;
    public final int attention;

    public Message(String text, int attention) {
        this.text = text;
        this.attention = attention;
    }
}
