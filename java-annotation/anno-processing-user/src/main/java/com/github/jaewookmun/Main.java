package com.github.jaewookmun;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        PersonSupported person = new PersonSupported();
        person.setName("John");

        System.out.println("person = " + person.getName());
    }
}