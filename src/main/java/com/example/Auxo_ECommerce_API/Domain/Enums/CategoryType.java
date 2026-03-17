package com.example.Auxo_ECommerce_API.Domain.Enums;

public enum CategoryType {
    MAIN,// Level 1 — no parent
    SECTION,   // Level 2 — parent must be MAIN
    SUB_SECTION // Level 3 — parent must be SECTION
}
