package com.example.produtoapi.adapter;

import com.example.produtoapi.client.ClienteClient;
import com.example.produtoapi.dto.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cliente-api", url = "${cliente-api.url:http://localhost:8082}")
public interface FeignClienteClientAdapter extends ClienteClient {

    @GetMapping("/clientes/{id}")
    @Override
    ClienteDTO getClienteById(@PathVariable("id") String id);
}
