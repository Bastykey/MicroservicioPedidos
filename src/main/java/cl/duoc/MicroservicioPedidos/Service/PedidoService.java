package cl.duoc.MicroservicioPedidos.Service;

import cl.duoc.MicroservicioPedidos.Model.Pedido;
import cl.duoc.MicroservicioPedidos.Repository.PedidoRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> obtenerTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido obtenerPorId(Long id) throws Exception {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.orElseThrow(() -> new Exception("Pedido no encontrado con ID: " + id));
    }

    public Pedido guardar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public void eliminar(Long id) throws Exception {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
        } else {
            throw new Exception("No se puede eliminar un pedido que no existe.");
        }
    }

    public List<Pedido> buscarPorFecha(LocalDate fecha) {
        return pedidoRepository.findByFechaPedido(fecha);
    }
}
