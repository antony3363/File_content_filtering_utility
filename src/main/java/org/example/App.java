package org.example;



public class App {
    static Output output = new Output();

    public static void main(String[] args){
        Input.input();

        Input.readText();

        if (output.isS()) {
            output.outputS();
        } else if (output.isF()) {
            output.outputF();
        }
    }
}
