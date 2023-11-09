package controle.finaceiro.api.domain.tarefa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import controle.finaceiro.api.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tarefa")
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descricao;

    private Boolean feito;

    private String data;

    @ManyToOne
    @JoinColumn(name="created_by_id", nullable=false)
    @JsonManagedReference
    @JsonIgnore
    private User createdBy;

    @ManyToOne
    @JoinColumn(name="assigned_to_id", nullable=false)
    @JsonManagedReference
    private User assignedTo;

}
