package fiap.scj.casamento.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fiap.scj.casamento.R
import fiap.scj.casamento.activity.ListaActivity
import fiap.scj.casamento.model.Casamento
import kotlinx.android.synthetic.main.item_casamento.view.*
import java.text.SimpleDateFormat


class ListaCasamentoAdapter(private val casamentos: List<Casamento>, private val context: ListaActivity) : RecyclerView.Adapter<ListaCasamentoAdapter.CasamentoViewHolder>() {

    override fun onBindViewHolder(holder: CasamentoViewHolder?, position: Int) {
        val casamento = casamentos[position]
        holder?.let {
            it.bindView(casamento)
        }
    }

    override fun getItemCount(): Int {
        return casamentos.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CasamentoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_casamento, parent, false)
        return CasamentoViewHolder(view, context)
    }

    class CasamentoViewHolder(itemView: View, private val context: ListaActivity) : RecyclerView.ViewHolder(itemView) {
        fun bindView(casamento: Casamento) {
            context.configurarOnClick(casamento, itemView)
            itemView.tvItemCasamentoNomeHomem.text = casamento.nomeHomem
            itemView.tvItemCasamentoNomeMulher.text = casamento.nomeMulher
            itemView.tvItemCasamentoLocal.text = casamento.local
            if (casamento.data != null) {
                itemView.tvItemCasamentoData.text = SimpleDateFormat("dd/MM/yyyy").format(SimpleDateFormat("yyyy-MM-dd").parse(casamento.data))
            }
        }

    }


}