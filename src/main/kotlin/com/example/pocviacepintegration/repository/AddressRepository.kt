package com.example.pocviacepintegration.repository

import com.example.pocviacepintegration.model.entities.AddressEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AddressRepository : JpaRepository<AddressEntity, String> {
}