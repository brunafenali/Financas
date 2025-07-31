package com.brunafenali.finanas // ou o nome correto do seu pacote

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnEuro: Button
    private lateinit var btnReal: Button
    private lateinit var txtSaldo: TextView
    private lateinit var listView: ListView

    private var moedaAtual = Moeda.EUR
    private val transacoesMockadas = listOf(
        Transacao(TipoTransacao.ENTRADA, 2500.0, "Salário", "Julho", System.currentTimeMillis(), Moeda.EUR),
        Transacao(TipoTransacao.SAIDA, 800.0, "Aluguel", "Apartamento", System.currentTimeMillis(), Moeda.EUR),
        Transacao(TipoTransacao.ENTRADA, 1200.0, "Investimento", "Brasil", System.currentTimeMillis(), Moeda.BRL),
        Transacao(TipoTransacao.SAIDA, 300.0, "Compras", "Loja online", System.currentTimeMillis(), Moeda.BRL)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnEuro = findViewById(R.id.btn_euro)
        btnReal = findViewById(R.id.btn_real)
        txtSaldo = findViewById(R.id.txt_saldo)
        listView = findViewById(R.id.list_transacoes)

        btnEuro.setOnClickListener {
            moedaAtual = Moeda.EUR
            atualizarUI()
        }

        btnReal.setOnClickListener {
            moedaAtual = Moeda.BRL
            atualizarUI()
        }

        atualizarUI()
    }

    private fun atualizarUI() {
        val transacoesFiltradas = transacoesMockadas.filter { it.moeda == moedaAtual }
        val saldo = transacoesFiltradas.sumOf {
            if (it.tipo == TipoTransacao.ENTRADA) it.valor else -it.valor
        }

        txtSaldo.text = "Saldo: ${moedaAtual.simbolo()} %.2f".format(saldo)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            transacoesFiltradas.map { "${it.categoria}: ${moedaAtual.simbolo()}${it.valor}" }
        )
        listView.adapter = adapter
    }
}
