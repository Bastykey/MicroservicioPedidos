package cl.duoc.MicroservicioPedidos.Repository;

import cl.duoc.MicroservicioPedidos.Model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByFechaPedido(LocalDate fecha);

    List<Pedido> findByIdUsuario(String idUsuario);
    }