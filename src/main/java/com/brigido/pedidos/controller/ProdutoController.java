package com.brigido.pedidos.controller;

import com.brigido.pedidos.dto.ProdutoDto;
import com.brigido.pedidos.entity.ProdutoEntity;
import com.brigido.pedidos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;

//@Tag(name = "ProdutoController")
@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

//    @Operation(summary = "Salva o produto")
    @PostMapping
    public ResponseEntity<ProdutoEntity> save(@RequestBody ProdutoDto dto) {
        return ResponseEntity.ok(produtoService.save(dto));
    }

//    @Operation(summary = "Busca o produto por Id")
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoEntity> buscaPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(produtoService.findById(id));
    }

//    @Operation(summary = "Atualiza o produto")
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoEntity> atualizarProduto(@PathVariable UUID id, @RequestBody ProdutoDto dto) {
        return ResponseEntity.ok(produtoService.update(id, dto));
    }

//    @Operation(summary = "Deleta o produto")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        produtoService.delete(id);
    }

//    @Operation(summary = "Lista todos os Produtos")
    @GetMapping
    public ResponseEntity<List<ProdutoEntity>> list() {
        return ResponseEntity.ok(produtoService.list());
    }
}
