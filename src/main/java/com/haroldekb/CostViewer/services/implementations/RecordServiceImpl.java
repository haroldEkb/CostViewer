package com.haroldekb.costviewer.services.implementations;

import com.haroldekb.costviewer.entities.Record;
import com.haroldekb.costviewer.entities.User;
import com.haroldekb.costviewer.repositories.RecordRepository;
import com.haroldekb.costviewer.services.ParseService;
import com.haroldekb.costviewer.services.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author haroldekb@mail.ru
 **/

@Service
public class RecordServiceImpl implements RecordService {

    private ParseService parseService;
    private RecordRepository recordRepository;

    @Autowired
    public void setParseService(ParseService parseService) {
        this.parseService = parseService;
    }

    @Autowired
    public void setRecordRepository(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public void saveRecordsFromFile(User user, String fileName) {
        List<Record> recordList = new ArrayList<>();
        try {
            recordList = parseService.parseNote(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        recordList.forEach(record -> record.setUser(user));

        recordRepository.saveAll(recordList);
    }
}
