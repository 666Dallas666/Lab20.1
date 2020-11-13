package com.company;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
class Text {
    private final HashMap<String, Integer> Words = new HashMap<>();
    String[] Exceptions = {".",",","(", ")", "!", "?", "\"", ";", ":","[", "]","<", ">"};
    public ArrayList<String> fromTextToArray(String line){
        ArrayList<String> words = new ArrayList<>();
        String[] buf = line.split(" ");
        StringBuilder temp;
        int index;
        for (String word: buf) {
            if (!word.equals("—") && !word.equals("-") && !word.equals("")) {
                temp = new StringBuilder(word);
                for (String s1 : Exceptions) {
                    index = temp.indexOf(s1);
                    if (index != -1)
                        temp.deleteCharAt(index);
                }
                word = temp.toString();
                words.add(word);
            }
        }
        return words;
    }
    public void TenWords(){
        HashMap<String, Integer> buff = new HashMap<>();
        String key = "";
        Integer value = 0;
        for (int i = 0; i < 10; i++){
            value = 0;
            for (HashMap.Entry<String, Integer> pair : Words.entrySet()){
                if (!buff.containsKey(pair.getKey()) && pair.getValue() > value){
                    key = pair.getKey();
                    value = pair.getValue();
                }
            }
            buff.put(key, value);
            System.out.println(i + 1 + ". \'" + key + "\' - " + value + " раз");
        }
    }
    public void Sum(ArrayList<String> words){
        for (String word: words){
            word = word.toLowerCase();
            Words.computeIfPresent(word, (key, value) -> value + 1);
            Words.putIfAbsent(word, 1);
        }
    }
    public void Input(String path){
        try {
            FileReader reader = new FileReader(path);
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine())
                Sum(fromTextToArray(scanner.nextLine()));
            reader.close();
        }
        catch (IOException ignored){}
    }
    public void Replacer(String path){
        try {
            FileReader reader = new FileReader(path);
            FileWriter writer = new FileWriter("C:\\Users\\Home\\Desktop\\out.txt");
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine())
                writer.write(Replacer1('#', scanner.nextLine()));
            reader.close();
            writer.flush();
            writer.close();
        }
        catch (IOException ignored){}
    }
    public String Replacer1(char symbol, String line){
        return line.replace(' ', symbol);
    }
}
public class Main {
    public static void main(String[] args) {
        Text myText = new Text();
        myText.Input("C:\\Users\\Home\\Desktop\\text.txt");
        myText.TenWords();
        myText.Replacer("C:\\Users\\Home\\Desktop\\text.txt");
    }
}