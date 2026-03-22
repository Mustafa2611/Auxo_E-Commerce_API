package com.example.Auxo_ECommerce_API.Domain.Entities.Users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name ="Roles")
public class Role {
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private UUID id ;
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
//    @UuidGenerator(style = UuidGenerator.Style.NAME)
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private String id;
    private String name;

    // Inverse side — mappedBy points to the field name in User that owns the join table
    @ManyToMany(mappedBy = "Roles", fetch = FetchType.LAZY)
    private List<User> Users;
//    @ManyToMany(
//            fetch = FetchType.LAZY,
//            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
//    )
//    @JoinTable(
//            name = "User_Roles",
//            joinColumns = @JoinColumn(name = "Role_Id"),
//            inverseJoinColumns = @JoinColumn(name = "User_Id")
//    )
//    private List<User> Users;
}
