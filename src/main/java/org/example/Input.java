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
        for (int i = 0; i < chars.length; i++) {

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
                            System.out.println("Одновременная работа команд -s и -f не допустима");
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

                } else if (chars[i] == '-' && nameFile.equals("-")) {
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


    public static void readText(){
        FileWriter writerInt = null;
        FileWriter writerFloat = null;
        FileWriter writerString = null;



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


        BufferedReader br;
        String line;

        for (FileReader file : App.output.getFiles()) {
            try {
                br = new BufferedReader(file);
                while ((line = br.readLine()) != null) {
                    try {
                        Long.parseLong(line);
                        if (!App.output.isIntIsCreated()) {
                            try {
                                writerInt = new FileWriter(App.output.getFullPath() + "integers.txt", append);
                                App.output.setIntIsCreated(true);
                            } catch (IOException e11) {
                                System.out.println("По данному пути (" + App.output.getFullPath() + ") не была найдена папка");
                                System.exit(0);
                            }
                        }
                        writerInt.write(line + "\n");

                    } catch (NumberFormatException e) {
                        try {
                            Float.parseFloat(line);
                            if (!App.output.isFloatIsCreated()) {
                                try {
                                    writerFloat = new FileWriter(App.output.getFullPath() + "floats.txt", append);
                                    App.output.setFloatIsCreated(true);
                                } catch (IOException e12) {
                                    System.out.println("По данному пути (" + App.output.getFullPath() + ") не была найдена папка");
                                    System.exit(0);
                                }
                            }
                            writerFloat.write(line + "\n");

                        } catch (NumberFormatException e1) {
                            if (!App.output.isStringIsCreated()) {
                                try {
                                    writerString = new FileWriter(App.output.getFullPath() + "strings.txt", append);
                                    App.output.setStringIsCreated(true);
                                } catch (IOException e13) {
                                    System.out.println("По данному пути (" + App.output.getFullPath() + ") не была найдена папка");
                                    System.exit(0);
                                }
                            }
                            writerString.write(line + "\n");

                        }
                    }
                }


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        try {
            if (App.output.isIntIsCreated())
                writerInt.close();
            if (App.output.isFloatIsCreated())
                writerFloat.close();
            if (App.output.isStringIsCreated())
                writerString.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


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
