package com.project4.ACSchapter6.shoppingapi.service;

import java.time.LocalDate;
import	java.time.LocalDateTime;
import	java.util.List;
import	java.util.Optional;
import	java.util.stream.Collectors;

import com.project4.ACSchapter6.shoppingapi.dto.ShopReportDTO;
import	lombok.RequiredArgsConstructor;

import	org.springframework.stereotype.Service;

import	com.project4.ACSchapter6.shoppingapi.dto.ShopDTO;
import	com.project4.ACSchapter6.shoppingapi.model.Shop;
import	com.project4.ACSchapter6.shoppingapi.repository.ShopRepository;
import	com.project4.ACSchapter6.shoppingapi.repository.ReportRepository;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;
    private final ReportRepository reportRepository;

    public List<ShopDTO> getAll(){
        List<Shop> shops = shopRepository.findAll();
        return shops.stream().map(ShopDTO::convert).collect(Collectors.toList());
    }

    public List<ShopDTO> getByUser(String userIdentifier){
        List<Shop> shops = shopRepository.findAllByUserIdentifier(userIdentifier);
        return shops.stream().map(ShopDTO::convert).collect(Collectors.toList());
    }

    public List<ShopDTO> getByDate(ShopDTO shopDTO){
        List<Shop> shops = shopRepository.findAllByDateGreaterThan(shopDTO.getDate());
        return shops.stream().map(ShopDTO::convert).collect(Collectors.toList());
    }


    public List<ShopDTO> getShopsByFilters(LocalDate dataInicio, LocalDate dataFim, Float valorMinimo){
        List<Shop> shops = reportRepository.getShopsByFilters(dataInicio, dataFim, valorMinimo);
        return shops.stream().map(ShopDTO::convert).collect(Collectors.toList());
    }

    public ShopDTO findById(long productId){
        Optional<Shop> shop = shopRepository.findById(productId);
        if(shop.isPresent()){
            return ShopDTO.convert(shop.get());
        }
        return null;
    }

    public ShopDTO save(ShopDTO shopDTO){
        shopDTO.setTotal(shopDTO.getItems().stream().map(x -> x.getPrice()).reduce((float) 0 , Float::sum));
        Shop shop = Shop.convert(shopDTO);
        shop.setDate(LocalDateTime.now());

        shop = shopRepository.save(shop);
        return ShopDTO.convert(shop);
    }

    public ShopReportDTO getReportByDate(LocalDate dataInicio, LocalDate dataFim){
        return reportRepository.getReportByDate(dataInicio, dataFim);
    }

}
