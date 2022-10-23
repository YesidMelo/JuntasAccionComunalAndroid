package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.agendarReunion.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hefesto.juntasaccioncomunal.R
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.agendarReunion.PuntoReunionAgendarReunionAsambleaModel

class ListaPuntosReunionAgendarAdapter constructor(
    val listaPuntos: List<PuntoReunionAgendarReunionAsambleaModel>,
    val eliminarItem : (PuntoReunionAgendarReunionAsambleaModel) -> Unit
) : RecyclerView.Adapter<ListaPuntosReunionAgendarAdapter.Item> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_agendarreunion_puntoreunion, null, false)
        view.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
        return Item(view = view)
    }

    override fun onBindViewHolder(holder: Item, position: Int) {
        holder
            .llenar(punto = listaPuntos[position], position)
            .ponerEscuchador()
    }

    override fun getItemCount(): Int = listaPuntos.size

    inner class Item(val view: View) : RecyclerView.ViewHolder(view) {

        //region variables
        private lateinit var punto: PuntoReunionAgendarReunionAsambleaModel
        private var posision = 0
        //endregion

        fun llenar(punto: PuntoReunionAgendarReunionAsambleaModel, posision: Int) : Item {
            this.punto = punto
            this.posision = posision + 1
            llenarTextoTitulo()
            return this
        }

        fun ponerEscuchador() {
            val imgeViewEliminar = view.findViewById<ImageView>(R.id.imageView_agendarReunion_eliminarPunto)?:return
            imgeViewEliminar.setOnClickListener {
                eliminarItem.invoke(punto)
            }
        }

        //region metodos privados
        private fun llenarTextoTitulo() {
            val texto = view.findViewById<TextView>(R.id.textView_agendarCita_tituloPunto)
            texto.text = "$posision. ${punto.tituloPunto}"
        }
        //endregion

    }
}