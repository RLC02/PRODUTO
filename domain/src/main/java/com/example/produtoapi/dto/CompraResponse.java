package com.example.produtoapi.dto;

import lombok.Data;
import java.util.List;

@Data
public class CompraResponse {
    private String id;
    private String clienteId;
    private List<ItemCompraResponse> itens;
    private Double total;
}
