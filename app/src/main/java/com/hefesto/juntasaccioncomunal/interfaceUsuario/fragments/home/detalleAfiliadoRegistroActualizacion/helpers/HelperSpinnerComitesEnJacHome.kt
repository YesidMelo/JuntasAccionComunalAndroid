package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.helpers

import android.widget.ArrayAdapter
import android.widget.Spinner
import com.hefesto.juntasaccioncomunal.logica.modelos.general.ComiteEnJacModel

class HelperSpinnerComitesEnJacHome {

    //region variables
    private lateinit var spinner: Spinner
    private lateinit var listaComites: List<ComiteEnJacModel>
    private lateinit var adapter: ArrayAdapter<ComiteEnJacModel>
    //endregion

    fun conSpinner(spinner: Spinner) : HelperSpinnerComitesEnJacHome {
        this.spinner = spinner
        return this
    }

    fun conListaComites(listaComites: List<ComiteEnJacModel>) : HelperSpinnerComitesEnJacHome {
        this.listaComites = listaComites
        return this
    }

    fun cargarSpinner() {
        adapter = ArrayAdapter(spinner.context!!, android.R.layout.simple_list_item_1, listaComites)
        spinner.adapter = adapter
    }
}