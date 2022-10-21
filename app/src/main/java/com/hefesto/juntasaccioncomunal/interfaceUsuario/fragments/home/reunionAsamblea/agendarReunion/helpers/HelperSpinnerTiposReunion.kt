package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.agendarReunion.helpers

import android.widget.ArrayAdapter
import android.widget.Spinner
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.TipoReunionModel

class HelperSpinnerTiposReunion {

    //region variables
    private lateinit var spinner: Spinner
    private lateinit var listaTiposReunion: List<TipoReunionModel>
    private lateinit var adapter: ArrayAdapter<TipoReunionModel>
    //endregion

    //region metodos publicos
    fun conSpinner(spinner: Spinner): HelperSpinnerTiposReunion{
        this.spinner = spinner
        return this
    }

    fun conListaTiposReunion(lista: List<TipoReunionModel>) : HelperSpinnerTiposReunion {
        this.listaTiposReunion = lista
        return this
    }

    fun cargarSpinner() {
        adapter = ArrayAdapter(spinner.context!!, android.R.layout.simple_list_item_1, listaTiposReunion)
        spinner.adapter = adapter
    }

    fun traerSeleccionado(): TipoReunionModel = (spinner.selectedItem as TipoReunionModel)

    //endregion
}