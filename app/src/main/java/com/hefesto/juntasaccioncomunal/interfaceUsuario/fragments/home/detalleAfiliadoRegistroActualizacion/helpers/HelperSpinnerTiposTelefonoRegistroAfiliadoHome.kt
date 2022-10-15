package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.helpers

import android.widget.ArrayAdapter
import android.widget.Spinner
import com.hefesto.juntasaccioncomunal.logica.modelos.general.TipoTelefonoModel

class HelperSpinnerTiposTelefonoRegistroAfiliadoHome {

    //region variables
    private lateinit var spinner: Spinner
    private lateinit var listaTiposTelefono : List<TipoTelefonoModel>
    private lateinit var adapter: ArrayAdapter<TipoTelefonoModel>
    //endregion

    //region metodos publicos
    fun conSpinner(spinner: Spinner) : HelperSpinnerTiposTelefonoRegistroAfiliadoHome {
        this.spinner = spinner
        return this
    }

    fun conListaTiposTelefono(listaTiposTelefono: List<TipoTelefonoModel>) : HelperSpinnerTiposTelefonoRegistroAfiliadoHome {
        this.listaTiposTelefono = listaTiposTelefono
        return this
    }

    fun traerTipoTelefono() = (spinner.selectedItem as TipoTelefonoModel).tipoTelefono

    fun cargarSpinner() {
        adapter = ArrayAdapter(spinner.context!!, android.R.layout.simple_list_item_1, listaTiposTelefono)
        spinner.adapter = adapter
    }
    //endregion
}