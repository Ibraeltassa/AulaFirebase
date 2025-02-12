package com.ibrahim.aulafirebase

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.ibrahim.aulafirebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnExecutar.setOnClickListener{
            cadastroUsuario()
        }

    }

    private fun cadastroUsuario() {

        //Dados digitados pelo usuário
        val email = "jamilton.jm@gmail.com"
        val senha = "12345jm67@"

        //Tela de cadastro do App
        val autenticacao = FirebaseAuth.getInstance()
        autenticacao.createUserWithEmailAndPassword(
            email, senha
        ).addOnSuccessListener { authResult ->

            val email = authResult.user?.email
            val id = authResult.user?.uid

            //exibirMensagem("Sucesso ao cadastrar usuário: $id - $email")
            binding.bntResultado.text = "sucesso: $id - $email"
        }.addOnFailureListener { exception ->
            val mensagemErro = exception.message
            binding.bntResultado.text = "Erro: $mensagemErro"
        }

    }

    private fun exibirMensagem(texto: String) {
        Toast.makeText(this, texto, Toast.LENGTH_LONG).show()
    }

}