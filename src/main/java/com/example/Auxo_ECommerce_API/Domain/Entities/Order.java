package com.example.Auxo_ECommerce_API.Domain.Entities;

import com.example.Auxo_ECommerce_API.Domain.Entities.Users.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Orders")
@Getter
@Setter
@Builder

public class Order {
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
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;
}
