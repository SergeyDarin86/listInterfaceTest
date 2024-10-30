package ru.darin.testList;

import java.util.Arrays;

public class RegexExample {
    public static void main(String[] args) {
        String str = "-673";
        String str2 = "+456";
        System.out.println(str2.matches("(-|\\+)?\\d*"));

        System.out.println();
        String str3 = "srwerfE34345sR";
        String regex = "[\\w]+\\d+[a-zA-Z]+";
        System.out.println(str3.matches(regex));
        System.out.println();

        String str4 = "sdfs";
        String regex1 = "[^abc]+";
        System.out.println(str4.matches(regex1));

        String url = "http://www.google.ru";
        String regexUrl = "http://www\\..+\\.(com|ru)";
        System.out.println(url.matches(regexUrl));

        // регулярное выражение для телефонного номера
        String phoneNumber = "+78198172403";
        String numberRegex = "(\\+7|8)\\d{10}";
        System.out.println(phoneNumber.matches(numberRegex));

        String phrase = "Hello!3434My344name55is222Sergey!";
        String[] words = phrase.split("\\d+");
        System.out.println(Arrays.toString(words));

        String modifiedPhrase = phrase.replace(" ", ".");
        System.out.println(modifiedPhrase);

        String modifiedPhrase2 = phrase.replaceAll("\\d+"," - ");
        System.out.println(modifiedPhrase2);
    }
}
