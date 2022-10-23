package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.asambleaReunion.CrearActaUI
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.ReunionAsambleaCreacionActaModel
import javax.inject.Inject

class CrearActaViewModel constructor(
    @JvmField @Inject var crearActaUI: CrearActaUI
) : BaseViewModel() {

    //region variables
    private val haCargadoLiveData = MutableLiveData<Boolean>()
    private val detalleReunionLiveData = MutableLiveData<ReunionAsambleaCreacionActaModel?>()
    //endregion

    override fun traerBaseUI(): BaseUI = crearActaUI

    //region metodos publicos

    fun conReunionAModificar(detalle : ReunionAsambleaCreacionActaModel?) : CrearActaViewModel{
        detalleReunionLiveData.postValue(detalle)
        return this
    }

    fun cargo(cargo : Boolean) = haCargadoLiveData.postValue(cargo)

    fun traerDetalleReunionLiveData() = detalleReunionLiveData

    fun traerHaCargadoLiveData() = haCargadoLiveData

    //endregion
}