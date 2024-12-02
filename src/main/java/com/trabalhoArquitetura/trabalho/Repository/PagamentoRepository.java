package com.trabalhoArquitetura.trabalho.Repository;

import com.trabalhoArquitetura.trabalho.Model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    Optional<Pagamento> findByIdPedido(String idPedido);
}
