package fiap.scj.casamento.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import fiap.scj.casamento.R
import fiap.scj.casamento.api.CasamentoApi
import fiap.scj.casamento.api.RetrofitClient
import fiap.scj.casamento.model.Casamento
import fiap.scj.casamento.model.EdicaoCasamento
import kotlinx.android.synthetic.main.activity_edicao.*
import kotlinx.android.synthetic.main.content_edicao.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.ParseException
import java.text.SimpleDateFormat

class EdicaoActivity : AppCompatActivity() {

    var casamentoId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edicao)
        setSupportActionBar(toolbarEdicao)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)

        val cadastroActivity = this
        val api = RetrofitClient.getInstance().create(CasamentoApi::class.java)

        if (intent.extras != null && intent.extras.getString("ID") != null) {
            casamentoId = intent.extras.getString("ID")

            api.buscarPorId(casamentoId!!).enqueue(object : Callback<Casamento> {

                override fun onFailure(call: Call<Casamento>?, t: Throwable?) {
                    Toast.makeText(cadastroActivity, resources.getString(R.string.erro) + t?.message, Toast.LENGTH_SHORT).show()
                    Log.e("CASAMENTO", t?.message)
                }

                override fun onResponse(call: Call<Casamento>?, response: Response<Casamento>?) {
                    if (response?.isSuccessful == true) {
                        val casamento = response.body()
                        tilEdicaoNomeHomem.editText!!.setText(casamento!!.nomeHomem)
                        tilEdicaoNomeMulher.editText!!.setText(casamento!!.nomeMulher)
                        tilEdicaoData.editText!!.setText(SimpleDateFormat("dd/MM/yyyy").format(SimpleDateFormat("yyyy-MM-dd").parse(casamento.data)))
                        tilEdicaoLocal.editText!!.setText(casamento!!.local)
                    } else {
                        Toast.makeText(cadastroActivity, resources.getString(R.string.falha_buscar_casamento_por_id) + casamentoId, Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }

        btnEdicaoSalvar.setOnClickListener {
            if (tilEdicaoNomeHomem.editText?.text.toString().isBlank()) {
                Toast.makeText(this, R.string.nome_homem_obrigatorio, Toast.LENGTH_SHORT).show()
            } else if (tilEdicaoNomeMulher.editText?.text.toString().isBlank()) {
                Toast.makeText(this, R.string.nome_mulher_obrigatorio, Toast.LENGTH_SHORT).show()
            } else if (tilEdicaoData.editText?.text.toString().isBlank()) {
                Toast.makeText(this, R.string.data_casamento_obrigatorio, Toast.LENGTH_SHORT).show()
            } else if (!isValidDate(tilEdicaoData.editText?.text.toString())) {
                Toast.makeText(this, R.string.formato_data_casamento_errado, Toast.LENGTH_SHORT).show()
            } else if (tilEdicaoLocal.editText?.text.toString().isBlank()) {
                Toast.makeText(this, R.string.local_casamento_obrigatorio, Toast.LENGTH_SHORT).show()
            } else {

                val data = SimpleDateFormat("yyyy-MM-dd").format(SimpleDateFormat("dd/MM/yyyy").parse(tilEdicaoData.editText?.text.toString()))
                val edicaoCasamento = EdicaoCasamento(
                        tilEdicaoNomeHomem.editText?.text.toString(),
                        tilEdicaoNomeMulher.editText?.text.toString(),
                        data,
                        tilEdicaoLocal.editText?.text.toString()
                )

                val salvarCasamentoCallback = object : Callback<Casamento> {
                    override fun onFailure(call: Call<Casamento>?, t: Throwable?) {
                        Toast.makeText(cadastroActivity, resources.getString(R.string.erro) + t?.message, Toast.LENGTH_SHORT).show()
                        Log.e("CASAMENTO", t?.message)
                    }

                    override fun onResponse(call: Call<Casamento>?, response: Response<Casamento>?) {
                        if (response?.isSuccessful == true) {
                            startActivity(Intent(cadastroActivity, ListaActivity::class.java))
                            cadastroActivity.finish()
                        } else {
                            Toast.makeText(cadastroActivity, R.string.falha_salvar_casamento, Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                if (casamentoId == null) {
                    api.inserir(edicaoCasamento).enqueue(salvarCasamentoCallback)
                } else {
                    api.atualizar(casamentoId!!, edicaoCasamento).enqueue(salvarCasamentoCallback)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this, ListaActivity::class.java))
        this.finish()
        return super.onSupportNavigateUp()
    }

    private fun isValidDate(toString: String): Boolean {
        try {
            SimpleDateFormat("dd/MM/yyyy").parse(tilEdicaoData.editText?.text.toString())
        } catch (e: ParseException) {
            return false
        }
        return true
    }

}
