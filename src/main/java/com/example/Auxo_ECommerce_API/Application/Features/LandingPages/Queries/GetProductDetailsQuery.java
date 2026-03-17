package com.example.Auxo_ECommerce_API.Application.Features.LandingPages.Queries;

import com.example.Auxo_ECommerce_API.Application.Common.IQuery;
import com.example.Auxo_ECommerce_API.Application.Features.LandingPages.Dtos.GetProductDetailsDto;
import lombok.Data;

import java.util.UUID;

@Data
public class GetProductDetailsQuery implements IQuery<GetProductDetailsDto> {
    private UUID Id;
}
