package com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.LoginRepositorio
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.AfiliadoARegistrarModel
import javax.inject.Inject

interface RegistrarAfiliadoCasoUso {
    fun invoke(afiliadoARegistrarModel: AfiliadoARegistrarModel) : MutableLiveData<Boolean?>
}

class RegistrarAfiliadoCasoUsoImpl constructor(
    @JvmField @Inject var loginRepositorio: LoginRepositorio
) : RegistrarAfiliadoCasoUso {

    override fun invoke(afiliadoARegistrarModel: AfiliadoARegistrarModel): MutableLiveData<Boolean?> {
        return loginRepositorio.registrarAfiliado(afiliadoARegistrarModel = afiliadoARegistrarModel)
    }

}