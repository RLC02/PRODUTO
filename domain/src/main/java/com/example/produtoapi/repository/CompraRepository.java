package com.example.produtoapi.repository;

import com.example.produtoapi.model.Compra;
import java.util.List;
import java.util.Optional;

public interface CompraRepository {
    List<Compra> findByClienteId(String clienteId);
    Optional<Compra> findById(String id);
    Compra save(Compra compra);
    void deleteById(String id);
}
