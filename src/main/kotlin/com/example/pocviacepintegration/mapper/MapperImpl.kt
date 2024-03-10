package com.example.pocviacepintegration.mapper

import com.example.pocviacepintegration.dto.AddressDTO
import com.example.pocviacepintegration.model.entities.AddressEntity
import org.springframework.stereotype.Component

@Component
class MapperImpl: Mapper<AddressDTO, AddressEntity> {
    override fun fromEntity(entity: AddressEntity): AddressDTO {
        return AddressDTO(
            cep = entity.cep,
            logradouro = entity.logradouro,
            complemento = entity.complemento,
            bairro = entity.bairro,
            localidade = entity.localidade,
            uf = entity.uf
        )
    }

    override fun toEntity(domain: AddressDTO): AddressEntity {
        return AddressEntity(
            cep = domain.cep,
            logradouro = domain.logradouro,
            complemento = domain.complemento,
            bairro = domain.bairro,
            localidade = domain.localidade,
            uf = domain.uf,
            ibge = null,
            gia = null,
            ddd = null,
            siafi = null

        )
    }
}