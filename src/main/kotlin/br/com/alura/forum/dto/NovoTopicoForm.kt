package br.com.alura.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class NovoTopicoForm(
    @field:NotEmpty(message = "Titulo não pode ser em branco")
    val titulo: String,
    @field:NotEmpty(message = "Mensagem não pode ser em branco")
    val mensagem: String,
    @field:NotNull(message = "idCurso não pode ser nulo")
    val idCurso: Long,
    @field:NotNull(message = "idAutor não pode ser nulo")
    val idAutor: Long
)
