package fiap.scj.casamento.activity

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import fiap.scj.casamento.R
import fiap.scj.casamento.adapter.ListaCasamentoAdapter
import fiap.scj.casamento.api.CasamentoApi
import fiap.scj.casamento.api.RetrofitClient
import fiap.scj.casamento.model.Casamento
import fiap.scj.casamento.preference.Preference
import kotlinx.android.synthetic.main.activity_lista.*
import kotlinx.android.synthetic.main.content_lista.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)
        setSupportActionBar(toolbarLista)

        fabAdicionar.setOnClickListener {
            startActivity(Intent(this, EdicaoActivity::class.java))
            this.finish()
        }

        carregarDados()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.miLogout -> {
                val edit = Preference.getInstance(this).edit()
                edit.remove("usuario_logado")
                edit.commit()
                startActivity(Intent(this, LoginActivity::class.java))
                this.finish()
            }
            R.id.miSobre -> {
                startActivity(Intent(this, SobreActivity::class.java))
                this.finish()
            }
            R.id.miRefresh -> {
                carregarDados()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun carregarDados() {
        val api = RetrofitClient
                .getInstance()
                .create(CasamentoApi::class.java)

        val listaActivity = this

        api.buscarTodos()
                .enqueue(object : Callback<List<Casamento>> {
                    override fun onFailure(call: Call<List<Casamento>>?, t: Throwable?) {
                        Toast.makeText(listaActivity, resources.getString(R.string.erro) + t?.message, Toast.LENGTH_SHORT).show()
                        Log.e("CASAMENTO", t?.message)
                    }

                    override fun onResponse(call: Call<List<Casamento>>?, response: Response<List<Casamento>>?) {
                        if (response?.isSuccessful == true) {
                            setupLista(response?.body())
                        } else {
                            Toast.makeText(listaActivity, resources.getString(R.string.falha) + response?.errorBody()
                                    ?.charStream()?.readText(), Toast.LENGTH_SHORT).show()
                        }
                    }
                })
    }

    fun setupLista(casamentos: List<Casamento>?) {
        casamentos.let {
            rvCasamentos.adapter = ListaCasamentoAdapter(casamentos!!, this)
            val layoutManager = LinearLayoutManager(this)
            rvCasamentos.layoutManager = layoutManager
        }

    }

    fun removerCasamento(casamento: Casamento) {
        val listaActivity = this
        val api = RetrofitClient.getInstance().create(CasamentoApi::class.java)
        api.removerPorId(casamento.id).enqueue(object : Callback<Void> {

            override fun onFailure(call: Call<Void>?, t: Throwable?) {
                Toast.makeText(listaActivity, resources.getString(R.string.erro) + t?.message, Toast.LENGTH_SHORT).show()
                Log.e("CASAMENTO", t?.message)
            }

            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                if (response?.isSuccessful == true) {
                    Toast.makeText(listaActivity, R.string.casamento_removido_com_sucesso, Toast.LENGTH_SHORT).show()
                    carregarDados()
                } else {
                    Toast.makeText(listaActivity, resources.getString(R.string.falha_remover_casamento_por_id) + casamento.id, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun configurarOnClick(casamento: Casamento, itemView : View) {
        val listaActivity = this
        itemView.setOnClickListener {
            val builder = AlertDialog.Builder(listaActivity)
            builder.setTitle(R.string.acao)
            builder.setItems(arrayOf<CharSequence>(resources.getString(R.string.alterar), resources.getString(R.string.remover)), DialogInterface.OnClickListener { dialogInterface, i ->
                run {
                    when (i) {
                        0 -> {
                            abrirEdicaoActivity(casamento)
                        }
                        1 -> {
                            removerCasamento(casamento)
                        }
                    }
                }
            })
            builder.show()
        }
    }

    private fun abrirEdicaoActivity(casamento: Casamento) {
        val intent = Intent(this, EdicaoActivity::class.java)
        intent.putExtra("ID", casamento.id)
        this.startActivity(intent)
        this.finish()
    }

}
