package com.example.Auxo_ECommerce_API.Application.Features.Categories.Queries;

import com.example.Auxo_ECommerce_API.Application.Common.IQuery;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Dtos.GetCategoryDto;
import lombok.Data;

import java.util.List;

// Query
@Data
public class GetChildrenByParentIdQuery implements IQuery<List<GetCategoryDto>> {
    private Long parentId;
    public GetChildrenByParentIdQuery(Long parentId) { this.parentId = parentId; }
}
