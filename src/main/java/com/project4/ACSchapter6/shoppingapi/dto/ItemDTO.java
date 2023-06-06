package com.project4.ACSchapter6.shoppingapi.dto;

import	java.util.Date;
import	java.util.List;
import	jakarta.validation.constraints.NotBlank;
import	jakarta.validation.constraints.NotNull;

import	com.project4.ACSchapter6.shoppingapi.model.Item;

import	lombok.AllArgsConstructor;
import	lombok.Getter;
import	lombok.NoArgsConstructor;
import	lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    @NotBlank
    private String productIdentifier;
    @NotNull
    private Float price;
}
