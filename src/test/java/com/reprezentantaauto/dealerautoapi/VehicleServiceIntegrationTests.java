package com.reprezentantaauto.dealerautoapi;

import com.reprezentantaauto.dealerautoapi.model.Vehicle;
import com.reprezentantaauto.dealerautoapi.service.VehicleService;
import com.reprezentantaauto.dealerautoapi.dto.VehicleDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehicleServiceIntegrationTests {

	@Autowired
	private VehicleService vehicleService;

	@Test
	public void testCreateVehicle_whenValidRequest_thenReturnCreatedVehicle() {

		VehicleDto vehicleRequest = new VehicleDto();
		vehicleRequest.setBrand("Audi");
		vehicleRequest.setModel("A4");
		vehicleRequest.setYear(2018);
		vehicleRequest.setFuel("Diesel");
		vehicleRequest.setEngine_size(2.000);
		vehicleRequest.setTransmission("2x4");
		vehicleRequest.setPrice(30.000);
		vehicleRequest.setQuantity(5);

		Vehicle createdVehicle = vehicleService.create(vehicleRequest);

		assertThat(createdVehicle,notNullValue());
		assertThat(createdVehicle.getId(),greaterThan(0L));
		assertThat(createdVehicle.getBrand(),is(vehicleRequest.getBrand()));
		assertThat(createdVehicle.getModel(),is(vehicleRequest.getModel()));
		assertThat(createdVehicle.getYear(),is(vehicleRequest.getYear()));
		assertThat(createdVehicle.getEngine_size(),is(vehicleRequest.getEngine_size()));
		assertThat(createdVehicle.getTransmission(),is(vehicleRequest.getTransmission()));
		assertThat(createdVehicle.getPrice(),is(vehicleRequest.getPrice()));
		assertThat(createdVehicle.getQuantity(),is(vehicleRequest.getQuantity()));


	}

	@Test(expected = ConstraintViolationException.class)

	public void testCreateVehicle_whenMissingMandatoryProperties_thenThrowException(){
		VehicleDto request = new VehicleDto();

		vehicleService.create(request);

	}
}
