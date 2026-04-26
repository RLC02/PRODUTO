package com.example.produtoapi.model;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class Produto {
    private String id;
    private String nome;
    private BigDecimal preco;
}
