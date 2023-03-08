package com.brigido.pedidos.dto;

import lombok.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdicionaItemPedidoDto {
    private UUID pedidoId;
    private List<ItemPedidoDto> itens;
}
