package com.haroldekb.costviewer.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

/**
 * @author haroldekb@mail.ru
 **/

public class Parser {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    //"^[0-9]{2}[.][0-9]{2}[.][0-9]{4}"
    private final static String BILL_REG_EXP = "^[0-9]{2}[.][0-9]{2}[.][0-9]{4}";
    public final static Pattern BILL_PATTERN = Pattern.compile(BILL_REG_EXP);
    private final static Pattern COMMA_PATTERN = Pattern.compile("[,]");

    public static void parse(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String note;

        boolean skip = false;
        while (reader.ready()){
            note = reader.readLine();
            if (!skip) {
                if (BILL_PATTERN.matcher(note).find()){
                    parseAsBill(note);
                } else if (note.startsWith("{")){
                    skip = true;
                }
            } else {
                if (note.startsWith("}")) {
                    skip = false;
                }
            }
        }
    }

    private static void parseAsBill(String note) {
        String[] str = note.split(" ", 4);
        LocalDate date = LocalDate.parse(str[0], formatter);
        double cost = Double.parseDouble(COMMA_PATTERN.matcher(str[2]).replaceAll("."));
        if (str[1].equals("-")) cost *= -1;

        System.out.println(date.format(formatter) + " " + cost + str[3].replaceFirst(" ?р?[.]? ?-", " -"));
    }
}
