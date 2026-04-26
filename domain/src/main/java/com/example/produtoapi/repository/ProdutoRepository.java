package com.example.produtoapi.repository;

import com.example.produtoapi.model.Produto;
import java.util.List;
import java.util.Optional;

public interface ProdutoRepository {
    List<Produto> findAll();
    List<Produto> findAllById(Iterable<String> ids);
    Optional<Produto> findById(String id);
    Produto save(Produto produto);
    void deleteById(String id);
}
