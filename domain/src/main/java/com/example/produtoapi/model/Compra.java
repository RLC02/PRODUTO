package com.example.produtoapi.model;

import lombok.Data;
import java.util.List;

@Data
public class Compra {
    private String id;
    private String clienteId;
    private List<ItemCompra> itens;
    private Double total;
}
