package com.reprezentantaauto.dealerautoapi.controller;

import com.reprezentantaauto.dealerautoapi.exception.ResourceNotFoundException;
import com.reprezentantaauto.dealerautoapi.model.Vehicle;
import com.reprezentantaauto.dealerautoapi.service.VehicleService;
import com.reprezentantaauto.dealerautoapi.dto.VehicleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/vehicles/")
public class VehicleController {

    private final VehicleService service;

    @Autowired
    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @GetMapping("getAll")
    public List<Vehicle> getAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Vehicle> getById(@PathVariable("id") Long id) throws ResourceNotFoundException {
         Vehicle response = service.findById(id);

        return new ResponseEntity<>(response,HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public List<Vehicle> deleteById(@PathVariable("id") Long id){
        service.deleteById(id);
        return service.findAll();
    }

    @PostMapping("create")
    public Vehicle create(@RequestBody VehicleDto vehicleRequest) {
        return service.create(vehicleRequest);
    }

    @DeleteMapping("deleteByBrand")
    public void deleteByBrand(@RequestParam(value = "brand") String brand){

        service.deleteByBrand(brand);
    }

    @DeleteMapping("deleteByIdAndBrand")
    public void deleteByIdAndBrand(@RequestParam(value = "id") long id , @RequestParam(value = "brand") String brand){

        service.deleteByIdAndBrand(id, brand);
    }

    @PutMapping("{id}")
   public ResponseEntity updateVehicle(@PathVariable("id") Long id,@RequestBody @Valid VehicleDto updateVehicleRequest) throws ResourceNotFoundException {

        service.updateVehicle(id,updateVehicleRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("findByBrandAndModel/{Brand}/{Model}")
    public ResponseEntity<Vehicle> findByBrandAndModel(@PathVariable("Brand") String brand,@PathVariable("Model") String model){

        Vehicle response = service.findByBrandAndModel(brand,model);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
