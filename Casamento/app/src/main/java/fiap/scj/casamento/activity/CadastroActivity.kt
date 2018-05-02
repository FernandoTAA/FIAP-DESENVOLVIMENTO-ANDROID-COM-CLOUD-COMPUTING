package fiap.scj.casamento.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import fiap.scj.casamento.R
import fiap.scj.casamento.api.RetrofitClient
import fiap.scj.casamento.api.UsuarioApi
import fiap.scj.casamento.model.EdicaoUsuario
import fiap.scj.casamento.model.Mensagem
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.content_cadastro.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
        setSupportActionBar(toolbarCadastro)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)

        btnCadastroUsuarioSalvar.setOnClickListener {
            if (tilCadastroUsuarioNome.editText?.text.toString().isBlank()) {
                Toast.makeText(this, R.string.nome_obrigatorio, Toast.LENGTH_SHORT).show()
            } else if (tilCadastroUsuarioEmail.editText?.text.toString().isBlank()) {
                Toast.makeText(this, R.string.email_obrigatorio, Toast.LENGTH_SHORT).show()
            } else if (tilCadastroUsuarioUsuario.editText?.text.toString().isBlank()) {
                Toast.makeText(this, R.string.usuario_obrigatorio, Toast.LENGTH_SHORT).show()
            } else if (tilCadastroUsuarioSenha.editText?.text.toString().isBlank()) {
                Toast.makeText(this, R.string.senha_obrigatorio, Toast.LENGTH_SHORT).show()
            } else if (tilCadastroUsuarioConfirmacaoSenha.editText?.text.toString().isBlank()) {
                Toast.makeText(this, R.string.confirmacao_senha_obrigatorio, Toast.LENGTH_SHORT).show()
            } else if (!tilCadastroUsuarioSenha.editText?.text.toString().equals(tilCadastroUsuarioConfirmacaoSenha.editText?.text.toString())) {
                Toast.makeText(this, R.string.senhas_diferentes, Toast.LENGTH_SHORT).show()
            } else {
                val api = RetrofitClient.getInstance().create(UsuarioApi::class.java)
                val cadastroActivity = this

                val criacaoUsuario = EdicaoUsuario(
                        tilCadastroUsuarioNome.editText?.text.toString(),
                        tilCadastroUsuarioEmail.editText?.text.toString(),
                        tilCadastroUsuarioUsuario.editText?.text.toString(),
                        tilCadastroUsuarioSenha.editText?.text.toString()
                )

                api.cadastrar(criacaoUsuario).enqueue(object : Callback<Mensagem> {
                    override fun onFailure(call: Call<Mensagem>?, t: Throwable?) {
                        Toast.makeText(cadastroActivity, resources.getString(R.string.erro) + t?.message, Toast.LENGTH_SHORT).show()
                        Log.e("USUARIO", t?.message)
                    }

                    override fun onResponse(call: Call<Mensagem>?, response: Response<Mensagem>?) {
                        if (response?.isSuccessful == true) {
                            startActivity(Intent(cadastroActivity, LoginActivity::class.java))
                            cadastroActivity.finish()
                        } else {
                            Toast.makeText(cadastroActivity, R.string.falha_cadastrar_usuario, Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this, LoginActivity::class.java))
        this.finish()
        return super.onSupportNavigateUp()
    }
}
