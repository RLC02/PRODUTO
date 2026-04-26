package com.example.produtoapi.controller;

import com.example.produtoapi.dto.CompraRequest;
import com.example.produtoapi.model.Compra;
import com.example.produtoapi.service.CompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.produtoapi.dto.CompraResponse;

@RestController
@RequestMapping("/compras")
@RequiredArgsConstructor
public class CompraController {

    private final CompraService service;

    @GetMapping("/{id}")
    public ResponseEntity<CompraResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getCompraById(id));
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<CompraResponse>> getByCliente(@PathVariable String clienteId) {
        return ResponseEntity.ok(service.listarPorCliente(clienteId));
    }

    @PostMapping
    public ResponseEntity<Compra> comprar(@RequestBody CompraRequest request) {
        try {
            Compra compra = service.realizarCompra(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(compra);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
