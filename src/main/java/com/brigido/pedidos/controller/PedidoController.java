package com.brigido.pedidos.controller;

import com.brigido.pedidos.dto.*;
import com.brigido.pedidos.entity.PedidoEntity;
import com.brigido.pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoEntity> create(@RequestBody PedidoDto dto) {
        return ResponseEntity.ok(pedidoService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoEntity> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(pedidoService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoEntity> aplicaDesconto(@PathVariable UUID id, @RequestBody AplicaDescontoPedidoDto dto) {
        return ResponseEntity.ok(pedidoService.aplicaDesconto(id, dto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        pedidoService.delete(id);
    }

    @GetMapping
    public ResponseEntity<List<PedidoEntity>> list() {
        return ResponseEntity.ok(pedidoService.list());
    }

    @PutMapping("/fechar/{id}")
    public ResponseEntity<PedidoEntity> fechar(@PathVariable UUID id) {
        return ResponseEntity.ok(pedidoService.fechar(id));
    }
}
