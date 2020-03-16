package com.haroldekb.costviewer.services;

import com.haroldekb.costviewer.entities.User;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface RecordService{
    void saveRecordsFromFile(User user, String fileName);
}
