package com.brunafenali.finanas

enum class Moeda {
    EUR, BRL;

    fun simbolo(): String {
        return when (this) {
            EUR -> "€"
            BRL -> "R$"
        }
    }
}