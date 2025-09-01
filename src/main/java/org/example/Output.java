package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Output {
    private boolean o = false;
    private boolean p = false;
    private boolean s = false;
    private boolean f = false;
    private boolean a = false;

    private boolean flagInt = false, flagFloat = false, flagString = false;
    private boolean IntIsCreated = false,  FloatIsCreated = false, StringIsCreated = false;

    private int countInt = 0, countFloat = 0, countString = 0;
    private long sumInt;
    private float sumFloat = 0;
    private int minString, maxString;
    private long minInt, maxInt;
    private float minFloat, maxFloat;

    private String fullPath;

    private ArrayList<FileReader> files = new ArrayList<>();

    private ArrayList<Long>  integers = new ArrayList<>();
    private ArrayList<Float> floats = new ArrayList<>();
    private ArrayList<String> strings = new ArrayList<>();

    private String path, name = "";


    private BufferedReader brString = null, brFloat = null, brInt = null;

    public void outputF() {
        metodF();
        if (isIntIsCreated()) {
            System.out.println("Статистика файла " + fullPath + "integers.txt");
            System.out.println("Кол-во элементов: " + countInt);
            System.out.println("Сумма элементов: " + sumInt);
            System.out.println("Среднее значение: " + sumInt / countInt);
            System.out.println("Максимальное значение: " + maxInt);
            System.out.println("Минимальное значение: " + minInt + '\n');
        }
        if (isFloatIsCreated()) {
            System.out.println("Статистика файла " + fullPath + "floats.txt");
            System.out.println("Кол-во элементов: " + countFloat);
            System.out.println("Сумма элементов: " + sumFloat);
            System.out.println("Среднее значение: " + sumFloat / countFloat);
            System.out.println("Максимальное значение: " + maxFloat);
            System.out.println("Минимальное значение: " + minFloat + '\n');
        }
        if (isStringIsCreated()) {
            System.out.println("Статистика файла " + fullPath + "strings.txt");
            System.out.println("Кол-во элементов: " + countString);
            System.out.println("Размер самой короткой строки: " + minString);
            System.out.println("Размер самой длинной строки: " + maxString);
        }
    }

    public void outputS() {
        metodS();
        if (isIntIsCreated()) {
            System.out.println("Статистика файла " + fullPath + "integers.txt");
            System.out.println("Кол-во элементов: " + countInt);
        }

        if (isFloatIsCreated()) {
            System.out.println("Статистика файла " + fullPath + "floats.txt");
            System.out.println("Кол-во элементов: " + countFloat);
        }

        if (isStringIsCreated()) {
            System.out.println("Статистика файла " + fullPath + "strings.txt");
            System.out.println("Кол-во элементов: " + countString);
        }
    }

    public void metodS() {
        int count = 0;
        String line;

        try {
            if (isIntIsCreated()) {
                brInt = new BufferedReader(new FileReader(fullPath + "integers.txt"));
                while ((line = brInt.readLine()) != null) {
                    countInt += 1;
                }
            }
            if (isFloatIsCreated()) {
                brFloat = new BufferedReader(new FileReader(fullPath + "floats.txt"));
                while ((line = brFloat.readLine()) != null) {
                    countFloat += 1;
                }
            }
            if (isStringIsCreated()) {
                brString = new BufferedReader(new FileReader(fullPath + "strings.txt"));
                while ((line = brString.readLine()) != null) {
                    countString += 1;
                }
            }



        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void metodF() {
        int count = 0;
        String line;

        try {
            if (isIntIsCreated()) {
                brInt = new BufferedReader(new FileReader(fullPath + "integers.txt"));
                while ((line = brInt.readLine()) != null) {
                    sumInt += Long.parseLong(line);
                    if (flagInt) {
                        if (Long.parseLong(line) < minInt) {
                            minInt = Long.parseLong(line);
                        } else if (Long.parseLong(line) > maxInt) {
                            maxInt = Long.parseLong(line);
                        }
                    } else {
                        minInt = Long.parseLong(line);
                        maxInt = Long.parseLong(line);
                        flagInt = true;
                    }
                    countInt += 1;
                }
            }
            if (isFloatIsCreated()) {
                brFloat = new BufferedReader(new FileReader(fullPath + "floats.txt"));
                while ((line = brFloat.readLine()) != null) {
                    sumFloat += Float.parseFloat(line);
                    if (flagFloat) {
                        if (Float.parseFloat(line) < minFloat) {
                            minFloat = Float.parseFloat(line);
                        } else if (Float.parseFloat(line) > maxFloat) {
                            maxFloat = Float.parseFloat(line);
                        }
                    } else {
                        minFloat = Float.parseFloat(line);
                        maxFloat = Float.parseFloat(line);
                        flagFloat = true;
                    }
                    countFloat += 1;
                }
            }
            if (isStringIsCreated()) {
                brString = new BufferedReader(new FileReader(fullPath + "strings.txt"));
                while ((line = brString.readLine()) != null) {

                    if (flagString) {
                        if (line.length() < minString) {
                            minString = line.length();
                        } else if (line.length() > maxString) {
                            maxString = line.length();
                        }
                    } else {
                        maxString = line.length();
                        minString = line.length();
                        flagString = true;
                    }

                    countString += 1;
                }
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    public Output() {

    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public boolean isFlagInt() {
        return flagInt;
    }

    public void setFlagInt(boolean flagInt) {
        this.flagInt = flagInt;
    }

    public boolean isFlagFloat() {
        return flagFloat;
    }

    public boolean isIntIsCreated() {
        return IntIsCreated;
    }

    public void setIntIsCreated(boolean intIsCreated) {
        IntIsCreated = intIsCreated;
    }

    public boolean isFloatIsCreated() {
        return FloatIsCreated;
    }

    public void setFloatIsCreated(boolean floatIsCreated) {
        FloatIsCreated = floatIsCreated;
    }

    public boolean isStringIsCreated() {
        return StringIsCreated;
    }

    public void setStringIsCreated(boolean stringIsCreated) {
        StringIsCreated = stringIsCreated;
    }

    public void setFlagFloat(boolean flagFloat) {
        this.flagFloat = flagFloat;
    }

    public ArrayList<Long> getIntegers() {
        return integers;
    }

    public void setIntegers(ArrayList<Long> integerss) {
        this.integers = integerss;
    }

    public ArrayList<Float> getFloats() {
        return floats;
    }

    public void setFloats(ArrayList<Float> floats) {
        this.floats = floats;
    }

    public ArrayList<String> getStrings() {
        return strings;
    }

    public void setStrings(ArrayList<String> strings) {
        this.strings = strings;
    }

    public boolean isFlagString() {
        return flagString;
    }

    public void setFlagString(boolean flagString) {
        this.flagString = flagString;
    }

    public int getCountInt() {
        return countInt;
    }

    public void setCountInt(int countInt) {
        this.countInt = countInt;
    }

    public int getCountFloat() {
        return countFloat;
    }

    public void setCountFloat(int countFloat) {
        this.countFloat = countFloat;
    }

    public int getCountString() {
        return countString;
    }

    public void setCountString(int countString) {
        this.countString = countString;
    }

    public long getSumInt() {
        return sumInt;
    }

    public void setSumInt(long sumInt) {
        this.sumInt = sumInt;
    }

    public float getSumFloat() {
        return sumFloat;
    }

    public void setSumFloat(float sumFloat) {
        this.sumFloat = sumFloat;
    }

    public float getMinFloat() {
        return minFloat;
    }

    public void setMinFloat(float minFloat) {
        this.minFloat = minFloat;
    }

    public float getMaxFloat() {
        return maxFloat;
    }

    public void setMaxFloat(float maxFloat) {
        this.maxFloat = maxFloat;
    }

    public long getMinInt() {
        return minInt;
    }

    public void setMinInt(long minInt) {
        this.minInt = minInt;
    }

    public long getMaxInt() {
        return maxInt;
    }

    public void setMaxInt(long maxInt) {
        this.maxInt = maxInt;
    }

    public int getMaxString() {
        return maxString;
    }

    public void setMaxString(int maxString) {
        this.maxString = maxString;
    }

    public int getMinString() {
        return minString;
    }

    public void setMinString(int minString) {
        this.minString = minString;
    }

    public ArrayList<FileReader> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<FileReader> files) {
        this.files = files;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isA() {
        return a;
    }

    public void setA(boolean a) {
        this.a = a;
    }

    public boolean isO() {
        return o;
    }

    public void setO(boolean o) {
        this.o = o;
    }

    public boolean isP() {
        return p;
    }

    public void setP(boolean p) {
        this.p = p;
    }

    public boolean isS() {
        return s;
    }

    public void setS(boolean s) {
        this.s = s;
    }

    public boolean isF() {
        return f;
    }

    public void setF(boolean f) {
        this.f = f;
    }
}
