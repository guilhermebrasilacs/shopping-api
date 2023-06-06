package com.project4.ACSchapter6.shoppingapi.dto;

import com.project4.ACSchapter6.shoppingapi.dto.ShopDTO;
import com.project4.ACSchapter6.shoppingapi.dto.ItemDTO;
import com.project4.ACSchapter6.shoppingapi.model.Item;
import com.project4.ACSchapter6.shoppingapi.model.Shop;

public class DTOconverter {
    public static ItemDTO convert(Item item){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setProductIdentifier(item.getProductIdentifier());
        itemDTO.setPrice(item.getPrice());
        return itemDTO;
    }
    public static ShopDTO convert(Shop shop){
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setUserIdentifier(shop.getUserIdentifier());
        shopDTO.setTotal(shop.getTotal());
        shopDTO.setDate(shop.getDate());
        return shopDTO;
    }
}
