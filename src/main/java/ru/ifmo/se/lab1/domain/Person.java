package ru.ifmo.se.lab1.domain;

import java.util.Objects;

public class Person {
    public final String name;
    private final int knowledge;
    private final int reactionThreshold;
    private final Runnable reaction;

    private int attention;


    public Person(String name, int knowledge, int reactionThreshold, Runnable reaction) {
        this.name = name;
        this.knowledge = knowledge;
        this.reactionThreshold = reactionThreshold;
        this.reaction = reaction;
    }

    public void say(Message msg, Starship starship) {
        starship.notify(msg);
    }

    public void receive(Message msg) {
        attention += msg.attention * knowledge;
        if (attention >= reactionThreshold) {
            reaction.run();
            attention = 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this==o) {
            return true;
        }

        if (o==null || getClass()!=o.getClass()) {
            return false;
        }

        Person person = (Person) o;
        return knowledge==person.knowledge &&
                reactionThreshold==person.reactionThreshold &&
                name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, knowledge, reactionThreshold);
    }
}
