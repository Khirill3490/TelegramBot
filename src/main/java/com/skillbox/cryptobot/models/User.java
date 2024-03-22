package com.skillbox.cryptobot.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "uuid", nullable = false)
    private Long userUUID;


    private String name;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "subscription_price")
    private Double subscriptionPrice;
}
