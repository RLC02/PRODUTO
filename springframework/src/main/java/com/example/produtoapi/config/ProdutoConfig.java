package com.example.produtoapi.config;

import com.example.produtoapi.client.ClienteClient;
import com.example.produtoapi.repository.CompraRepository;
import com.example.produtoapi.repository.ProdutoRepository;
import com.example.produtoapi.service.CompraService;
import com.example.produtoapi.service.ProdutoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdutoConfig {

    @Bean
    public ProdutoService produtoService(ProdutoRepository produtoRepository) {
        return new ProdutoService(produtoRepository);
    }

    @Bean
    public CompraService compraService(CompraRepository compraRepository, ProdutoRepository produtoRepository, ClienteClient clienteClient) {
        return new CompraService(compraRepository, produtoRepository, clienteClient);
    }
}
