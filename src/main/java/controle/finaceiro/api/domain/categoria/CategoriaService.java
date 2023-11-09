package controle.finaceiro.api.domain.categoria;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria createCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria updateCategoria(Long id, Categoria categoriaDetails) {
        Categoria categoria = getCategoriaById(id);
        if (categoriaDetails.getNome() != null) {
            categoria.setNome(categoriaDetails.getNome());
        }
        if (categoriaDetails.getCor() != null) {
            categoria.setCor(categoriaDetails.getCor());
        }
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria getCategoriaById(Long id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
    }

    public void deleteCategoria(Long id) {
        categoriaRepository.delete(getCategoriaById(id));
    }
}