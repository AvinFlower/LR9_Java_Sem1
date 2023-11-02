package org.example;


public class Main {
    public static void main(String[] args) throws Exception {
        Crocodile crocodile = new Crocodile("Lacoste", 8, "Clear food",Crocodile.Size.BIG);
        Crocodile crocodile2 = new Crocodile("Louis", 4, "Expensive Food",Crocodile.Size.NORMAL);
        Crocodile crocodile3 = new Crocodile("Gucci", 2, "Birds",Crocodile.Size.SMALL);
        Crocodile crocodile4 = new Crocodile("Godzilla", 35, "Crocodiles", Crocodile.Size.GIGANT);

        AnnotationProcessor.createTable(crocodile);
        AnnotationProcessor.insertIntoTable(crocodile);
        AnnotationProcessor.insertIntoTable(crocodile2);
        AnnotationProcessor.insertIntoTable(crocodile3);
        AnnotationProcessor.insertIntoTable(crocodile4);
    }
}