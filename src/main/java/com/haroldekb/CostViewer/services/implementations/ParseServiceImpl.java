package com.haroldekb.costviewer.services.implementations;

import com.haroldekb.costviewer.entities.Record;
import com.haroldekb.costviewer.services.ParseService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author haroldekb@mail.ru
 **/

@Service
public class ParseServiceImpl implements ParseService {
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final static Pattern DATE_PATTERN = Pattern.compile("^[0-9]{2}[.][0-9]{2}[.][0-9]{4}");
    private final static Pattern COMMA_PATTERN = Pattern.compile("[,]");

    @Override
    public List<Record> parseNote(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String note;
        List<Record> records = new ArrayList<>();

        boolean skip = false;
        while (reader.ready()){
            note = reader.readLine();
            if (!skip) {
                if (DATE_PATTERN.matcher(note).find()){
                    records.add(parseAsRecord(note));
                } else if (note.startsWith("{")){
                    skip = true;
                }
            } else {
                if (note.startsWith("}")) {
                    skip = false;
                }
            }
        }
        return records;
    }

    private static Record parseAsRecord(String note) {
        if (note == null) throw new NullPointerException();

        Record record = new Record();
        String[] str = note.split(" ", 4);
        LocalDate date = LocalDate.parse(str[0], FORMATTER);
        double cost = Double.parseDouble(COMMA_PATTERN.matcher(str[2]).replaceAll("."));
        if (str[1].equals("-")) cost *= -1;

        record.setDate(date);
        record.setValue(cost);
        record.setComment(str[3].replaceFirst("^ ?р?[.]? ?- ", ""));

        return record;
    }
}
