package controle.finaceiro.api.domain.tarefa;

import controle.finaceiro.api.service.UnauthorizedException;
import controle.finaceiro.api.user.User;
import controle.finaceiro.api.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public Task createTask(TaskDTO taskDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User admin = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
        User funcionario = userRepository.findById(taskDTO.getAssignedToId())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: "));
        Task task = new Task();
        task.setCreatedBy(admin);
        task.setAssignedTo(funcionario);
        task.setFeito(false);

        BeanUtils.copyProperties(taskDTO, task, "assignedToId", "feito");

        return taskRepository.save(task);
    }

    public Task updateTask(Long id, TaskDTO taskDetails) {
        Task task = getTaskById(id);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
        if (!task.getAssignedTo().equals(user)) {
            if (taskDetails.getTitulo() != null) {
                task.setTitulo(taskDetails.getTitulo());
            }
            if (taskDetails.getDescricao() != null) {
                task.setDescricao(taskDetails.getDescricao());
            }
            if (taskDetails.getFeito() != null) {
                task.setFeito(taskDetails.getFeito());
            }
        } else {
            throw new UnauthorizedException("O usuário não tem permissão para atualizar esta tarefa");
        }
        if (task.getCreatedBy().equals(user)) {
            if (taskDetails.getTitulo() != null) {
                task.setTitulo(taskDetails.getTitulo());
            }
            if (taskDetails.getDescricao() != null) {
                task.setDescricao(taskDetails.getDescricao());
            }
            if (taskDetails.getFeito() != null) {
                task.setFeito(taskDetails.getFeito());
            }
            if (taskDetails.getData() != null) {
                task.setData(taskDetails.getData());
            }
            if (taskDetails.getAssignedToId() != null) {
                User funcionario = userRepository.findById(taskDetails.getAssignedToId())
                        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: "));
                task.setAssignedTo(funcionario);
            }
        } else {
            throw new UnauthorizedException("O usuário não tem permissão para atualizar esta tarefa");
        }
        return taskRepository.save(task);
    }

    public Page<Task> getAllTasksAdmin(Pageable pageable) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
        return taskRepository.findByCreatedBy(user, pageable);
    }

    public Page<Task> getAllTasks(Pageable pageable) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
        return taskRepository.findByAssignedTo(user, pageable);
    }

    public Task getTaskById(Long id) {
        Optional<Task> taskOpt = taskRepository.findById(id);
        if (taskOpt.isPresent()) {
            return taskOpt.get();
        } else {
            throw new EntityNotFoundException("Tarefa não encontrada com o id " + id);
        }
    }
    public Page<Task> getTasksByUserId(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: "));
        return taskRepository.findByAssignedTo(user, pageable);
    }

    public void deleteTask(Long id) {
        taskRepository.delete(getTaskById(id));
    }
}
