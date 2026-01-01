package com.example.Auxo_ECommerce_API.Application.Dtos.Cateories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCategoryDto {
    private Long Id;
    private String NameAr;
    private String NameEn;
}
