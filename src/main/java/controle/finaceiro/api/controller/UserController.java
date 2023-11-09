package controle.finaceiro.api.controller;

import controle.finaceiro.api.domain.categoria.Categoria;
import controle.finaceiro.api.domain.categoria.CategoriaService;
import controle.finaceiro.api.domain.tarefa.Task;
import controle.finaceiro.api.domain.tarefa.TaskDTO;
import controle.finaceiro.api.domain.tarefa.TaskService;
import controle.finaceiro.api.domain.transacao.Transacao;
import controle.finaceiro.api.domain.transacao.TransacaoDTO;
import controle.finaceiro.api.domain.transacao.TransacaoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "bearer-key")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private TransacaoService transacaoService;
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private TaskService taskService;

    @PostMapping("/transacao")
    public ResponseEntity<Transacao> createTransacao(@RequestBody TransacaoDTO transacaoInput) {
        return ResponseEntity.status(CREATED).body(transacaoService.createTransacao(transacaoInput));
    }

    @GetMapping("/transacao")
    public ResponseEntity<Page<Transacao>> getAllTransacoes(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "data") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        return ResponseEntity.ok(transacaoService.getAllTransacoes(pageable));
    }

    @GetMapping("/transacao/{id}")
    public ResponseEntity<Transacao> getTransacaoById(@PathVariable Long id) {
        return ResponseEntity.ok(transacaoService.getTransacaoById(id));
    }

    @PutMapping("/transacao/{id}")
    public ResponseEntity<Transacao> updateTransacao(@PathVariable Long id, @RequestBody TransacaoDTO transacaoInput) {
        return ResponseEntity.ok(transacaoService.updateTransacao(id, transacaoInput));
    }

    @DeleteMapping("/transacao/{id}")
    public ResponseEntity<Void> deleteTransacao(@PathVariable Long id) {
        transacaoService.deleteTransacao(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categoria")
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        return ResponseEntity.ok(categoriaService.getAllCategorias());
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.getCategoriaById(id));
    }

    @GetMapping("/transacao/categoria/{categoriaId}")
    public ResponseEntity<List<Transacao>> getTransacoesByCategoria(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(transacaoService.getTransacoesByCategoria(categoriaId));
    }

    @GetMapping("/tarefa")
    public ResponseEntity<Page<Task>> getAllTasks(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "data") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        return ResponseEntity.ok(taskService.getAllTasks(pageable));
    }

    @PutMapping("/tarefa/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(taskService.updateTask(id, taskDTO));
    }
}
