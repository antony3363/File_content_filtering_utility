package org.example;


import java.io.IOException;

public class App {
    static Output output = new Output();

    public static void main(String[] args) throws IOException { // убрать IOException
        Input.input();

        /*
        System.out.println("a " + String.valueOf(output.isA()) + '\n' +
                "o " +  String.valueOf(output.isO()) + '\n' +
                "s " + String.valueOf(output.isS()) + '\n' +
                "p " + String.valueOf(output.isP()) + '\n' +
                "f " + String.valueOf(output.isF()) + '\n' +
                output.getName() + '\n' +
                output.getPath() + '\n' +
                output.getFiles());
         */
        Input.readText();

//        System.out.println(output.getPath() + "/" + output.getName() + "integers.txt" + '\n' +
//                output.getPath() + "/" + output.getName() + "floats.txt" + '\n' +
//                output.getPath() + "/" + output.getName() + "strings.txt");

//        System.out.println(output.getName() + "integers.txt" + '\n' +
//                output.getName() + "floats.txt" + '\n' +
//                output.getName() + "strings.txt");

        if (output.isS()) {
            output.outputS();
        } else if (output.isF()) {
            output.outputF();
        }
    }
}
