package com.project4.ACSchapter6.shoppingapi.repository;

import	java.time.LocalDate;
import	java.util.List;
import	com.project4.ACSchapter6.shoppingapi.dto.ShopReportDTO;
import	com.project4.ACSchapter6.shoppingapi.model.Shop;

public interface ReportRepository {
    public List<Shop> getShopsByFilters(LocalDate dataInicio, LocalDate dataFim, Float valorMinimo);
    public ShopReportDTO getReportByDate(LocalDate dataInicio, LocalDate dataFim);
}
