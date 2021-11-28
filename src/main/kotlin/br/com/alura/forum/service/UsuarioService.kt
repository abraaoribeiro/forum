package br.com.alura.forum.service

import br.com.alura.forum.model.Usuario
import br.com.alura.forum.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class UsuarioService(
    private val usuarioRepository: UsuarioRepository
) {

    fun buscarPorId(idAutor: Long): Usuario {
        return usuarioRepository.getById(idAutor)
    }
}