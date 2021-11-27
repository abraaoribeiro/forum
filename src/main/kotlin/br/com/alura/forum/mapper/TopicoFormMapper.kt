package br.com.alura.forum.mapper

import br.com.alura.forum.dto.NovoTopicoForm
import br.com.alura.forum.model.Topico
import br.com.alura.forum.service.CursoService
import br.com.alura.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class TopicoFormMapper(
    private val cursoService: CursoService,
    private val usuarioService: UsuarioService
) : Mapper<NovoTopicoForm, Topico> {
    override fun map(topico: NovoTopicoForm): Topico {
        return Topico(
            titulo = topico.titulo,
            mensagem = topico.mensagem,
            curso = cursoService.buscarPorId(topico.idCurso),
            autor = usuarioService.buscarPorId(topico.idAutor)
        )
    }
}