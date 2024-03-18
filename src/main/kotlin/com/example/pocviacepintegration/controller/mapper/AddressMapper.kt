package com.example.pocviacepintegration.controller.mapper

import com.example.pocviacepintegration.controller.request.AddressRequest
import com.example.pocviacepintegration.controller.response.AddressResponse
import com.example.pocviacepintegration.model.entities.AddressEntity


object AddressMapper {

    fun toResponse(entity: AddressEntity): AddressResponse {
        return AddressResponse(
            cep =entity.cep,
            logradouro = entity.logradouro,
            complemento = entity.complemento,
            bairro = entity.bairro,
            localidade = entity.localidade,
            uf = entity.uf
        )
    }

    fun requestToEntity(request: AddressRequest): AddressEntity {
        return AddressEntity(
            cep = request.cep,
            logradouro = request.logradouro,
            complemento = request.complemento,
            bairro = request.bairro,
            localidade = request.localidade,
            uf = request.uf,
            ibge = request.ibge,
            gia = request.gia,
            ddd = request.ddd,
            siafi = request.siafi,
        )
    }

    fun entityToRequest(entity: AddressEntity): AddressRequest {
        return AddressRequest(
            cep = entity.cep,
            logradouro = entity.logradouro,
            complemento = entity.complemento,
            bairro = entity.bairro,
            localidade = entity.localidade,
            uf = entity.uf,
            ibge = entity.ibge,
            gia = entity.gia,
            ddd = entity.ddd,
            siafi = entity.siafi,
        )
    }
}
