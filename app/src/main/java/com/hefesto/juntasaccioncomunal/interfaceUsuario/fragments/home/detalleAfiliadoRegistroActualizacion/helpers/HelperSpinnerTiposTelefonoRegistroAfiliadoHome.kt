package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.helpers

import android.widget.ArrayAdapter
import android.widget.Spinner
import com.hefesto.juntasaccioncomunal.logica.modelos.general.TipoTelefonoModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoTelefono

class HelperSpinnerTiposTelefonoRegistroAfiliadoHome {

    //region variables
    private lateinit var spinner: Spinner
    private lateinit var listaTiposTelefono : List<TipoTelefonoModel>
    private lateinit var adapter: ArrayAdapter<TipoTelefonoModel>
    private var tipoTelefonoSeleccionado : TipoTelefono? = null
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

    fun conTipoTelefonoSeleccionado(tipoTelefonoSeleccionado : TipoTelefono?) : HelperSpinnerTiposTelefonoRegistroAfiliadoHome {
        this.tipoTelefonoSeleccionado = tipoTelefonoSeleccionado
        seleccionarItem()
        return this
    }

    fun traerTipoTelefono() = (spinner.selectedItem as TipoTelefonoModel).tipoTelefono

    fun cargarSpinner() {
        adapter = ArrayAdapter(spinner.context!!, android.R.layout.simple_list_item_1, listaTiposTelefono)
        spinner.adapter = adapter
    }
    //endregion

    //region metodos privados
    private fun seleccionarItem() {
        val tipoSeleccionado = this.tipoTelefonoSeleccionado?:return
        val itemSeleccionado = listaTiposTelefono.filter { return@filter it.tipoTelefono == tipoSeleccionado }
        if (itemSeleccionado.isEmpty()) return
        val modeloSeleccionado = itemSeleccionado.first()
        spinner.setSelection(listaTiposTelefono.indexOf(modeloSeleccionado))
    }
    //endregion
}