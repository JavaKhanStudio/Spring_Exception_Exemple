package com.home.it.homeIt.service;

import com.home.it.homeIt.dto.SmartDeviceDTO;
import com.home.it.homeIt.entity.AppUser;
import com.home.it.homeIt.entity.Plan;
import com.home.it.homeIt.entity.SmartDevice;
import com.home.it.homeIt.enums.DeviceTypeEnum;
import com.home.it.homeIt.exception.DeviceLimitException;
import com.home.it.homeIt.exception.DeviceNotAllowedException;
import com.home.it.homeIt.exception.NoPlanException;
import com.home.it.homeIt.exception.UserNotFoundException;
import com.home.it.homeIt.repository.AppUserRepository;
import com.home.it.homeIt.repository.SmartDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DevicesServices {

    private final SmartDeviceRepository smartDeviceRepository ;

    private final AppUserRepository appUserRepository ;

    @Autowired
    public DevicesServices(SmartDeviceRepository smartDeviceRepository, AppUserRepository appUserRepository) {
        this.smartDeviceRepository = smartDeviceRepository;
        this.appUserRepository = appUserRepository;
    }

    public SmartDevice addDeviceProper(SmartDeviceDTO deviceDTO) throws Exception {

        if("TOP_SECRET".equals(deviceDTO.name())) {
            throw new HttpMessageNotReadableException ("This will be caught by controller/GlobalErrorCatch.java");
        }

        AppUser user = appUserRepository.findById(deviceDTO.ownerID()).orElseThrow(() -> new UserNotFoundException()) ;
        Plan plan = user.getCurrentPlan() ;
        if(plan == null) {
            throw new NoPlanException() ;
        }

        Integer maxDevices = plan.getMaxDevices() ;

        if(!"Premium".equals(plan.getName()) && maxDevices <= user.getUserDevices().size() ) {
            System.out.println(maxDevices + " / " + user.getUserDevices().size() );
            throw new DeviceLimitException() ;
        }

        if(!"Premium".equals(plan.getName()) && !plan.getAllowedDevices().contains(DeviceTypeEnum.valueOf(deviceDTO.deviceTypeEnum()))) {
            System.out.println("Element should be " + plan.getAllowedDevices() + " But is " + deviceDTO.deviceTypeEnum());
            throw new DeviceNotAllowedException() ;
        }

        return smartDeviceRepository.save(new SmartDevice(deviceDTO)) ;
    }


}
