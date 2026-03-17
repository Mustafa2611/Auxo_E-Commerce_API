package com.example.Auxo_ECommerce_API.Application.Features.Categories.Queries;

import com.example.Auxo_ECommerce_API.Application.Common.IQuery;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Dtos.GetCategoryDetailsDto;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Dtos.GetCategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCategoryByIdQuery implements IQuery<GetCategoryDetailsDto> {
    private Long Id;
}
