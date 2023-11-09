package controle.finaceiro.api.domain.categoria;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import controle.finaceiro.api.domain.transacao.Transacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String nome;
    private String cor;

}