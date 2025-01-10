package com.github.jaewookmun;

public class Person {
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    @Builder
    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    @Builder
    public void setName(String name) {
        this.name = name;
    }
}
