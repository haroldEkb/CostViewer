package com.haroldekb.costviewer.repositories.specifications;

import com.haroldekb.costviewer.entities.Record;
import com.haroldekb.costviewer.entities.User;
import com.haroldekb.costviewer.services.RecordService;
import com.haroldekb.costviewer.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDate;

/**
 * @author haroldekb@mail.ru
 */

@Component
@SessionScope
public class RecordSpecifications {

    private RecordService recordService;

    @Autowired
    public void setAuthorService(RecordService recordService) {
        this.recordService = recordService;
    }

    public Specification<Record> incomeGreaterThan(Double value){
        return (Specification<Record>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("value"),
                value == null ? 0.0 : value);
    }

    public Specification<Record> costsGreaterThan(Double value){
        return (Specification<Record>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThan(root.get("value"),
                value == null ? 0.0 : -value);
    }

    public Specification<Record> commentLike(String comment){
        return (Specification<Record>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("comment"), "%" + comment + "%");
    }

    public Specification<Record> monthEquals(Integer month){
        return (Specification<Record>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("date"), "%-" + month + "-%");
    }

    public Specification<Record> yearEquals(Integer year){
        return (Specification<Record>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("date"), year + "-%");
    }
}
