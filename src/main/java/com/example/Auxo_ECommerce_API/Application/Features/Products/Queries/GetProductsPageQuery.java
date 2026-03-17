package com.example.Auxo_ECommerce_API.Application.Features.Products.Queries;

import com.example.Auxo_ECommerce_API.Application.Common.IQuery;
import com.example.Auxo_ECommerce_API.Application.Features.Products.Dtos.GetProductsPageDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class GetProductsPageQuery implements IQuery<List<GetProductsPageDto>> {
    private Long CategoryId;
}
