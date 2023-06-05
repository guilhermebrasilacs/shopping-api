package com.project4.ACSchapter6.shoppingapi.controller;

import	java.util.List;

import jakarta.validation.Valid;
import	lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import	com.project4.ACSchapter6.shoppingapi.dto.ShopDTO;
import	com.project4.ACSchapter6.shoppingapi.service.ShopService;

@RestController
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;

    @GetMapping("/shopping")
    public List<ShopDTO> getShops(){
        return shopService.getAll();
    }
    @GetMapping("/shopping/shopByUser/{userIdentifier}")
    public List<ShopDTO> getShops(@PathVariable String userIdentifier){
        return  shopService.getByUser(userIdentifier);
    }
    @GetMapping("/shopping/shopByDate")
    public List<ShopDTO> getShops(@RequestBody ShopDTO shopDTO){
        return shopService.getByDate(shopDTO);
    }
    @GetMapping("/shopping/{id}")
    public ShopDTO findById(@PathVariable Long id){
        return shopService.findById(id);
    }

    @PostMapping("/shopping")
    @ResponseStatus(HttpStatus.CREATED)
    public ShopDTO newShop(@Valid @RequestBody ShopDTO shopDTO){
        return shopService.save(shopDTO);
    }
}
