package com.reprezentantaauto.dealerautoapi.service;

import com.reprezentantaauto.dealerautoapi.domain.Vehicle;
import com.reprezentantaauto.dealerautoapi.repository.VehicleRepository;
import com.reprezentantaauto.dealerautoapi.request.CreateVehicleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    //inversion of control(vehicleRepository obj gives control to VehicleService class) & dependency injection

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle createVehicle(CreateVehicleRequest vehicleRequest) {

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
