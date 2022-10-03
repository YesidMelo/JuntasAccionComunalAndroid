package com.hefesto.juntasaccioncomunal.ui.fragments.login.registrarAfiliado

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.login.ui.RegistrarAfiliadoFragmentUI
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.AfiliadoARegistrarModel
import com.hefesto.juntasaccioncomunal.ui.base.BaseViewModel
import javax.inject.Inject

class RegistrarAfiliadoFragmentViewModel constructor(
    @JvmField @Inject var registrarAfiliadoUI : RegistrarAfiliadoFragmentUI
) : BaseViewModel() {

    //region variables
    val cargaCompleta = MutableLiveData<Boolean>()
    private val mapaDeCarga = emptyMap<ElementosCarga, Boolean>().toMutableMap()
    //endregion

    override fun traerBaseUI(): BaseUI = registrarAfiliadoUI

    fun inicializarMapaDeCarga() {
        mapaDeCarga.clear()
        ElementosCarga.values().forEach { mapaDeCarga[it] = false }
        cargaCompleta.value = false
    }

    fun registrarAfiliado(afiliadoARegistrarModel: AfiliadoARegistrarModel) = registrarAfiliadoUI.registrarAfiliado(afiliadoARegistrarModel = afiliadoARegistrarModel)

    fun traerListaJacsRegistradas() = registrarAfiliadoUI.traerListaJacsRegistradas()

    fun traerTiposDocumento() = registrarAfiliadoUI.traerTiposDocumento()

    fun cargo(elementoCarga : ElementosCarga) {
        mapaDeCarga[elementoCarga] = true
        var cargoTodo = true
        mapaDeCarga.forEach {
            cargoTodo = cargoTodo && it.value
        }
        cargaCompleta.postValue(cargoTodo)
    }

    enum class ElementosCarga {
        JACS_DISPONIBLES,
        TIPOS_DOCUMENTO
    }
}