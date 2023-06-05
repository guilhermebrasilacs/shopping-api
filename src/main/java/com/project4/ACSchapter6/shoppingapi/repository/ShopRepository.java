package com.project4.ACSchapter6.shoppingapi.repository;

import java.time.LocalDateTime;
import	java.util.Date;
import	java.util.List;

import	org.springframework.data.jpa.repository.JpaRepository;
import	org.springframework.stereotype.Repository;

import	com.project4.ACSchapter6.shoppingapi.model.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long>, ReportRepository{
    public List<Shop> findAllByUserIdentifier(String userIdentifier);
    public List<Shop> findAllByTotalGreaterThan(Float total);
    public List<Shop> findAllByDateGreaterThan(LocalDateTime date);
}
