package br.com.alura.forum.dto

import javax.validation.constraints.NotEmpty

data class AtualizacaoTopicoForm(
    @field:NotEmpty
    val titulo: String,
    @field:NotEmpty
    val mensagem: String
)
