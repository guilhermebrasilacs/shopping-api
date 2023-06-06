package com.project4.ACSchapter6.shoppingapi.dto;

import java.time.LocalDateTime;
import	java.util.Date;
import	java.util.List;
import	jakarta.validation.constraints.NotBlank;
import	jakarta.validation.constraints.NotNull;

import	com.project4.ACSchapter6.shoppingapi.model.Shop;
import	lombok.AllArgsConstructor;
import	lombok.Getter;
import	lombok.NoArgsConstructor;
import	lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShopDTO {
    @NotBlank
    private String userIdentifier;
    @NotNull
    private Float total;
    @NotNull
    private LocalDateTime date;
    @NotNull
    private List<ItemDTO> items;
}
