package com.reprezentantaauto.dealerautoapi.controller;

import com.reprezentantaauto.dealerautoapi.domain.Vehicle;
import com.reprezentantaauto.dealerautoapi.request.CreateVehicleRequest;
import com.reprezentantaauto.dealerautoapi.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Vehicle getById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @DeleteMapping("{id}")
    public List<Vehicle> deleteById(@PathVariable("id") Long id){
        service.deleteById(id);
        return service.findAll();
    }

    @PostMapping("create")
    public Vehicle create(@RequestBody CreateVehicleRequest vehicleRequest) {
        return service.create(vehicleRequest);
    }

    @DeleteMapping("delete")
    public void deleteByBrand(@RequestParam(value = "brand") String brand){

        service.deleteByBrand(brand);
    }

    @DeleteMapping("deleteBy")
    public void deleteByIdAndBrand(@RequestParam(value = "id") long id , @RequestParam(value = "brand") String brand){

        service.deleteByIdAndBrand(id, brand);
    }
}
