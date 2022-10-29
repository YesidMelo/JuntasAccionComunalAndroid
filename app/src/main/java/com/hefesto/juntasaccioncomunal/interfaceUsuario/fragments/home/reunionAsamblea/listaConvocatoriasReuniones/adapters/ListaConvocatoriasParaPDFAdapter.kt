package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.listaConvocatoriasReuniones.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hefesto.juntasaccioncomunal.R
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.reunionParaConvocatoriaPDF.ReunionParaGenerarConvocatoriaPDFModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirAFormato

class ListaConvocatoriasParaPDFAdapter constructor(
    private var listaConvocatorias : List<ReunionParaGenerarConvocatoriaPDFModel>,
    private var itemSeleccionado: (ReunionParaGenerarConvocatoriaPDFModel) -> Unit
) : RecyclerView.Adapter<ListaConvocatoriasParaPDFAdapter.Item> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_generarconvocatoria_pdf, null, false)
        view.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
        return Item(view= view)
    }

    override fun onBindViewHolder(holder: Item, position: Int) {
        holder
            .llenar(listaConvocatorias[position])
            .ponerEscuchador()
    }

    override fun getItemCount(): Int= listaConvocatorias.size

    inner class Item(val view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var reunionParaGenerarConvocatoriaPDFModel: ReunionParaGenerarConvocatoriaPDFModel

        fun llenar(reunionParaGenerarConvocatoriaPDFModel: ReunionParaGenerarConvocatoriaPDFModel): Item {
            this.reunionParaGenerarConvocatoriaPDFModel = reunionParaGenerarConvocatoriaPDFModel
            llenarElementosVistas()
            return this
        }

        fun ponerEscuchador() {
            view.setOnClickListener {
                itemSeleccionado.invoke(reunionParaGenerarConvocatoriaPDFModel)
            }
        }

        //region metodos privados
        private fun llenarElementosVistas() {
            (view.findViewById<TextView>(R.id.textView_generarConvocatoriaPDF_detalleasunto)).text = reunionParaGenerarConvocatoriaPDFModel.asuntoReunion
            (view.findViewById<TextView>(R.id.textView_generarConvocatoriaPDF_detallefechaconvocatoria)).text = reunionParaGenerarConvocatoriaPDFModel.fechaYHoraProgramacionReunion?.convertirAFormato(formato = FormatosFecha.ANIO_MES_DIA_GIONES)
            (view.findViewById<TextView>(R.id.textView_generarConvocatoriaPDF_detalleHoraConvocatoria)).text = reunionParaGenerarConvocatoriaPDFModel.fechaYHoraProgramacionReunion?.convertirAFormato(formato = FormatosFecha.HORA_MINUTO_12H)
            (view.findViewById<TextView>(R.id.textView_generarConvocatoriaPDF_detalleTipoReunion)).setText(reunionParaGenerarConvocatoriaPDFModel.tipoReunion!!.traerStringRes())
        }
        //endregion
    }
}