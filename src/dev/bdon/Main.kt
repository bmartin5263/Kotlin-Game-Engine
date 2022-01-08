package dev.bdon;

import dev.bdon.engine.Engine;

public class Main { //the Class by which we display our rectangle
    public static void main(String[] args){
        Engine engine = new Engine();
        engine.launch();
        System.out.println("Done");
    }
}