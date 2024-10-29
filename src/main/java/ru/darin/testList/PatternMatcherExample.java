package ru.darin.testList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcherExample {
    public static void main(String[] args) {
        String text = "Hello guys! I send you my email joe@gmail.com, so we can keep in touch." +
                "Thanks Joe, that's cool. I am sending to you my address: tim@yandex.ru. Let's stay in touch ...";

        Pattern email = Pattern.compile("(\\w+)@(gmail|yandex)\\.(com|ru)");
        Matcher matcher = email.matcher(text);

        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}












