package com.example.produtoapi.service;

import com.example.produtoapi.client.ClienteClient;
import com.example.produtoapi.dto.ClienteDTO;
import com.example.produtoapi.dto.CompraRequest;
import com.example.produtoapi.model.Compra;
import com.example.produtoapi.model.Produto;
import com.example.produtoapi.repository.CompraRepository;
import com.example.produtoapi.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import com.example.produtoapi.model.ItemCompra;
import com.example.produtoapi.dto.ItemCompraRequest;
import com.example.produtoapi.dto.CompraResponse;
import com.example.produtoapi.dto.ItemCompraResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CompraService {
    private final CompraRepository compraRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteClient clienteClient;

    public CompraResponse getCompraById(String id) {
        return compraRepository.findById(id).map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Compra não encontrada"));
    }

    public List<CompraResponse> listarPorCliente(String clienteId) {
        return compraRepository.findByClienteId(clienteId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public Compra realizarCompra(CompraRequest request) {
        ClienteDTO cliente = clienteClient.getClienteById(request.getClienteId());
        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado.");
        }

        List<String> produtoIds = request.getItens().stream()
                .map(ItemCompraRequest::getProdutoId)
                .collect(Collectors.toList());

        List<Produto> produtos = produtoRepository.findAllById(produtoIds);
        
        Double total = request.getItens().stream().mapToDouble(item -> {
            Produto p = produtos.stream()
                    .filter(prod -> prod.getId().equals(item.getProdutoId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + item.getProdutoId()));
            return p.getPreco().doubleValue() * item.getQuantidade();
        }).sum();

        List<ItemCompra> itens = request.getItens().stream()
                .map(req -> new ItemCompra(req.getProdutoId(), req.getQuantidade()))
                .collect(Collectors.toList());

        Compra compra = new Compra();
        compra.setClienteId(request.getClienteId());
        compra.setItens(itens);
        compra.setTotal(total);

        return compraRepository.save(compra);
    }

    private CompraResponse mapToResponse(Compra compra) {
        CompraResponse response = new CompraResponse();
        response.setId(compra.getId());
        response.setClienteId(compra.getClienteId());
        response.setTotal(compra.getTotal());

        List<ItemCompraResponse> itensResponse = compra.getItens().stream().map(item -> {
            Produto p = produtoRepository.findById(item.getProdutoId()).orElse(null);
            String nome = (p != null) ? p.getNome() : "Produto Desconhecido";
            return new ItemCompraResponse(nome, item.getQuantidade());
        }).collect(Collectors.toList());

        response.setItens(itensResponse);
        return response;
    }
}
