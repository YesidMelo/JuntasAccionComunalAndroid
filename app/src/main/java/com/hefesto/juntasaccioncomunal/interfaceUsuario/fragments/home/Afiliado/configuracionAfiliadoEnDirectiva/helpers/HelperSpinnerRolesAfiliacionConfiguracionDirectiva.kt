package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.configuracionAfiliadoEnDirectiva.helpers

import android.widget.ArrayAdapter
import android.widget.Spinner
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.configuracionAfiliadoEnDirectiva.ConfiguracionAfiliadoEnDirectivaViewModel
import com.hefesto.juntasaccioncomunal.logica.modelos.general.RolAppModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.AfiliadoParaModificacionDirectivaModel

class HelperSpinnerRolesAfiliacionConfiguracionDirectiva {

    //region variables
    private lateinit var spinner: Spinner
    private lateinit var adapter: ArrayAdapter<RolAppModel>
    private lateinit var afiliado: AfiliadoParaModificacionDirectivaModel
    private lateinit var listaRolAppModel: List<RolAppModel>
    private lateinit var viewModel : ConfiguracionAfiliadoEnDirectivaViewModel
    //endregion

    //region metodos publicos
    fun conSpinner(spinner: Spinner) : HelperSpinnerRolesAfiliacionConfiguracionDirectiva {
        this.spinner = spinner
        return this
    }

    fun conAfiliado(afiliado: AfiliadoParaModificacionDirectivaModel) : HelperSpinnerRolesAfiliacionConfiguracionDirectiva {
        this.afiliado = afiliado
        return this
    }

    fun conListaRolAppModel(lista: List<RolAppModel>) : HelperSpinnerRolesAfiliacionConfiguracionDirectiva {
        this.listaRolAppModel = lista
        return this
    }

    fun conViewModel(viewModel: ConfiguracionAfiliadoEnDirectivaViewModel) : HelperSpinnerRolesAfiliacionConfiguracionDirectiva {
        this.viewModel = viewModel
        return this
    }

    fun traerItemSeleccionado() = spinner.selectedItem as RolAppModel

    fun cargarSpinner() {
        adapter = ArrayAdapter(spinner.context, android.R.layout.simple_list_item_1, listaRolAppModel)
        spinner.adapter = adapter
        viewModel.cargoElemento( elementoCarga =  ConfiguracionAfiliadoEnDirectivaViewModel.ElementoDeCarga.ROLES_APP)
        cargarRolActualAfiliado()
    }
    //endregion

    //region metodos privados
    private fun cargarRolActualAfiliado() {
        val listaRoles = listaRolAppModel.filter { return@filter it.rolesEnApp == afiliado.rolApp }
        if (listaRoles.isNullOrEmpty()) return
        val rol = listaRoles.first()
        spinner.setSelection(listaRolAppModel.indexOf(rol))
    }
    //endregion
}