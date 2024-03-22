package com.home.it.homeIt.controller;

import com.home.it.homeIt.dto.SmartDeviceDTO;
import com.home.it.homeIt.exception.DeviceLimitException;
import com.home.it.homeIt.exception.DeviceNotAllowedException;
import com.home.it.homeIt.exception.NoPlanException;
import com.home.it.homeIt.exception.UserNotFoundException;
import com.home.it.homeIt.service.DevicesServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/device/class")
public class SmartDeviceControllerClassLevel {

    private final DevicesServices devicesServices ;

    public SmartDeviceControllerClassLevel(DevicesServices devicesServices) {
        this.devicesServices = devicesServices;
    }

    @ExceptionHandler({NoPlanException.class})
    public ResponseEntity<String> handleNoPlanException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({DeviceLimitException.class, DeviceNotAllowedException.class})
    public ResponseEntity<String> handlePlanLevelException(Exception ex) {
        if(ex instanceof DeviceLimitException) {
            System.out.println("Is a device limit exception");
        } else if(ex instanceof DeviceNotAllowedException) {
            System.out.println("Is a device not allowed exception");
        }

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping(path = "")
    public ResponseEntity addDeviceProper(@RequestBody SmartDeviceDTO smartDeviceDTO) throws Exception {
        devicesServices.addDeviceProper(smartDeviceDTO) ;
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
