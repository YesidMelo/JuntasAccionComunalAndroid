package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.listaAfiliadosModificacionDirectivas.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hefesto.juntasaccioncomunal.R
import com.hefesto.juntasaccioncomunal.logica.modelos.home.AfiliadoModificacionDirectivaModel

class ListaAfiliadosModificacionDirectivaAdapter constructor(
    var listaAfiliados: List<AfiliadoModificacionDirectivaModel>,
    var escuchadorSeleccionado: (AfiliadoModificacionDirectivaModel) -> Unit
)
    : RecyclerView.Adapter<ListaAfiliadosModificacionDirectivaAdapter.DetalleItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetalleItem {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemlista_afiliado_modificaciondirectiva, null, false)
        view.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
        return DetalleItem(view = view)
    }

    override fun onBindViewHolder(holder: DetalleItem, position: Int) {
        holder
            .configurarDetalle(afiliado = listaAfiliados[position])
            .configurarEscuchador(afiliado = listaAfiliados[position])
    }

    override fun getItemCount(): Int = listaAfiliados.size
    

    inner class DetalleItem(val view: View) : RecyclerView.ViewHolder(view) {

        fun configurarDetalle(afiliado: AfiliadoModificacionDirectivaModel) : DetalleItem{
            configurarNombres(afiliado = afiliado)
            configurarEstadoAfiliacion(afiliado = afiliado)
            configurarRolApp(afiliado = afiliado)
            return this
        }

        fun configurarEscuchador(afiliado: AfiliadoModificacionDirectivaModel) : DetalleItem {
            view.setOnClickListener {
                escuchadorSeleccionado.invoke(afiliado)
            }
            return this
        }

        //region metodos privados
        private fun configurarNombres(afiliado: AfiliadoModificacionDirectivaModel) {
            val nombres = view.findViewById<TextView>(R.id.textView_nombreCompleto_modificacion_directiva)
            nombres.text = "${afiliado.nombres} ${afiliado.apellidos}"
        }

        private fun configurarRolApp(afiliado: AfiliadoModificacionDirectivaModel) {
            val rolApp = view.findViewById<TextView>(R.id.textView_afiliado_rolApp)
            rolApp.setText(afiliado.rolApp.traerStringRes())
        }

        private fun configurarEstadoAfiliacion(afiliado: AfiliadoModificacionDirectivaModel) {
            val estado = view.findViewById<TextView>(R.id.textView_estadoAfiliacion_modificacion_directiva)
            estado.setText(afiliado.estadoAfiliacion.traerStringRes())
        }

        //endregion
    }

}