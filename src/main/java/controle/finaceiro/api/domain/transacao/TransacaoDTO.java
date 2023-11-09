package controle.finaceiro.api.domain.transacao;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransacaoDTO {
    private Boolean despesa;
    private BigDecimal valor;
    private String descricao;
    private Boolean pago;
    private String data;
    private String categoria;
}
