package controle.finaceiro.api.domain.tarefa;

import controle.finaceiro.api.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByCreatedBy(User user, Pageable pageable);

    Page<Task> findByAssignedTo(User user, Pageable pageable);
}
