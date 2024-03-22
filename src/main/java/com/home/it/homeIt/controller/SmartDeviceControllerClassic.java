package com.home.it.homeIt.controller;

import com.home.it.homeIt.dto.SmartDeviceDTO;
import com.home.it.homeIt.exception.DeviceLimitException;
import com.home.it.homeIt.exception.DeviceNotAllowedException;
import com.home.it.homeIt.exception.NoPlanException;
import com.home.it.homeIt.exception.UserNotFoundException;
import com.home.it.homeIt.service.DevicesServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/device/classic")
public class SmartDeviceControllerClassic {

    private final DevicesServices devicesServices ;

    public SmartDeviceControllerClassic(DevicesServices devicesServices) {
        this.devicesServices = devicesServices;
    }

    @PostMapping(path = "")
    public ResponseEntity addDeviceProper(@RequestBody SmartDeviceDTO smartDeviceDTO) throws Exception {

        try {
            devicesServices.addDeviceProper(smartDeviceDTO) ;
        } catch(NoPlanException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch(UserNotFoundException userNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not found that guy");
        } catch(DeviceLimitException | DeviceNotAllowedException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage() + " and give Me Money");
        } catch(Exception e) {

            if (e instanceof HttpMessageNotReadableException) {
                System.out.println("You Are cleared");
                throw e ;
            }

            // Non managed error, we should log it
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("I don't know :(");
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
}
