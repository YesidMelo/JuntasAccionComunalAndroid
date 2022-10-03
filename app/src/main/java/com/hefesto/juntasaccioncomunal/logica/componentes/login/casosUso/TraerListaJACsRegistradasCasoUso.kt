package com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.LoginRepositorio
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.JACDisponibleParaAfiliadoModel
import javax.inject.Inject

interface TraerListaJACsRegistradasCasoUso {
    fun invoke() : MutableLiveData<List<JACDisponibleParaAfiliadoModel>?>
}

class TraerListaJACsRegistradasCasoUsoImpl constructor(
    @JvmField @Inject var loginRepositorio: LoginRepositorio
): TraerListaJACsRegistradasCasoUso {

    override fun invoke(): MutableLiveData<List<JACDisponibleParaAfiliadoModel>?> = loginRepositorio.traerJacsRegistradas()

}
