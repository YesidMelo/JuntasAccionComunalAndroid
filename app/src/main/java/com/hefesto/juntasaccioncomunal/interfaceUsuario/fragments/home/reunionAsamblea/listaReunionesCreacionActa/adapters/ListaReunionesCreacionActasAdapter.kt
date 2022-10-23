package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.listaReunionesCreacionActa.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hefesto.juntasaccioncomunal.R
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.ReunionAsambleaCreacionActaModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirAFormato

class ListaReunionesCreacionActasAdapter constructor(
    private val listaReuniones: List<ReunionAsambleaCreacionActaModel>,
    private val reunionSeleccionada: (ReunionAsambleaCreacionActaModel)->Unit
) : RecyclerView.Adapter<ListaReunionesCreacionActasAdapter.Item>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_reunionasamblea_crearacta, null, false)
        view.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
        return Item(view = view)
    }

    override fun onBindViewHolder(holder: Item, position: Int) {
        holder.llenarVista(detalle = listaReuniones[position]).implementarEscuchador()
    }

    override fun getItemCount(): Int = listaReuniones.size


    inner class Item (val view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var detalle : ReunionAsambleaCreacionActaModel

        fun llenarVista(detalle: ReunionAsambleaCreacionActaModel) : Item {
            this.detalle = detalle
            ponerAsunto()
            ponerFechaReunion()
            ponerHoraReunion()
            ponerTipoReunion()
            return this
        }

        fun implementarEscuchador() {
            view.setOnClickListener {
                reunionSeleccionada.invoke(detalle)
            }
        }

        //region metodos privados
        private fun ponerAsunto() {
            val asunto = view.findViewById<TextView>(R.id.textView_listareuniones_detalleasunto)
            asunto.text = detalle.asuntoReunion
        }

        private fun ponerFechaReunion() {
            val fecha = view.findViewById<TextView>(R.id.textView_listareuniones_detallefechaconvocatoria)
            fecha.text = detalle.fechaYHoraProgramacionReunion?.convertirAFormato(formato = FormatosFecha.SLASH_1)
        }

        private fun ponerHoraReunion() {
            val hora = view.findViewById<TextView>(R.id.textView_listareuniones_detalleHoraConvocatoria)
            hora.text = detalle.fechaYHoraProgramacionReunion?.convertirAFormato(formato = FormatosFecha.HORA_MINUTO_12H)
        }

        private fun ponerTipoReunion() {
            val tipoReunion = view.findViewById<TextView>(R.id.textView_listareuniones_detalleTipoReunion)
            tipoReunion.setText(detalle.tipoReunion!!.traerStringRes())
        }


        //endregion
    }
}