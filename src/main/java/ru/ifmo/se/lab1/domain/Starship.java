package ru.ifmo.se.lab1.domain;

import java.util.HashSet;
import java.util.Set;

public class Starship {
    private final String name;
    private final Set<Person> crew;

    public Starship(String name) {
        this.name = name;
        this.crew = new HashSet<>();
    }

    public void enter(Person person) {
        if (crew.contains(person)) {
            var msg = String.format("person is already on the starship: person=%s, starship=%s",
                    person.name, name);
            throw new IllegalArgumentException(msg);
        }
        crew.add(person);
    }

    public void exit(Person person) {
        if (!crew.remove(person)) {
            var msg = String.format("unknown person on the starship: person=%s, starship=%s", person.name, name);
            throw new IllegalArgumentException(msg);
        }
    }

    public void notify(Message msg) {
        crew.forEach(p -> p.receive(msg));
    }
}
