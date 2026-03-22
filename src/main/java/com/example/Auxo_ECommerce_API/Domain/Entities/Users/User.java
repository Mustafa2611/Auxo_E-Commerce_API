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
//    @Column(name = "id", updatable = false, nullable = false, length = 36)
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private String id;
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    private String phoneNumber;
    private String password;
    // User OWNS the join table — JPA writes to User_Roles from this side
    @ManyToMany(fetch = FetchType.LAZY)   // NO cascade — roles already exist, just link them
    @JoinTable(
            name = "user_roles",                                      // lowercase — matches DB
            joinColumns        = @JoinColumn(name = "user_id"),       // lowercase
            inverseJoinColumns = @JoinColumn(name = "role_id")        // lowercase
    )
    private List<Role> Roles;

//    @ManyToMany(mappedBy = "Users")
//    private List<Role> Roles;
}
