package com.example.produtoapi.repository;

import com.example.produtoapi.entity.ProdutoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoMongoRepository extends MongoRepository<ProdutoEntity, String> {
}
