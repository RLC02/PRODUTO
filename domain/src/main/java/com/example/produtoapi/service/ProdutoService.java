package com.example.produtoapi.service;

import com.example.produtoapi.model.Produto;
import com.example.produtoapi.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository repository;

    public List<Produto> findAll() {
        return repository.findAll();
    }

    public Optional<Produto> findById(String id) {
        return repository.findById(id);
    }

    public Produto save(Produto produto) {
        return repository.save(produto);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public Optional<Produto> update(String id, Produto data) {
        return repository.findById(id).map(existing -> {
            existing.setNome(data.getNome());
            existing.setPreco(data.getPreco());
            return repository.save(existing);
        });
    }
}
