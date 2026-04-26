package com.example.produtoapi.adapter;

import com.example.produtoapi.entity.CompraEntity;
import com.example.produtoapi.model.Compra;
import com.example.produtoapi.repository.CompraMongoRepository;
import com.example.produtoapi.repository.CompraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompraRepositoryAdapter implements CompraRepository {

    private final CompraMongoRepository mongoRepository;

    @Override
    public List<Compra> findByClienteId(String clienteId) {
        return mongoRepository.findByClienteId(clienteId).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Compra> findById(String id) {
        return mongoRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Compra save(Compra compra) {
        CompraEntity entity = toEntity(compra);
        return toDomain(mongoRepository.save(entity));
    }

    @Override
    public void deleteById(String id) {
        mongoRepository.deleteById(id);
    }

    private Compra toDomain(CompraEntity entity) {
        Compra compra = new Compra();
        compra.setId(entity.getId());
        compra.setClienteId(entity.getClienteId());
        compra.setItens(entity.getItens());
        compra.setTotal(entity.getTotal());
        return compra;
    }

    private CompraEntity toEntity(Compra compra) {
        CompraEntity entity = new CompraEntity();
        entity.setId(compra.getId());
        entity.setClienteId(compra.getClienteId());
        entity.setItens(compra.getItens());
        entity.setTotal(compra.getTotal());
        return entity;
    }
}
