package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.agendarReunion.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hefesto.juntasaccioncomunal.R
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.agendarReunion.ConvocanteReunionAsambleaAAgendarModel

class ListaConvocantesAgendarCitaAdapter constructor(
    private val listaConvocantes : List<ConvocanteReunionAsambleaAAgendarModel>,
    private val selectorItem: (ConvocanteReunionAsambleaAAgendarModel)->Unit
) : RecyclerView.Adapter<ListaConvocantesAgendarCitaAdapter.Item>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_agendarreunion_convocante, null, false)
        view.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
        return Item(view = view)
    }

    override fun onBindViewHolder(holder: Item, position: Int) {
        holder
            .llenar(detalle = listaConvocantes[position])
            .conEscuchador()
    }

    override fun getItemCount(): Int = listaConvocantes.size


    inner class Item(val view: View) : RecyclerView.ViewHolder(view) {

        //region variables
        private lateinit var detalle : ConvocanteReunionAsambleaAAgendarModel
        //endregion

        fun llenar(detalle : ConvocanteReunionAsambleaAAgendarModel) : Item {
            this.detalle = detalle
            llenarItem()
            return this
        }

        fun conEscuchador() {
            val imageView = view.findViewById<ImageView>(R.id.imageView_agendarreunion_eliminarconvocante)
            imageView.setOnClickListener {
                selectorItem.invoke(detalle)
            }
        }

        //region metodos privados
        private fun llenarItem() {
            val nombres = view.findViewById<TextView>(R.id.textView_agendarreunion_nombreconvocante)
            val documentos = view.findViewById<TextView>(R.id.textView_agendarreunion_documentoconvocante)
            nombres.text = "${detalle.nombres?:""} ${detalle.apellidos?:""}"
            documentos.text = detalle.numeroDocumento?:""
        }
        //endregion

    }
}