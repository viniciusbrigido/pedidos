package com.brigido.pedidos.dto;

import com.brigido.pedidos.enumeration.StatusProdutoEnum;
import com.brigido.pedidos.enumeration.TipoProdutoEnum;
import lombok.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoDto {

    private UUID id;
    private String nome;
    private Double valor;
    private TipoProdutoEnum tipo;
    private StatusProdutoEnum status;
}
