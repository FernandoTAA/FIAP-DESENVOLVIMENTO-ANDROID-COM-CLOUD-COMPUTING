package fiap.scj.casamento.preference

import android.content.ContextWrapper
import android.content.SharedPreferences

object Preference {
    private var setting: SharedPreferences? = null

    fun getInstance(context: ContextWrapper): SharedPreferences {
        if (setting == null) {
            setting = context.getSharedPreferences("casamento", 0)
        }
        return setting!!
    }
}