package com.home.it.homeIt.entity;

import com.home.it.homeIt.dto.SmartDeviceDTO;
import com.home.it.homeIt.enums.DeviceStatusEnum;
import com.home.it.homeIt.enums.DeviceTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SmartDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;

    @Enumerated(EnumType.STRING)
    private DeviceTypeEnum deviceTypeEnum;

    @Enumerated(EnumType.STRING)
    private DeviceStatusEnum status;

    @ManyToOne
    AppUser owner ;

    public SmartDevice(SmartDeviceDTO dto) {
        this.name = dto.name() ;
        this.location = dto.location() ;

        this.deviceTypeEnum = DeviceTypeEnum.valueOf(dto.deviceTypeEnum()) ;
        this.status = DeviceStatusEnum.valueOf(dto.status()) ;
        this.owner = AppUser.builder().id(dto.ownerID()).build() ;
    }
}
