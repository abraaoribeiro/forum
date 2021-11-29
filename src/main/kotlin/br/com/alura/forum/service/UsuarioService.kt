package br.com.alura.forum.service

import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.model.Usuario
import br.com.alura.forum.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UsuarioService(
    private val usuarioRepository: UsuarioRepository
):UserDetailsService {

    fun buscarPorId(idAutor: Long): Usuario {
        return usuarioRepository.getById(idAutor)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
       val usuario = usuarioRepository.findByEmail(username)
           .orElseThrow{NotFoundException("Usuario não encontrado")}

       return UserDetailService(usuario)
    }
}