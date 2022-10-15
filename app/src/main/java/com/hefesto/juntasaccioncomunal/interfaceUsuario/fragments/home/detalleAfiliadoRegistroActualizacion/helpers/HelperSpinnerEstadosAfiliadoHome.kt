package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.helpers

import android.widget.ArrayAdapter
import android.widget.Spinner
import com.hefesto.juntasaccioncomunal.logica.modelos.general.EstadoAfiliadoModel

class HelperSpinnerEstadosAfiliadoHome {

    //region variables
    private lateinit var spinner: Spinner
    private lateinit var listaEstados: List<EstadoAfiliadoModel>
    private lateinit var adapter: ArrayAdapter<EstadoAfiliadoModel>
    //endregion

    fun conSpinner(spinner: Spinner) : HelperSpinnerEstadosAfiliadoHome {
        this.spinner = spinner
        return this
    }

    fun conListaEstados(listaEstados: List<EstadoAfiliadoModel>) : HelperSpinnerEstadosAfiliadoHome {
        this.listaEstados = listaEstados
        return this
    }

    fun cargarSpinner() {
        adapter = ArrayAdapter(spinner.context!!, android.R.layout.simple_list_item_1, listaEstados)
        spinner.adapter = adapter
    }

    fun traerEstadoAfiliado() = (spinner.selectedItem as EstadoAfiliadoModel).estadoAfiliacion
}