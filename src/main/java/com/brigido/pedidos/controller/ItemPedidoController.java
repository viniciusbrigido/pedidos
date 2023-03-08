package com.brigido.pedidos.controller;

import com.brigido.pedidos.dto.AdicionaItemPedidoDto;
import com.brigido.pedidos.entity.ItemPedidoEntity;
import com.brigido.pedidos.entity.PedidoEntity;
import com.brigido.pedidos.service.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/item-pedido")
public class ItemPedidoController {

    @Autowired
    private ItemPedidoService itemPedidoService;

    @PostMapping
    public ResponseEntity<PedidoEntity> save(@RequestBody AdicionaItemPedidoDto dto) {
        return ResponseEntity.ok(itemPedidoService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedidoEntity> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(itemPedidoService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        itemPedidoService.delete(id);
    }

    @GetMapping
    public ResponseEntity<List<ItemPedidoEntity>> list() {
        return ResponseEntity.ok(itemPedidoService.list());
    }
}
