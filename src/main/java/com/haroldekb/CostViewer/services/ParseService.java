package com.haroldekb.costviewer.services;

import com.haroldekb.costviewer.entities.Record;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface ParseService {
    List<Record> parseNote(String fileName) throws IOException;
}
