package com.project4.ACSchapter6.shoppingapi.repository;

import com.project4.ACSchapter6.shoppingapi.dto.ShopReportDTO;
import com.project4.ACSchapter6.shoppingapi.model.Shop;
import	jakarta.persistence.EntityManager;
import	jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.time.LocalDate;
import java.util.List;

public class ReportRepositoryImpl implements ReportRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Shop> getShopsByFilters(LocalDate dataInicio, LocalDate dataFim, Float valorMinimo) {
        StringBuilder sb = new StringBuilder();
        sb.append("select s ");
        sb.append("from shop s ");
        sb.append("where s.date >= dataInicio ");

        if(dataFim != null){
            sb.append("and s.date <= dataFim ");
        }
        if(valorMinimo != null){
            sb.append("and s.total <= valorMinimo");
        }

        Query query = entityManager.createQuery(sb.toString());
        query.setParameter("dataInicio", dataInicio.atTime(0, 0));
        if(dataFim != null){
            query.setParameter("dataFim", dataFim.atTime(23, 59));
        }
        if(valorMinimo != null){
            query.setParameter("valorMinimo", valorMinimo);
        }

        return query.getResultList();
    }

    @Override
    public ShopReportDTO getReportByDate(LocalDate dataInicio, LocalDate dataFim) {
        return null;
    }
}
