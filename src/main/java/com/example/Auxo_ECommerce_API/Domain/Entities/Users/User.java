package com.example.Auxo_ECommerce_API.Domain.Entities.Users;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")

public class User {
//    @Id
//    @UuidGenerator
//    @Column(columnDefinition = "uniqueidentifier")
//    private UUID id;
//@Id
////@GeneratedValue(strategy = GenerationType.UUID)
////@Column(name = "id", columnDefinition = "VARCHAR(36)", updatable = false, nullable = false)
//@GeneratedValue(generator = "UUID")
//@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
//@Column(updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
//private UUID id;

    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private String id;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
    @ManyToMany(mappedBy = "Users")
    private List<Role> Roles;
}
