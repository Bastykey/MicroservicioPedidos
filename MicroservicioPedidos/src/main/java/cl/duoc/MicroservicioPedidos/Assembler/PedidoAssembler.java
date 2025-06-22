package cl.duoc.MicroservicioPedidos.Assembler;

import cl.duoc.MicroservicioPedidos.DTO.PedidoDTO;
import cl.duoc.MicroservicioPedidos.Model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class PedidoAssembler {

    public PedidoDTO toDTO(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setId(pedido.getIdPedido());
        dto.setFecha(pedido.getFechaPedido());
        dto.setIdUsuario(pedido.getIdUsuario());
        dto.setDetalle(pedido.getDetalle());
        return dto;
    }

    public Pedido toEntity(PedidoDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setIdPedido(dto.getId());
        pedido.setFechaPedido(dto.getFecha());
        pedido.setIdUsuario(dto.getIdUsuario());
        pedido.setDetalle(dto.getDetalle());
        return pedido;
    }
}