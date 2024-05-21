package com.sh.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderAreaDetailDto {
    private String title;
    private String ISBN;
    private int quantity;
    private String location;
    private String areaName;
}
