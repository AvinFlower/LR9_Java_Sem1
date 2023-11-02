package org.example;

@Table(title = "Crocodile1")
public class Crocodile {
    public enum Size{
        BIG, SMALL, NORMAL, GIGANT
    }
    @Column
    private String name;

    @Column
    private int age;

    @Column
    private String eat;

    @Column
    private Size size;

    public Crocodile(String name, int age, String eat, Size size) {
        this.name = name;
        this.age = age;
        this.size = size;
        this.eat = eat;
    }
}
