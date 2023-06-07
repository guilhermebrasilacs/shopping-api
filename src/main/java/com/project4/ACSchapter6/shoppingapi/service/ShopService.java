package com.project4.ACSchapter6.shoppingapi.service;

import java.time.LocalDate;
import	java.time.LocalDateTime;
import	java.util.List;
import	java.util.Optional;
import	java.util.stream.Collectors;

import com.project4.ACSchapter6.shoppingapi.dto.DTOconverter;
import com.project4.ACSchapter6.shoppingapi.dto.ItemDTO;
import com.project4.ACSchapter6.shoppingapi.dto.ShopReportDTO;
import com.project5.ACSchapter8.shoppingclient.dto.ProductDTO;
import com.project5.ACSchapter8.shoppingclient.dto.UserDTO;
import	lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import	org.springframework.stereotype.Service;

import	com.project4.ACSchapter6.shoppingapi.dto.ShopDTO;
import	com.project4.ACSchapter6.shoppingapi.model.Shop;
import	com.project4.ACSchapter6.shoppingapi.repository.ShopRepository;
import	com.project4.ACSchapter6.shoppingapi.repository.ReportRepository;

@Service
@RequiredArgsConstructor
public class ShopService {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private final ShopRepository shopRepository;
    private final ReportRepository reportRepository;

    public List<ShopDTO> getAll(){
        List<Shop> shops = shopRepository.findAll();
        return shops.stream().map(DTOconverter::convert).collect(Collectors.toList());
    }

    public List<ShopDTO> getByUser(String userIdentifier){
        List<Shop> shops = shopRepository.findAllByUserIdentifier(userIdentifier);
        return shops.stream().map(DTOconverter::convert).collect(Collectors.toList());
    }

    public List<ShopDTO> getByDate(ShopDTO shopDTO){
        List<Shop> shops = shopRepository.findAllByDateGreaterThan(shopDTO.getDate());
        return shops.stream().map(DTOconverter::convert).collect(Collectors.toList());
    }


    public List<ShopDTO> getShopsByFilters(LocalDate dataInicio, LocalDate dataFim, Float valorMinimo){
        List<Shop> shops = reportRepository.getShopsByFilters(dataInicio, dataFim, valorMinimo);
        return shops.stream().map(DTOconverter::convert).collect(Collectors.toList());
    }

    public ShopDTO findById(long productId){
        Optional<Shop> shop = shopRepository.findById(productId);
        if(shop.isPresent()){
            return DTOconverter.convert(shop.get());
        }
        return null;
    }

    public ShopDTO save(ShopDTO shopDTO, String key){
        UserDTO userDTO = userService.getUserByCpf(shopDTO.getUserIdentifier(), key);
        validateProducts(shopDTO.getItems());
        shopDTO.setTotal(shopDTO.getItems().stream().map(x -> x.getPrice()).reduce((float) 0 , Float::sum));
        Shop shop = Shop.convert(shopDTO);
        shop.setDate(LocalDateTime.now());

        shop = shopRepository.save(shop);
        return DTOconverter.convert(shop);
    }
    private boolean validateProducts(List<ItemDTO> items){
        for(ItemDTO item : items){
            ProductDTO productDTO = productService.getProductIdentifier(item.getProductIdentifier());
            if(productDTO == null){
                return false;
            }
            item.setPrice(productDTO.getPreco());
        }
        return true;
    }

    public ShopReportDTO getReportByDate(LocalDate dataInicio, LocalDate dataFim){
        return reportRepository.getReportByDate(dataInicio, dataFim);
    }

}
