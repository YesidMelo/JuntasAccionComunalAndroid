package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.registrarAfiliado.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hefesto.juntasaccioncomunal.R
import com.hefesto.juntasaccioncomunal.logica.modelos.home.DatosBasicosAfiliadoActualizarRegistrarInformacionModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirAFormato

class ListaAfiliadosRecyclerViewAdapter constructor(
    val listaAfiliados: List<DatosBasicosAfiliadoActualizarRegistrarInformacionModel>,
    val escuchadorItemSeleccionado: (DatosBasicosAfiliadoActualizarRegistrarInformacionModel) -> Unit
) : RecyclerView.Adapter<ListaAfiliadosRecyclerViewAdapter.Item> (){

    inner class Item(val view: View): RecyclerView.ViewHolder(view) {

        fun configurarVista(afiliado : DatosBasicosAfiliadoActualizarRegistrarInformacionModel) : Item {
            ponerNombres(afiliado = afiliado)
            ponerEstadoAfiliacion(afiliado= afiliado)
            ponerDocumentos(afiliado= afiliado)
            ponerFechaAfiliacion(afiliado = afiliado)
            return this
        }

        fun ponerEscuchadorSeleccion(afiliado : DatosBasicosAfiliadoActualizarRegistrarInformacionModel) : Item {
            view.setOnClickListener {
                escuchadorItemSeleccionado.invoke(afiliado)
            }
            return this
        }

        //region metodos privados
        private fun ponerNombres(afiliado : DatosBasicosAfiliadoActualizarRegistrarInformacionModel) {
            (view.findViewById<TextView>(R.id.textView_nombre_afiliado_jac)).text = "${afiliado.nombres} ${afiliado.apellidos}"
        }

        private fun ponerEstadoAfiliacion(afiliado : DatosBasicosAfiliadoActualizarRegistrarInformacionModel) {
            if (afiliado.datosJACModel?.estadoAfiliacion == null) return
            (view.findViewById<TextView>(R.id.textView_estado_afiliacion_home)).setText(afiliado.datosJACModel?.estadoAfiliacion!!.traerStringRes())
        }

        private fun ponerDocumentos(afiliado : DatosBasicosAfiliadoActualizarRegistrarInformacionModel) {
            (view.findViewById<TextView>(R.id.textView_identificacion_home)).text = afiliado.documento
        }

        private fun ponerFechaAfiliacion(afiliado : DatosBasicosAfiliadoActualizarRegistrarInformacionModel) {
            (view.findViewById<TextView>(R.id.textView_fechaRegistro_home)).text = afiliado.fechaNacimiento?.convertirAFormato(formato = FormatosFecha.SLASH_1)
        }
        //endregion
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemlista_afiliados_jac, null, false)
        view.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
        return Item(view = view)
    }

    override fun onBindViewHolder(holder: Item, position: Int) {
        holder
            .configurarVista(afiliado = listaAfiliados[position])
            .ponerEscuchadorSeleccion(afiliado = listaAfiliados[position])
    }

    override fun getItemCount(): Int = listaAfiliados.size
}