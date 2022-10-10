package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.configuracionAfiliadoEnDirectiva

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.ConfiguracionAfiliadoEnDirectivaUI
import javax.inject.Inject

class ConfiguracionAfiliadoEnDirectivaViewModel constructor(
    @JvmField @Inject var configuracionAfiliadoEnDirectivaUI: ConfiguracionAfiliadoEnDirectivaUI
) : BaseViewModel() {

    //region variables
    private val mapCarga = emptyMap<ElementoDeCarga, Boolean>().toMutableMap()
    private val cargoLiveData = MutableLiveData<Boolean>()
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

    enum class ElementoDeCarga {
        ESTADOS_AFILIADO,
        ROLES_APP
    }
}