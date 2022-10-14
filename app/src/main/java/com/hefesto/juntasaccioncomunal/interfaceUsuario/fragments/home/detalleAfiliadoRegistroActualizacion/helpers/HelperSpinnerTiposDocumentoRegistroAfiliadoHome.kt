package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.helpers

import android.widget.ArrayAdapter
import android.widget.Spinner
import com.hefesto.juntasaccioncomunal.logica.modelos.general.TipoDocumentoModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.DatosBasicosAfiliadoActualizarRegistrarInformacionModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoDocumento

class HelperSpinnerTiposDocumentoRegistroAfiliadoHome {

    //region variables
    private lateinit var spinner: Spinner
    private lateinit var listaTiposDocumentoModel : List<TipoDocumentoModel>
    private var datosBasicosAfiliadoActualizarRegistrarInformacionModel: DatosBasicosAfiliadoActualizarRegistrarInformacionModel? = null
    private lateinit var adapter : ArrayAdapter<TipoDocumentoModel>
    //endregion

    //region metodos publicos
    fun conSpinner(spinner: Spinner) : HelperSpinnerTiposDocumentoRegistroAfiliadoHome {
        this.spinner = spinner
        return this
    }

    fun conListaTiposDocumento(listaTiposDocumento: List<TipoDocumentoModel>) : HelperSpinnerTiposDocumentoRegistroAfiliadoHome {
        this.listaTiposDocumentoModel = listaTiposDocumento
        return this
    }

    fun conDatosBasicosAfiliadoActualizarRegistroInformacionModel(datosBasicosAfiliadoActualizarRegistrarInformacionModel: DatosBasicosAfiliadoActualizarRegistrarInformacionModel?) : HelperSpinnerTiposDocumentoRegistroAfiliadoHome {
        this.datosBasicosAfiliadoActualizarRegistrarInformacionModel = datosBasicosAfiliadoActualizarRegistrarInformacionModel
        return this
    }

    fun cargarSpinner() {
        adapter = ArrayAdapter(spinner.context!!, android.R.layout.simple_list_item_1, listaTiposDocumentoModel)
        spinner.adapter = adapter
        seleccionarTipoDocumentoSeleccionadoPorAfiliado()
    }

    fun traerTipoDocumentoSeleccionado() : TipoDocumento = (spinner.selectedItem as TipoDocumentoModel).tipoDocumento

    //endregion

    //region metodos privados
    private fun seleccionarTipoDocumentoSeleccionadoPorAfiliado() {
        val datos = datosBasicosAfiliadoActualizarRegistrarInformacionModel?:return
        val seleccionado = listaTiposDocumentoModel.filter { return@filter it.tipoDocumento == datos.tipoDocumento }.toList()
        if (seleccionado.isEmpty()) return
        val documentoSeleccionado = seleccionado.first()
        spinner.setSelection(listaTiposDocumentoModel.indexOf(documentoSeleccionado))
    }
    //endregion

}