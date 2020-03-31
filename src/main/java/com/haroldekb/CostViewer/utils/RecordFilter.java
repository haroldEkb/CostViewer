package com.haroldekb.costviewer.utils;

import com.haroldekb.costviewer.entities.Record;
import com.haroldekb.costviewer.repositories.specifications.RecordSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.http.HttpServletRequest;

/**
 * @author haroldekb@mail.ru
 */

@Component
@SessionScope
public class RecordFilter {

    private Specification<Record> specification;
    private StringBuilder filtersString;
    private RecordSpecifications recordSpecifications;

    @Autowired
    public void setAuthorSpecifications(RecordSpecifications recordSpecifications) {
        this.recordSpecifications = recordSpecifications;
    }

    public Specification<Record> getSpecification() {
        return specification;
    }

    public StringBuilder getFiltersString() {
        return filtersString;
    }

    public void takeRequest(HttpServletRequest request){
        filtersString = new StringBuilder();
        specification = Specification.where(null);

        if (request.getParameter("minIncome") != null && !request.getParameter("minIncome").isEmpty()){
            specification = specification.and(recordSpecifications.incomeGreaterThan(Double.valueOf(request.getParameter("minIncome"))));
            filtersString.append("&minIncome=").append(request.getParameter("minIncome"));
        }

        if (request.getParameter("minCosts") != null && !request.getParameter("minCosts").isEmpty()){
            specification = specification.and(recordSpecifications.costsGreaterThan(Double.valueOf(request.getParameter("minCosts"))));
            filtersString.append("&minCosts=").append(request.getParameter("minCosts"));
        }

        if (request.getParameter("comment") != null && !request.getParameter("comment").isEmpty()){
            specification = specification.and(recordSpecifications.commentLike("%" + request.getParameter("comment") + "%"));
            filtersString.append("&comment=").append(request.getParameter("comment"));
        }

        if (request.getParameter("year") != null && !request.getParameter("year").isEmpty()){
            specification = specification.and(recordSpecifications.yearEquals(Integer.valueOf(request.getParameter("year"))));
            filtersString.append("&year=").append(request.getParameter("year"));
        }

        if (request.getParameter("month") != null && !request.getParameter("month").isEmpty()){
            specification = specification.and(recordSpecifications.monthEquals(Integer.valueOf(request.getParameter("month"))));
            filtersString.append("&month=").append(request.getParameter("month"));
        }
    }
}
