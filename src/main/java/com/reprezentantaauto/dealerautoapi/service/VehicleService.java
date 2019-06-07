package com.reprezentantaauto.dealerautoapi.service;

import com.reprezentantaauto.dealerautoapi.dto.UpdateVehicleRequest;
import com.reprezentantaauto.dealerautoapi.exception.ResourceNotFoundException;
import com.reprezentantaauto.dealerautoapi.model.Vehicle;
import com.reprezentantaauto.dealerautoapi.repository.VehicleRepository;
import com.reprezentantaauto.dealerautoapi.dto.CreateVehicleRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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

    public Vehicle findById(Long id) throws ResourceNotFoundException {
        return vehicleRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Vehicle "+id+" does not exist"));

    }

    public Vehicle updateVehicle(long id, UpdateVehicleRequest updateVehicleRequest) throws ResourceNotFoundException{
        Vehicle vehicle = new Vehicle();

        BeanUtils.copyProperties(updateVehicleRequest,vehicle);

        return vehicleRepository.save(vehicle);

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
