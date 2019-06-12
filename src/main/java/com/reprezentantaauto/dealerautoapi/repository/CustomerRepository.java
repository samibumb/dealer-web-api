package com.reprezentantaauto.dealerautoapi.repository;

import com.reprezentantaauto.dealerautoapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {


}
