package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarActaPdf.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hefesto.juntasaccioncomunal.R
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.actasParaPDF.ReunionParaGenerarPDFModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirAFormato

class ListaActasParaPDFAdapter constructor(
    private val listaActasParaPDF: List<ReunionParaGenerarPDFModel>,
    private val reunionSeleccionado: (ReunionParaGenerarPDFModel)->Unit
) : RecyclerView.Adapter<ListaActasParaPDFAdapter.Item> () {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_generarpdf_reunion, null, false)
        view.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
        return Item(view = view)
    }

    override fun onBindViewHolder(holder: Item, position: Int) {
        holder.llenar(acta = listaActasParaPDF[position]).ponerEscuchador()
    }

    override fun getItemCount(): Int = listaActasParaPDF.size

    inner class Item(private val view: View) :RecyclerView.ViewHolder(view) {

        //region variables
        private lateinit var acta : ReunionParaGenerarPDFModel
        //endregion

        fun llenar(acta: ReunionParaGenerarPDFModel) : Item {
            this.acta = acta
            llenarVista()
            return this
        }

        fun ponerEscuchador() {
            view.setOnClickListener {
                reunionSeleccionado.invoke(acta)
            }
        }


        //region metodos privados
        private fun llenarVista() {
            llenarAsunto()
            llenarFechaReunion()
            llenarHoraReunion()
            llenarTipoReunion()
        }

        private fun llenarAsunto() {
            val textview = view.findViewById<TextView>(R.id.textView_generarPDF_detalleasunto)
            textview.text = acta.asuntoReunion
        }

        private fun llenarFechaReunion() {
            val textview = view.findViewById<TextView>(R.id.textView_generarPDF_detallefechaconvocatoria)
            textview.text = acta.fechaRegistro?.convertirAFormato(formato = FormatosFecha.ANIO_MES_DIA_GIONES)
        }

        private fun llenarHoraReunion() {
            val textview = view.findViewById<TextView>(R.id.textView_generarPDF_detalleHoraConvocatoria)
            textview.text = acta.fechaYHoraProgramacionReunion?.convertirAFormato(formato = FormatosFecha.HORA_MINUTO_12H)
        }

        private fun llenarTipoReunion() {
            val textview = view.findViewById<TextView>(R.id.textView_generarPDF_detalleTipoReunion)
            textview.setText(acta.tipoReunion!!.traerStringRes())
        }


        //endregion

    }

}