package controle.finaceiro.api.domain.tarefa;

import controle.finaceiro.api.user.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskDTO {
    private String titulo;
    private String descricao;
    private Boolean feito;
    private Long assignedToId;
    private String data;

}
