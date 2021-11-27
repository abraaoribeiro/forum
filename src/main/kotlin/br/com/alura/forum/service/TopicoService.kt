package br.com.alura.forum.service

import br.com.alura.forum.dto.AtualizacaoTopicoForm
import br.com.alura.forum.dto.NovoTopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Topico
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicoService(
    private var topicos: List<Topico>,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper
) {


    fun listar(): List<TopicoView> {
        return topicos.stream()
            .map { topico -> topicoViewMapper.map(topico) }
            .collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = topicos.stream().filter { t ->
            t.id == id
        }.findFirst().get()
        return topicoViewMapper.map(topico)
    }

    fun cadastrar(form: NovoTopicoForm): TopicoView {
        val topico = topicoFormMapper.map(form)
        topico.id = topicos.size.toLong() + 1
        topicos = topicos.plus(topico)
        return topicoViewMapper.map(topico)
    }

    fun atualizar(id: Long, form: AtualizacaoTopicoForm): TopicoView {
        val topico = topicos.stream()
            .filter { t -> t.id == id }.findFirst().get()
        val topoicoAtualizado =  Topico(
            id = form.id,
            titulo = form.titulo,
            mensagem = form.mensagem,
            curso = topico.curso,
            autor = topico.autor,
            respostas = topico.respostas,
            status = topico.status,
            dataCriacao = topico.dataCriacao
        )

        topicos = topicos.minus(topico).plus(topoicoAtualizado)
        return topicoViewMapper.map(topoicoAtualizado)
    }

    fun deletar(id: Long) {
        val topico = topicos.stream()
            .filter { t -> t.id == id }.findFirst().get()
        topicos = topicos.minus(topico)
    }
}