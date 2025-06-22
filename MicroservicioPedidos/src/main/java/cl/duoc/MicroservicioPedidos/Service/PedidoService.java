package cl.duoc.MicroservicioPedidos.Service;

import cl.duoc.MicroservicioPedidos.Model.Pedido;
import cl.duoc.MicroservicioPedidos.Repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Transactional
@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> obtenerTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido obtenerPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + id));
    }

    public Pedido guardar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public void eliminar(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Pedido no encontrado con ID: " + id);
        }
        pedidoRepository.deleteById(id);
    }

    public List<Pedido> buscarPorFecha(LocalDate fecha) {
        return pedidoRepository.findByFechaPedido(fecha);
    }

    public List<Pedido> buscarPorUsuario(String idUsuario) {
        return pedidoRepository.findByIdUsuario(idUsuario);
    }
}