package com.brunafenali.finanas

data class Transacao(
    val tipo: TipoTransacao,
    val valor: Double,
    val categoria: String,
    val descricao: String?,
    val data: Long,
    val moeda: Moeda
)
