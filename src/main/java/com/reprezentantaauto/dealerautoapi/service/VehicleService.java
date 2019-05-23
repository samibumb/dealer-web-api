package com.reprezentantaauto.dealerautoapi.service;

import com.reprezentantaauto.dealerautoapi.domain.Vehicle;
import com.reprezentantaauto.dealerautoapi.repository.VehicleRepository;
import com.reprezentantaauto.dealerautoapi.request.CreateVehicleRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleService.class);

    //inversion of control(vehicleRepository obj gives control to VehicleService class) & dependency injection

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle create(CreateVehicleRequest vehicleRequest) {

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
        vehicle.setQuantity(vehicleRequest.getQuantity());

        return vehicleRepository.save(vehicle);
    }

    public void deleteById(long id) {
        vehicleRepository.deleteById(id);
    }

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public Vehicle findById(Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if(vehicle.isPresent()) {
            return vehicle.get();
        }

        return null;
    }

    @Transactional
    public void deleteByBrand(String brand){
        vehicleRepository.deleteByBrand(brand);
    }

    @Transactional
    public void deleteByIdAndBrand(long id, String brand){

        vehicleRepository.deleteByIdAndBrand(id,brand);
    }

}
