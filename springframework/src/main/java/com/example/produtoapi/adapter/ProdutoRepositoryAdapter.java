package com.example.produtoapi.adapter;

import com.example.produtoapi.entity.ProdutoEntity;
import com.example.produtoapi.model.Produto;
import com.example.produtoapi.repository.ProdutoMongoRepository;
import com.example.produtoapi.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
public class ProdutoRepositoryAdapter implements ProdutoRepository {

    private final ProdutoMongoRepository mongoRepository;

    @Override
    public List<Produto> findAll() {
        return mongoRepository.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Produto> findAllById(Iterable<String> ids) {
        return StreamSupport.stream(mongoRepository.findAllById(ids).spliterator(), false)
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Produto> findById(String id) {
        return mongoRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Produto save(Produto produto) {
        ProdutoEntity entity = toEntity(produto);
        return toDomain(mongoRepository.save(entity));
    }

    @Override
    public void deleteById(String id) {
        mongoRepository.deleteById(id);
    }

    private Produto toDomain(ProdutoEntity entity) {
        Produto produto = new Produto();
        produto.setId(entity.getId());
        produto.setNome(entity.getNome());
        produto.setPreco(entity.getPreco());
        return produto;
    }

    private ProdutoEntity toEntity(Produto produto) {
        ProdutoEntity entity = new ProdutoEntity();
        entity.setId(produto.getId());
        entity.setNome(produto.getNome());
        entity.setPreco(produto.getPreco());
        return entity;
    }
}
