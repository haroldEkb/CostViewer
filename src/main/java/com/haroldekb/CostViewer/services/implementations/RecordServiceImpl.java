package com.haroldekb.costviewer.services.implementations;

import com.haroldekb.costviewer.entities.Record;
import com.haroldekb.costviewer.entities.User;
import com.haroldekb.costviewer.repositories.RecordRepository;
import com.haroldekb.costviewer.services.ParseService;
import com.haroldekb.costviewer.services.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    public List<Record> findRecordsByUserId(Long id) {
        return recordRepository.findAllByUserId(id);
    }

    public Page<Record> findAllByPagingAndFiltering(Specification<Record> spec, Pageable pageable){
        return recordRepository.findAll(spec, pageable);
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
