package fiap.scj.casamento.api

import fiap.scj.casamento.model.EdicaoUsuario
import fiap.scj.casamento.model.Mensagem
import retrofit2.Call
import retrofit2.http.*

interface UsuarioApi {

    @POST("/usuario")
    fun cadastrar(@Body usuario: EdicaoUsuario): Call<Mensagem>

    @GET("/usuario/{usuario}/autenticar")
    fun autenticar(@Path("usuario") usuario: String, @Header("senha") senha: String): Call<Mensagem>

}