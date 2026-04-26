package com.example.produtoapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCompraResponse {
    private String produtoNome;
    private Integer quantidade;
}
