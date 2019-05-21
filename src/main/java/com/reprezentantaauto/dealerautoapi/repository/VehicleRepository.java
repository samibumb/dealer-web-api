package com.reprezentantaauto.dealerautoapi.repository;

import com.reprezentantaauto.dealerautoapi.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle , Long> {


}
