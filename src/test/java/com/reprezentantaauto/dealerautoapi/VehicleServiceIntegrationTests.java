package com.reprezentantaauto.dealerautoapi;

import com.reprezentantaauto.dealerautoapi.domain.Vehicle;
import com.reprezentantaauto.dealerautoapi.request.CreateVehicleRequest;
import com.reprezentantaauto.dealerautoapi.service.VehicleService;
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

		CreateVehicleRequest vehicleRequest = new CreateVehicleRequest();
		vehicleRequest.setBrand("Audi");
		vehicleRequest.setModel("A7");
		vehicleRequest.setYear(2018);
		vehicleRequest.setFuel("Diesel");
		vehicleRequest.setEngine_size(3.000);
		vehicleRequest.setTransmission("4x4");
		vehicleRequest.setPrice(50.000);
		vehicleRequest.setQuantity(10);

		Vehicle createdVehicle = vehicleService.createVehicle(vehicleRequest);

		assertThat(createdVehicle,notNullValue());
		assertThat(createdVehicle.getId(),greaterThan(new Long(0)));
		assertThat(createdVehicle.getBrand(),is(vehicleRequest.getBrand()));
		assertThat(createdVehicle.getModel(),is(vehicleRequest.getModel()));
		assertThat(createdVehicle.getYear(),is(vehicleRequest.getYear()));
		assertThat(createdVehicle.getEngine_size(),is(vehicleRequest.getEngine_size()));
		assertThat(createdVehicle.getTransmission(),is(vehicleRequest.getTransmission()));


	}

	@Test(expected = ConstraintViolationException.class)

	public void testCreateVehicle_whenMissingMandatoryProperties_thenThrowException(){
		CreateVehicleRequest request = new CreateVehicleRequest();

		vehicleService.createVehicle(request);

	}
}
