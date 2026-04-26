package com.example.produtoapi.repository;

import com.example.produtoapi.entity.CompraEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraMongoRepository extends MongoRepository<CompraEntity, String> {
    List<CompraEntity> findByClienteId(String clienteId);
}
