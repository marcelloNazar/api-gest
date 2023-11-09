package controle.finaceiro.api.controller;

import controle.finaceiro.api.domain.categoria.Categoria;
import controle.finaceiro.api.domain.categoria.CategoriaService;
import controle.finaceiro.api.domain.tarefa.Task;
import controle.finaceiro.api.domain.tarefa.TaskDTO;
import controle.finaceiro.api.domain.tarefa.TaskService;
import controle.finaceiro.api.domain.transacao.Transacao;
import controle.finaceiro.api.domain.transacao.TransacaoService;
import controle.finaceiro.api.user.User;
import controle.finaceiro.api.user.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/admin")
@SecurityRequirement(name = "bearer-key")
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private TransacaoService transacaoService;
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/categoria")
    public ResponseEntity<Categoria> createCategoria( @RequestBody Categoria categoria) {
        return ResponseEntity.status(CREATED).body(categoriaService.createCategoria(categoria));
    }

    @PutMapping("/categoria/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.updateCategoria(id, categoria));
    }

    @DeleteMapping("/categoria/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        categoriaService.deleteCategoria(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/tarefa")
    public ResponseEntity<Task> createTask( @RequestBody TaskDTO taskDTO) {
        return ResponseEntity.status(CREATED).body(taskService.createTask(taskDTO));
    }

    @GetMapping("/tarefa")
    public ResponseEntity<Page<Task>> getAllTasks(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "data") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        return ResponseEntity.ok(taskService.getAllTasksAdmin(pageable));
    }

    @GetMapping("/tarefa/user/{userId}")
    public ResponseEntity<Page<Task>> getAllTasksByUser(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "data") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        return ResponseEntity.ok(taskService.getTasksByUserId(userId, pageable));
    }

    @PutMapping("/tarefa/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @Valid @RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(taskService.updateTask(id, taskDTO));
    }

    @DeleteMapping("/tarefa/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/transacao")
    public ResponseEntity<Page<Transacao>> getAllTransacoes(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "data") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        return ResponseEntity.ok(transacaoService.getAllTransacoesAdmin(pageable));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/usuarios")
    public ResponseEntity<Page<User>> getAllUsers(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "username") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        return ResponseEntity.ok(userRepository.findAll(pageable));
    }

}