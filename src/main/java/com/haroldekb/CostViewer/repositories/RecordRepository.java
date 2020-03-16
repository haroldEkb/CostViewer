package com.haroldekb.costviewer.repositories;

import com.haroldekb.costviewer.entities.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {
}
