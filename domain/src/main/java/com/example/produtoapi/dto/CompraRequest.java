package com.example.produtoapi.dto;

import lombok.Data;
import java.util.List;

@Data
public class CompraRequest {
    private String clienteId;
    private List<ItemCompraRequest> itens;
}
