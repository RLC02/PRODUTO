package com.example.produtoapi.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document(collection = "produtos")
public class ProdutoEntity {
    @Id
    private String id;
    private String nome;
    private BigDecimal preco;
}
