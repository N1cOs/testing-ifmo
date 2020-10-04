package ru.ifmo.se.lab1.domain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class StarshipTest {
    private Starship starship;

    @Before
    public void setup() {
        starship = new Starship("test_starship");
    }

    @Test
    public void testNotifying() {
        var p1 = Mockito.mock(Person.class);
        starship.enter(p1);

        var p2 = Mockito.mock(Person.class);
        starship.enter(p2);

        starship.notify(Mockito.mock(Message.class));
        Mockito.verify(p1, Mockito.times(1)).receive(Mockito.any());
        Mockito.verify(p2, Mockito.times(1)).receive(Mockito.any());

        starship.exit(p1);
        starship.notify(Mockito.mock(Message.class));

        Mockito.verify(p1, Mockito.times(1)).receive(Mockito.any());
        Mockito.verify(p2, Mockito.times(2)).receive(Mockito.any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEnteringExistingPerson() {
        var p1 = Mockito.mock(Person.class);
        starship.enter(p1);
        starship.enter(p1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExitingUnknownPerson() {
        var p1 = Mockito.mock(Person.class);
        starship.exit(p1);
    }
}
