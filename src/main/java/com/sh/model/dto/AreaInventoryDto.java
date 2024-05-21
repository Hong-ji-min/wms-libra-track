package com.sh.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AreaInventoryDto {
    private String areaName;
    private String Location;
    private int capacity;
    private int quantity;
    private int reserved;


}
