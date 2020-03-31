package com.haroldekb.costviewer.services;

import com.haroldekb.costviewer.entities.Record;
import com.haroldekb.costviewer.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface RecordService{
    List<Record> findRecordsByUserId(Long id);
    void saveRecordsFromFile(User user, String fileName);
    Page<Record> findAllByPagingAndFiltering(Specification<Record> spec, Pageable pageable);
}
