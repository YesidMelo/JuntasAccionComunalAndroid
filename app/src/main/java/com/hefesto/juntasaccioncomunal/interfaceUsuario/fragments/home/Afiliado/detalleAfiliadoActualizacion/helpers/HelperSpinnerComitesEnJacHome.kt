package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.detalleAfiliadoActualizacion.helpers

import android.widget.ArrayAdapter
import android.widget.Spinner
import com.hefesto.juntasaccioncomunal.logica.modelos.general.ComiteEnJacModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.ComitesEnJAC

class HelperSpinnerComitesEnJacHome {

    //region variables
    private lateinit var spinner: Spinner
    private lateinit var listaComites: List<ComiteEnJacModel>
    private lateinit var adapter: ArrayAdapter<ComiteEnJacModel>
    private var comiteSeleccionado : ComitesEnJAC? = null
    //endregion

    fun conSpinner(spinner: Spinner) : HelperSpinnerComitesEnJacHome {
        this.spinner = spinner
        return this
    }

    fun conListaComites(listaComites: List<ComiteEnJacModel>) : HelperSpinnerComitesEnJacHome {
        this.listaComites = listaComites
        return this
    }

    fun conComiteSeleccionado(comiteSeleccionado : ComitesEnJAC?) : HelperSpinnerComitesEnJacHome {
        this.comiteSeleccionado = comiteSeleccionado
        asignarItemSeleccionadoASpinner()
        return this
    }

    fun cargarSpinner() {
        adapter = ArrayAdapter(spinner.context!!, android.R.layout.simple_list_item_1, listaComites)
        spinner.adapter = adapter
    }

    fun traerComiteSeleccionado() = (spinner.selectedItem as ComiteEnJacModel).comitesEnJAC

    //region metodos privados
    private fun asignarItemSeleccionadoASpinner() {
        val comite = comiteSeleccionado?:return
        val lista = listaComites.filter { return@filter it.comitesEnJAC == comite }
        if (lista.isEmpty()) return
        val model = lista.first()
        spinner.setSelection(listaComites.indexOf(model))
    }
    //endregion
}