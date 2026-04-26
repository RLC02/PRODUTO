package com.example.produtoapi.dto;

import lombok.Data;

@Data
public class ItemCompraRequest {
    private String produtoId;
    private Integer quantidade;
}
