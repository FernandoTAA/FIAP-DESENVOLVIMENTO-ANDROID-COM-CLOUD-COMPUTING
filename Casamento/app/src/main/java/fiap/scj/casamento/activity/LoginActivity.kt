package fiap.scj.casamento.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import fiap.scj.casamento.R
import fiap.scj.casamento.api.RetrofitClient
import fiap.scj.casamento.api.UsuarioApi
import fiap.scj.casamento.model.Mensagem
import fiap.scj.casamento.preference.Preference
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btEntrar.setOnClickListener {
            val usuario: String = tilUsuario.editText?.text.toString()
            val senha: String = tilSenha.editText?.text.toString()
            val manterConectado = cbManterConectado.isChecked
            usuarioValido(usuario, senha, manterConectado)
        }

        btCadastrar.setOnClickListener {
            startActivity(Intent(this, CadastroActivity::class.java))
            this.finish()
        }
    }

    private fun usuarioValido(usuario: String, senha: String, manterConectado: Boolean) {
        val loginActivity: LoginActivity = this;
        val api = RetrofitClient.getInstance().create(UsuarioApi::class.java)

        api.autenticar(usuario, senha).enqueue(object : Callback<Mensagem> {
            override fun onFailure(call: Call<Mensagem>?, t: Throwable?) {
                Log.e("USUARIO", t?.message)
            }

            override fun onResponse(call: Call<Mensagem>?, response: Response<Mensagem>?) {
                if (response?.isSuccessful != true) {
                    Toast.makeText(loginActivity, R.string.falha_autenticacao, Toast.LENGTH_SHORT).show()
                } else {
                    loginActivity.manterConectado(manterConectado, usuario)

                    startActivity(Intent(loginActivity, ListaActivity::class.java))
                    loginActivity.finish()
                }
            }

        })
    }

    private fun manterConectado(manterConectado: Boolean, usuario: String) {
        val edit = Preference.getInstance(this).edit()
        if (manterConectado) {
            edit.putString("usuario_logado", usuario)
        } else {
            edit.remove("usuario_logado")
        }
        edit.commit()
    }
}
