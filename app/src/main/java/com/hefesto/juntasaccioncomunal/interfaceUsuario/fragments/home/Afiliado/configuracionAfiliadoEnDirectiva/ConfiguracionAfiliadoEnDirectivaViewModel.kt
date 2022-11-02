package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.configuracionAfiliadoEnDirectiva

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso.CargarEscuchadorExcepcionesCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.ConfiguracionAfiliadoEnDirectivaUI
import com.hefesto.juntasaccioncomunal.logica.modelos.home.AfiliadoEnDirectivaModificadoModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.ManejarErrores
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class ConfiguracionAfiliadoEnDirectivaViewModel constructor(
    @JvmField @Inject var configuracionAfiliadoEnDirectivaUI: ConfiguracionAfiliadoEnDirectivaUI,
    @JvmField @Inject var cargarEscuchadorExcepcionesCasoUso: CargarEscuchadorExcepcionesCasoUso
) : BaseViewModel() {

    //region variables
    private val mapCarga = emptyMap<ElementoDeCarga, Boolean>().toMutableMap()
    private val cargoLiveData = MutableLiveData<Boolean>()
    private val actualizacionAfiliadoLiveData = MutableLiveData<Boolean?>()
    //endregion

    init {
        reiniciarMapaCarga()
    }

    override fun traerBaseUI(): BaseUI = configuracionAfiliadoEnDirectivaUI

    fun cargo() = cargoLiveData

    fun reiniciarMapaCarga() {
        mapCarga.clear()
        for (elemento in ElementoDeCarga.values()) {
            mapCarga.put(elemento, false)
        }
    }

    fun cargoElemento(elementoCarga: ElementoDeCarga) {
        mapCarga[elementoCarga] = true
        var finalizoCarga = true
        for (elementoCarga in mapCarga.values) {
            finalizoCarga = elementoCarga && finalizoCarga
        }
        cargoLiveData.postValue(finalizoCarga)
    }

    fun traerListaEstadosAfiliado() = configuracionAfiliadoEnDirectivaUI.traerListaEstadosAfiliadoEnDirectiva()

    fun traerListaRolesApp() = configuracionAfiliadoEnDirectivaUI.traerListaRolesAfiliadosEnDirectiva()

    fun actualizarAfiliadoEnDirectiva(afiliadoEnDirectivaModificadoModel: AfiliadoEnDirectivaModificadoModel) : MutableLiveData<Boolean?> {
        GlobalScope.launch {
            configuracionAfiliadoEnDirectivaUI
                .actualizarAfiliadoEnDirectiva(afiliadoEnDirectivaModificadoModel = afiliadoEnDirectivaModificadoModel)
                .ManejarErrores(escuchadorErrores = cargarEscuchadorExcepcionesCasoUso.invoke())
                .collect { actualizacionAfiliadoLiveData.postValue(it) }
        }
        return actualizacionAfiliadoLiveData
    }

    enum class ElementoDeCarga {
        ESTADOS_AFILIADO,
        ROLES_APP
    }
}