package com.home.it.homeIt.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private boolean isAdmin; // On vas garder ca simple

    @ManyToOne
    @JoinColumn(name = "current_plan_id")
    private Plan currentPlan;

    @OneToMany(mappedBy = "owner")
    List<SmartDevice> userDevices ;

}