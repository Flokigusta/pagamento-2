package com.trabalhoArquitetura.trabalho.Controller;

import com.trabalhoArquitetura.trabalho.Model.Pagamento;
import com.trabalhoArquitetura.trabalho.Service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService service;

    /**
     * Cria um novo pagamento.
     */
    @PostMapping
    public ResponseEntity<?> criarPagamento(@RequestBody Pagamento pagamento) {
        if (pagamento.getIdPedido() == null || pagamento.getValor() == null) {
            return ResponseEntity.badRequest().body("Campos obrigatórios: idPedido, valor");
        }

        Pagamento novoPagamento = service.criarPagamento(pagamento);
        return ResponseEntity.ok(novoPagamento);
    }

    /**
     * Consulta o status de um pagamento associado a um pedido.
     */
    @GetMapping("/{idPedido}")
    public ResponseEntity<?> consultarPagamento(@PathVariable String idPedido) {
        Optional<Pagamento> pagamento = service.consultarPagamento(idPedido);

        if (pagamento.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pagamento.get());
    }

    /**
     * Atualiza o status de um pagamento associado a um pedido.
     */
    @PutMapping("/{idPedido}")
    public ResponseEntity<?> atualizarStatusPagamento(@PathVariable String idPedido, @RequestBody String novoStatus) {
        try {
            Pagamento pagamentoAtualizado = service.atualizarStatusPagamento(idPedido, novoStatus);
            return ResponseEntity.ok(pagamentoAtualizado);
        } catch (IllegalArgumentException e) {
            // Captura problemas com o status inválido
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            // Captura exceções genéricas, como "Pagamento não encontrado"
            return ResponseEntity.notFound().build();
        }
    }
}

