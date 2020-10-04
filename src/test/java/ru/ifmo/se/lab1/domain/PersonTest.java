package ru.ifmo.se.lab1.domain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class PersonTest {
    private final static int KNOWLEDGE = 5;
    private final static int REACTION_THRESHOLD = 50;

    private Person person;
    private Runnable reaction;

    @Before
    public void setup() {
        reaction = Mockito.mock(Runnable.class);
        person = new Person("test_person", KNOWLEDGE, REACTION_THRESHOLD, reaction);
    }

    @Test
    public void testSaying() {
        var starship = Mockito.mock(Starship.class);
        person.say(Mockito.mock(Message.class), starship);
        Mockito.verify(starship, Mockito.times(1)).notify(Mockito.any());
    }

    @Test
    public void testReactionOnMessages() {
        var m1 = new Message("m1", REACTION_THRESHOLD / KNOWLEDGE - 1);
        person.receive(m1);
        Mockito.verify(reaction, Mockito.never()).run();

        var m2 = new Message("m2", REACTION_THRESHOLD);
        person.receive(m2);
        Mockito.verify(reaction, Mockito.times(1)).run();

        var m3 = new Message("m3", REACTION_THRESHOLD / KNOWLEDGE - 1);
        person.receive(m3);
        Mockito.verify(reaction, Mockito.times(1)).run();
    }
}
