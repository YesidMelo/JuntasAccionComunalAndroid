package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.configuracionAfiliadoEnDirectiva.helpers

import android.widget.ArrayAdapter
import android.widget.Spinner
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.configuracionAfiliadoEnDirectiva.ConfiguracionAfiliadoEnDirectivaViewModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.AfiliadoParaModificacionDirectivaModel
import com.hefesto.juntasaccioncomunal.logica.modelos.general.EstadoAfiliadoModel

class HelperSpinnerEstadosAfiliacionConfiguracionDirectivas {

    //region variables
    private lateinit var spinner: Spinner
    private lateinit var adapter: ArrayAdapter<EstadoAfiliadoModel>
    private lateinit var afiliado: AfiliadoParaModificacionDirectivaModel
    private lateinit var listaEstadosAfiliado: List<EstadoAfiliadoModel>
    private lateinit var viewModel : ConfiguracionAfiliadoEnDirectivaViewModel
    //endregion

    //region metodos publicos

    fun conViewModel(viewModel: ConfiguracionAfiliadoEnDirectivaViewModel) : HelperSpinnerEstadosAfiliacionConfiguracionDirectivas {
        this.viewModel = viewModel
        return this
    }

    fun conListaEstadosAfiliadoEnDirectiva(listaEstadosAfiliado: List<EstadoAfiliadoModel>) : HelperSpinnerEstadosAfiliacionConfiguracionDirectivas {
        this.listaEstadosAfiliado = listaEstadosAfiliado
        return this
    }

    fun conSpinner(spinner: Spinner) : HelperSpinnerEstadosAfiliacionConfiguracionDirectivas {
        this.spinner = spinner
        return this
    }

    fun conAfiliadoModificacionDirectivaModel(afiliado: AfiliadoParaModificacionDirectivaModel) : HelperSpinnerEstadosAfiliacionConfiguracionDirectivas{
        this.afiliado = afiliado
        return this
    }

    fun configurarSpinner() {
        adapter = ArrayAdapter(spinner.context, android.R.layout.simple_list_item_1, listaEstadosAfiliado)
        spinner.adapter = adapter
        ingresarEstadoActualAfiliadoEnSpinner()
        viewModel.cargoElemento(ConfiguracionAfiliadoEnDirectivaViewModel.ElementoDeCarga.ESTADOS_AFILIADO)
    }

    fun traerEstadoAfiliacionSeleccionado() : EstadoAfiliadoModel = spinner.selectedItem as EstadoAfiliadoModel

    //endregion

    //region metodos privados
    private fun ingresarEstadoActualAfiliadoEnSpinner() {
        val listaEstadosSeleccionados = listaEstadosAfiliado.filter { return@filter it.estadoAfiliacion == afiliado.estadoAfiliacion }
        if (listaEstadosSeleccionados.isNullOrEmpty()) return
        val estadoSeleccionado = listaEstadosSeleccionados.first()
        spinner.setSelection(listaEstadosAfiliado.indexOf(estadoSeleccionado))
    }
    //endregion
}