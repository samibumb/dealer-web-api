package com.reprezentantaauto.dealerautoapi.service;

import com.reprezentantaauto.dealerautoapi.domain.Vehicle;
import com.reprezentantaauto.dealerautoapi.repository.VehicleRepository;
import com.reprezentantaauto.dealerautoapi.request.CreateVehicleRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleService.class);

    //inversion of control(vehicleRepository obj gives control to VehicleService class) & dependency injection

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle createVehicle(CreateVehicleRequest vehicleRequest) {

        LOGGER.info("Creating object {}",vehicleRequest);

        Vehicle vehicle = new Vehicle();
        vehicle.setBrand(vehicleRequest.getBrand());
        vehicle.setModel(vehicleRequest.getModel());
        vehicle.setYear(vehicleRequest.getYear());
        vehicle.setFuel(vehicleRequest.getFuel());
        vehicle.setEngine_size(vehicleRequest.getEngine_size());
        vehicle.setTransmission(vehicleRequest.getTransmission());
        vehicle.setPrice(vehicleRequest.getPrice());
        vehicle.setImagePath(vehicleRequest.getImagePath());

        return vehicleRepository.save(vehicle);
    }
}
