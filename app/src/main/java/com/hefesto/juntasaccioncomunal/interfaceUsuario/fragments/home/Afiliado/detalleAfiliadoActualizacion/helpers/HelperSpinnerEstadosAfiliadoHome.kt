package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.detalleAfiliadoActualizacion.helpers

import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.hefesto.juntasaccioncomunal.logica.modelos.general.EstadoAfiliadoModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.EstadoAfiliacion

class HelperSpinnerEstadosAfiliadoHome {

    //region variables
    private lateinit var spinner: Spinner
    private lateinit var listaEstados: List<EstadoAfiliadoModel>
    private lateinit var adapter: ArrayAdapter<EstadoAfiliadoModel>
    private var estadoSeleccionado : EstadoAfiliacion? = null
    //endregion

    fun conSpinner(spinner: Spinner) : HelperSpinnerEstadosAfiliadoHome {
        this.spinner = spinner
        return this
    }

    fun conListaEstados(listaEstados: List<EstadoAfiliadoModel>) : HelperSpinnerEstadosAfiliadoHome {
        this.listaEstados = listaEstados
        return this
    }

    fun conEstadoSeleccionado(estadoSeleccionado : EstadoAfiliacion?) : HelperSpinnerEstadosAfiliadoHome {
        this.estadoSeleccionado = estadoSeleccionado
        ponerEstadoSeleccionado()
        return this
    }

    fun cargarSpinner() {
        try {
            adapter = ArrayAdapter(spinner.context!!, android.R.layout.simple_list_item_1, listaEstados)
            spinner.adapter = adapter
        } catch (e : Exception) {
            Log.e("error", "molestro",e)
        }
    }

    fun traerEstadoAfiliado() = (spinner.selectedItem as EstadoAfiliadoModel).estadoAfiliacion

    //region metodos privados
    private fun ponerEstadoSeleccionado() {
        val estado = estadoSeleccionado?:return
        val lista = listaEstados.filter { return@filter it.estadoAfiliacion == estado }
        if (lista.isEmpty()) return
        val modelo = lista.first()
        spinner.setSelection(listaEstados.indexOf(modelo))
    }
    //endregion
}