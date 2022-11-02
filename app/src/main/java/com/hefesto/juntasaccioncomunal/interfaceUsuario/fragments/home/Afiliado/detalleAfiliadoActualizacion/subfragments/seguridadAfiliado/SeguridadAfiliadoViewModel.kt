package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.detalleAfiliadoActualizacion.subfragments.seguridadAfiliado

import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.registroAfiliado.SeguridadAfiliadoUI
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.SeguridadParaRegistroModel
import javax.inject.Inject

class SeguridadAfiliadoViewModel constructor(
    @JvmField @Inject var seguridadAfiliadoUI: SeguridadAfiliadoUI
) : BaseViewModel() {

    //region variables
    private var contrasenia: String? = null
    private var repetirContrasenia: String? = null
    private var credencialesSesionId: Int? = null
    //endregion

    override fun traerBaseUI(): BaseUI = seguridadAfiliadoUI

    fun conCredencialesSesionId(credencialesSesionId: Int?) : SeguridadAfiliadoViewModel {
        this.credencialesSesionId = credencialesSesionId
        return this
    }

    fun conContrasenia(contrasenia: String) : SeguridadAfiliadoViewModel {
        this.contrasenia = contrasenia
        return this
    }

    fun conRepetirContrasenia(repetirContrasenia: String) : SeguridadAfiliadoViewModel {
        this.repetirContrasenia = repetirContrasenia
        return this
    }

    fun traerSeguridadParaRegistroModel() : SeguridadParaRegistroModel {
        return SeguridadParaRegistroModel(
            credencialesSesionId = this.credencialesSesionId,
            contrasenia = this.contrasenia,
            repetirContrasenia = this.repetirContrasenia
        )
    }

}