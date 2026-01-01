package com.example.Auxo_ECommerce_API.Domain.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")

public class User {
    @Id
    @UuidGenerator
    @Column(columnDefinition = "uniqueidentifier")
    private UUID id;
    private String username;
    private String password;
    private String role;
}
