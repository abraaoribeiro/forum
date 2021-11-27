package br.com.alura.forum.service

import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsuarioService(
    private var usuarios: List<Usuario>
) {

    init {
        val usuario = Usuario(id = 1, nome = "Ana", email = "ana@mail.com")

        usuarios = Arrays.asList(usuario)
    }

    fun buscarPorId(idAutor: Long): Usuario {
        return usuarios.first { autor -> autor.id == idAutor }
    }
}