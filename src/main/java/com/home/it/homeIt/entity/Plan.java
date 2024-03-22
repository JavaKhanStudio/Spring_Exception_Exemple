package com.home.it.homeIt.entity;

import com.home.it.homeIt.enums.DeviceTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Entity
@Getter
@Setter
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private Integer maxDevices;

    @ElementCollection(targetClass = DeviceTypeEnum.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "plan_allowed_devices", joinColumns = @JoinColumn(name = "plan_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "device_type")
    private Set<DeviceTypeEnum> allowedDevices;
}