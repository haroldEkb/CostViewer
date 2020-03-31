package com.haroldekb.costviewer.controllers;

import com.haroldekb.costviewer.entities.Record;
import com.haroldekb.costviewer.services.RecordService;
import com.haroldekb.costviewer.utils.RecordFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author haroldekb@mail.ru
 **/

@Controller
@RequestMapping("/records")
public class RecordController {
    private RecordService recordService;
    private RecordFilter recordFilter;

    @Autowired
    public void setRecordService(RecordService recordService) {
        this.recordService = recordService;
    }

    @Autowired
    public void setRecordFilter(RecordFilter recordFilter) {
        this.recordFilter = recordFilter;
    }

    @GetMapping("/{userId}")
    public String showRecords(@PathVariable("userId") Integer id, Model model, HttpServletRequest request,
                              @RequestParam(name = "pageNumber", required = false) Integer pageNumber){
        if (pageNumber == null || pageNumber < 1){
            pageNumber = 1;
        }

        recordFilter.takeRequest(request);
        Page<Record> page = recordService.findAllByPagingAndFiltering(recordFilter.getSpecification(), PageRequest.of(pageNumber - 1, 10, Sort.Direction.ASC, "id"));
        model.addAttribute("page", page);
        model.addAttribute("userId", id);
        model.addAttribute("filters", recordFilter.getFiltersString());


        model.addAttribute("records", recordService.findRecordsByUserId(Long.valueOf(id)));
        return "records";
    }
}
