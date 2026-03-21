package com.example.Auxo_ECommerce_API.Application.Features.Categories.Commands;

import com.example.Auxo_ECommerce_API.Application.Common.ICommand;
import com.example.Auxo_ECommerce_API.Domain.Enums.CategoryType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateCategoryCommand implements ICommand<Long> {
    private Long Id;
    private String NameAr;
    private String NameEn;
    // 🔹 Nullable
    private Long parentId;
    private CategoryType type = CategoryType.MAIN; // default
    @JsonIgnore
    private MultipartFile imageFile; // ← add this (org.springframework.web.multipart)
    private String existingImageUrl;     // ← current saved image url (keep if no new upload)

}
