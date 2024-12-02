package com.trabalhoArquitetura.trabalho.Service;

import com.trabalhoArquitetura.trabalho.Model.Pagamento;
import com.trabalhoArquitetura.trabalho.Repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    public Pagamento criarPagamento(Pagamento pagamento) {
        pagamento.setStatus("pendente");
        return repository.save(pagamento);
    }

    public Optional<Pagamento> consultarPagamento(String idPedido) {
        return repository.findByIdPedido(idPedido);
    }

    public Pagamento atualizarStatusPagamento(String idPedido, String novoStatus) {
        Pagamento pagamento = repository.findByIdPedido(idPedido)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));

        if (!novoStatus.equals("pago") && !novoStatus.equals("pendente")) {
            throw new IllegalArgumentException("Status inválido");
        }

        pagamento.setStatus(novoStatus);
        return repository.save(pagamento);
    }
}
