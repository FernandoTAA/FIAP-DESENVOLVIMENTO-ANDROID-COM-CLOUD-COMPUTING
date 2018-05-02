package fiap.scj.casamento.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.animation.AnimationUtils
import fiap.scj.casamento.R
import fiap.scj.casamento.preference.Preference
import kotlinx.android.synthetic.main.activity_splash.*

class
SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        carregar()
    }

    private fun carregar() {
        val animacao = AnimationUtils.loadAnimation(this, R.anim.splash)
        ivLogoSplash.startAnimation(animacao)

        Handler().postDelayed({
            val settings = Preference.getInstance(this)
            if (settings.getString("usuario_logado", null) == null) {
                startActivity(Intent(this, LoginActivity::class.java))
            } else {
                startActivity(Intent(this, ListaActivity::class.java))
            }
            this.finish()
        }, 3000)
    }
}
