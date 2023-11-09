package controle.finaceiro.api.domain.transacao;

import controle.finaceiro.api.domain.categoria.Categoria;
import controle.finaceiro.api.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    Page<Transacao> findByUser(User user, Pageable pageable);

    List<Transacao> findByUserAndCategoria(User user, Categoria categoria);
}
