package com.brigido.pedidos.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoDto {
    private List<ItemPedidoDto> itens;
    private Double percentualDesconto;
}
