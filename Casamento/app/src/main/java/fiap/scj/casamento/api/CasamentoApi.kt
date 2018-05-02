package fiap.scj.casamento.api

import fiap.scj.casamento.model.Casamento
import fiap.scj.casamento.model.EdicaoCasamento
import retrofit2.Call
import retrofit2.http.*

interface CasamentoApi {

    @GET("/casamento")
    fun buscarTodos(): Call<List<Casamento>>

    @GET("/casamento/{casamentoId}")
    fun buscarPorId(@Path("casamentoId") casamentoId: String): Call<Casamento>

    @POST("/casamento")
    fun inserir(@Body casamento: EdicaoCasamento): Call<Casamento>

    @PUT("/casamento/{casamentoId}")
    fun atualizar(@Path("casamentoId") casamentoId: String, @Body casamento: EdicaoCasamento): Call<Casamento>

    @DELETE("/casamento/{casamentoId}")
    fun removerPorId(@Path("casamentoId") casamentoId: String) : Call<Void>

}