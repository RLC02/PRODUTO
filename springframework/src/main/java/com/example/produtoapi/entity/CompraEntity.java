package com.example.produtoapi.entity;

import com.example.produtoapi.model.ItemCompra;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "compras")
public class CompraEntity {
    @Id
    private String id;
    private String clienteId;
    private List<ItemCompra> itens;
    private Double total;
}
