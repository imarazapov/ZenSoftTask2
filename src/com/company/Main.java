package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws Exception{

        String file1 = "/media/hanaria/A0C43646C4361F4A1/Java Rush/fileReadWrite/pattern.txt";
        String file2 = "/media/hanaria/A0C43646C4361F4A1/Java Rush/fileReadWrite/input.txt";

        List<String> patternList = new ArrayList<String>();
        List<String> inputList = new ArrayList<String>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader patternReader = new BufferedReader(new FileReader(file1));
        BufferedReader inputReader = new BufferedReader(new FileReader(file2));
        bufferedReader.close();


        while (patternReader.ready())  patternList.addAll(Arrays.asList(patternReader.readLine().split("\n")));
        while (inputReader.ready()) inputList.addAll(Arrays.asList(inputReader.readLine().split("\n")));

        System.out.println("---Pattern---");
        for(String str: patternList) System.out.println(str);

        System.out.println("---Input---");
        for(String str: inputList) System.out.println(str);

        System.out.println("---match exactly any pattern---");
        matchExactly(patternList, inputList);
        System.out.println("---match optional 1---");
        optional1(patternList, inputList);

        System.out.println("---match optional 2---");
        List<String> patternVariants = new ArrayList<String>();
        patternVariants=pattenExceptOne(patternList);
//        for(String str: patternVariants) System.out.println(str);
        matchExactly(patternVariants, inputList);

    }

    public static void matchExactly(List<String> pattern, List<String> input){
        for (int i = 0; i < pattern.size(); i++) {
            for (int j = 0; j < input.size(); j++) {
                if(pattern.get(i).equals(input.get(j))) System.out.println(input.get(j));
            }
        }

    }
    public static void optional1(List<String> pattern, List<String> input){

        for (int i = 0; i < pattern.size(); i++) {
            for (int j = 0; j < input.size(); j++) {
                if(input.get(j).matches("(.*)" + pattern.get(i) + "(.*)"))
                    System.out.println(input.get(j));

            }
        }
    }


    public static List<String> pattenExceptOne(List<String> pattern){
        String pat="";
        List<String> list= new ArrayList<String>();
        for (int i = 0; i < pattern.size(); i++) {
            list.add(pattern.get(i));
            char[] patternArray = pattern.get(i).toCharArray();
//            for(char c: patternArray) System.out.print(c);

            for (int k = 0; k < patternArray.length; k++)
            {

                for (int j = 0; j < patternArray.length; j++)
                {
                    if(k==j) continue;
                    else pat = pat + patternArray[j];
                }

                list.add(pat);
//                System.out.println(pat);
                pat="";
            }
        }
        /*if string has double char symbol*/
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if(list.get(i).equals(list.get(j))) {
                    count = count + 1;
                    if (count==2) list.remove(i);
                }
            }count = 0;
        }
        return list;
    }
}
