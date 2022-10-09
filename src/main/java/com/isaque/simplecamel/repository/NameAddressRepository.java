package com.isaque.simplecamel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isaque.simplecamel.model.NameAddressDTO;

@Repository
public interface NameAddressRepository extends JpaRepository<NameAddressDTO, String> {


	
}
