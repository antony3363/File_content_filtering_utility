package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Input {
    public static void input() {
        String str = "";
        String nameFile = "";
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        char[] chars = input.toCharArray();
        boolean flag = false;
        //boolean flagFile = false;
        //ArrayList<Character> charsFile = new ArrayList<Character>();
        for (int i = 0; i < chars.length; i++) {
            //charsFile.add(chars[i]);
            if (!nameFile.equals("") || !(chars[i] == ' ')) {
                nameFile += chars[i];


                if (nameFile.endsWith(".txt")) {
                    if (i + 1 == chars.length || chars[i + 1] == ' ') {
                        try {
                            App.output.getFiles().add(new FileReader(nameFile));
                            nameFile = "";
                            i += 1;
                        } catch (FileNotFoundException e) {
                            System.out.println("Файл " + nameFile + " не был найден на вашем устройстве");
                            System.exit(0);
                        }
                    } else {
                        System.out.println("Неверное расширение файла: '.txt" + chars[i + 1] + "'");
                        System.exit(0);
                    }

                } else if (flag) {
                    if (chars[i] == 'o') {
                        if (App.output.isO()) {
                            System.out.println("Команда -o была введена неоднократно");
                            System.exit(0);
                        }
                        try {
                            if (chars[i + 1] == ' ') {
                                App.output.setO(true);
                                i += 2;
                                while (chars[i] != ' ') {
                                    str += chars[i];
                                    i += 1;
                                    if (i == chars.length) {
                                        break;
                                    }
                                }
                                App.output.setPath(str);
                                str = "";
                                nameFile = "";
                            } else {
                                i = checkFile(chars, nameFile, i);
                                if (i == -1) {
                                    System.exit(0);
                                }
                                nameFile = "";
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Не был указан путь до папки");
                        }

                    } else if (chars[i] == 'p') {
                        if (App.output.isP()) {
                            System.out.println("Команда -p была введена неоднократно");
                            System.exit(0);
                        }
                        try {
                            if (chars[i + 1] == ' ') {
                                App.output.setP(true);
                                i += 2;
                                while (chars[i] != ' ') {
                                    str += chars[i];
                                    i += 1;
                                    if (i == chars.length) {
                                        break;
                                    }
                                }
                                App.output.setName(str);
                                str = "";
                                nameFile = "";
                            } else {
                                i = checkFile(chars, nameFile, i);
                                if (i == -1) {
                                    System.exit(0);
                                }
                                nameFile = "";
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Не был задан префикс имен выходных файлов");
                        }

                    } else if (chars[i] == 'f') {
                        if (App.output.isF()) {
                            System.out.println("Команда -f была введена неоднократно");
                            System.exit(0);
                        }
                        if (App.output.isS()) {
                            System.out.println("Одновременная работа команд -f и -s не допустима");
                            System.exit(0);
                        }
                        if (i + 1 == chars.length || chars[i + 1] == ' ') {
                            App.output.setF(true);
                            nameFile = "";
                            i += 1;
                        } else {
                            i = checkFile(chars, nameFile, i);
                            if (i == -1) {
                                System.exit(0);
                            }
                            nameFile = "";
                        }
                    } else if (chars[i] == 's') {
                        if (App.output.isS()) {
                            System.out.println("Команда -o была введена неоднократно");
                            System.exit(0);
                        }
                        if (App.output.isF()) {
                            System.out.println("Одновременная работа команд -f и -s не допустима");
                            System.exit(0);
                        }
                        if (i + 1 == chars.length || chars[i + 1] == ' ') {
                            App.output.setS(true);
                            nameFile = "";
                            i += 1;
                        } else {
                            i = checkFile(chars, nameFile, i);
                            if (i == -1) {
                                System.exit(0);
                            }
                            nameFile = "";
                        }
                    } else if (chars[i] == 'a') {
                        if (App.output.isA()) {
                            System.out.println("Команда -a была введена неоднократно");
                            System.exit(0);
                        }
                        if (i + 1 == chars.length || chars[i + 1] == ' ') {
                            App.output.setA(true);
                            nameFile = "";
                            i += 1;
                        } else {
                            i = checkFile(chars, nameFile, i);
                            if (i == -1) {
                                System.exit(0);
                            }
                            nameFile = "";
                        }
                    }

                    flag = false;

                } else if (chars[i] == '-') {
                    flag = true;
                } else if (!nameFile.equals(" ") && chars[i] == ' ') {
                    System.out.println("Неправильное имя файла: '" + nameFile + "'");
                    System.exit(0);
                } else if (chars[i] == ' ' || i + 1 == chars.length) {
                    System.out.println("Неправильное имя файла: '" + nameFile + "'");
                    System.exit(0);
                }
            }

        }
    }


    public static void readText() throws IOException { // убрать IOException
        FileWriter writerInt = null;
        FileWriter writerFloat = null;
        FileWriter writerString = null;
//        if (App.output.isO() && App.output.isA()) {
//            writerInt = new FileWriter(App.output.getPath() + "/" + App.output.getName() + "integers.txt", true);
//            writerFloat = new FileWriter(App.output.getPath() + "/" + App.output.getName() + "floats.txt", true);
//            writerString = new FileWriter(App.output.getPath() + "/" + App.output.getName() + "strings.txt", true);
//        } else if(App.output.isO() && App.output.isA() == false) {
//            writerInt = new FileWriter(App.output.getPath() + "/" + App.output.getName() + "integers.txt", false);
//            writerFloat = new FileWriter(App.output.getPath() + "/" + App.output.getName() + "floats.txt", false);
//            writerString = new FileWriter(App.output.getPath() + "/" + App.output.getName() + "strings.txt", false);
//        } else if(App.output.isO() == false && App.output.isA()) {
//            writerInt = new FileWriter(App.output.getName() + "integers.txt", true);
//            writerFloat = new FileWriter(App.output.getName() + "floats.txt", true);
//            writerString = new FileWriter(App.output.getName() + "strings.txt", true);
//        } else{
//            writerInt = new FileWriter(App.output.getName() + "integers.txt", false);
//            writerFloat = new FileWriter(App.output.getName() + "floats.txt", false);
//            writerString = new FileWriter(App.output.getName() + "strings.txt", false);
//        }

        boolean append;
        if (App.output.isO() && App.output.isA()) {
            App.output.setFullPath(App.output.getPath() + "/" + App.output.getName());
            append = true;
        } else if (App.output.isO() && App.output.isA() == false) {
            App.output.setFullPath(App.output.getPath() + "/" + App.output.getName());
            append = false;
        } else if (App.output.isO() == false && App.output.isA()) {
            App.output.setFullPath(App.output.getName());
            append = true;

        } else {
            App.output.setFullPath(App.output.getName());
            append = false;
        }
        try {
            writerInt = new FileWriter(App.output.getFullPath() + "integers.txt", append);
            writerFloat = new FileWriter(App.output.getFullPath() + "floats.txt", append);
            writerString = new FileWriter(App.output.getFullPath() + "strings.txt", append);
        } catch (FileNotFoundException e) {
            System.out.println("По данному пути (" + App.output.getFullPath() + ") не была найдена папка");
            System.exit(0);
        }

        BufferedReader br;
        String line;

        for (FileReader file : App.output.getFiles()) {
            try {
                br = new BufferedReader(file);
                while ((line = br.readLine()) != null) {
                    try {
                        //System.out.println("1");
                        //Integer.parseInt(line);
                        Long.parseLong(line);
                        writerInt.write(line + "\n");
//                        if (App.output.isF()) {
//                            App.output.setCountInt(App.output.getCountInt() + 1);
//                            App.output.setSumInt(App.output.getSumInt() + Integer.parseInt(line));
//                            if (App.output.isFlagInt()) {
//                                if (App.output.getMinInt() < Integer.parseInt(line)) {
//                                    App.output.setMinInt(Integer.parseInt(line));
//                                }
//                                if (App.output.getMaxInt() > Integer.parseInt(line)) {
//                                    App.output.setMaxInt(Integer.parseInt(line));
//                                }
//                            } else{
//                                App.output.setFlagInt(true);
//                                App.output.setMinInt(Integer.parseInt(line));
//                                App.output.setMaxInt(Integer.parseInt(line));
//                            }
//                        }else if(App.output.isS()){
//                            App.output.setCountInt(App.output.getCountInt() + 1);
//                        }
                    } catch (NumberFormatException e) {
                        try {
                            //System.out.println("2");
                            Float.parseFloat(line);
                            writerFloat.write(line + "\n");

//                            if (App.output.isF()) {
//                                App.output.setCountFloat(App.output.getCountFloat() + 1);
//                                App.output.setSumFloat(App.output.getSumFloat() + Float.parseFloat(line));
//                                if (App.output.isFlagFloat()) {
//                                    if (App.output.getMinFloat() < Float.parseFloat(line)) {
//                                        App.output.setMinFloat(Float.parseFloat(line));
//                                    }
//                                    if (App.output.getMaxFloat() > Float.parseFloat(line)) {
//                                        App.output.setMaxFloat(Float.parseFloat(line));
//                                    }
//                                } else{
//                                    App.output.setFlagFloat(true);
//                                    App.output.setMinFloat(Float.parseFloat(line));
//                                    App.output.setMaxFloat(Float.parseFloat(line));
//                                }
//                            }else if(App.output.isS()){
//                                App.output.setCountFloat(App.output.getCountFloat() + 1);
//                            }

                        } catch (NumberFormatException e2) {
                            //System.out.println("3");
                            writerString.write(line + "\n");

//                            if (App.output.isF()) {
//                                if (App.output.isFlagString()){
//                                    if  (App.output.getMinString() > line.length()) {
//                                        App.output.setMinString(line.length());
//                                    }else if (App.output.getMaxString() > line.length()) {
//                                        App.output.setMaxString(line.length());
//                                    }
//                                }else{
//                                    App.output.setFlagString(true);
//                                    App.output.setMinString(line.length());
//                                    App.output.setMaxString(line.length());
//
//                                }
//                            }else if (App.output.isS()){
//                                App.output.setCountString(App.output.getCountString() + 1);
//                            }
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        writerInt.close();
        writerFloat.close();
        writerString.close();


    }

    public static int checkFile(char[] chars, String nameFile, int i) {
        for (int j = i; j < chars.length; j++) {
            if (j + 1 <= chars.length - 1 && chars[j + 1] != ' ') {
                nameFile += chars[j + 1];
                if (nameFile.endsWith(".txt")) {
                    try {
                        App.output.getFiles().add(new FileReader(nameFile));
                        return j;
                    } catch (FileNotFoundException e) {
                        System.out.println("Файл '" + nameFile + "' не был найден на вашем устройстве");
                        return -1;
                    }
                }
            } else {
                System.out.println("'" + nameFile + "' - не корректное название файла");
                return -1;
            }
        }
        System.out.println("'" + nameFile + "' - не корректное название файла");
        return -1;
    }


}
