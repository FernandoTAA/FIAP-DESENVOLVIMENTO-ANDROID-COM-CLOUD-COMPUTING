package fiap.scj.casamento.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import fiap.scj.casamento.R
import kotlinx.android.synthetic.main.activity_sobre.*

class SobreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sobre)
        setSupportActionBar(toolbarSobre)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this, ListaActivity::class.java))
        this.finish()
        return super.onSupportNavigateUp()
    }
}
