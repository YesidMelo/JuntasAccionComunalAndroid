package com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.configuracionAfiliadoEnDirectiva

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.logica.modelos.general.RolAppModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.RolesEnApp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

interface TraerRolesAfiliadosEnDirectivaCasoUso {
    fun invoke() : MutableLiveData<List<RolAppModel>?>
}

class TraerRolesAfiliadosEnDirectivaCasoUsoImpl constructor(
    @JvmField @Inject var context: Context
) : TraerRolesAfiliadosEnDirectivaCasoUso {

    //region variables
    private val listaRolesLiveData = MutableLiveData<List<RolAppModel>?>()
    //endregion

    override fun invoke(): MutableLiveData<List<RolAppModel>?> {
        GlobalScope.launch {
            listaRolesLiveData.postValue(null)
            val lista = emptyList<RolAppModel>().toMutableList()

            for (rol in RolesEnApp.values()) {
                if (rol == RolesEnApp.ADMINISTRADOR) continue
                lista.add(RolAppModel(rolesEnApp = rol, nombre = context.getString(rol.traerStringRes())))
            }
            listaRolesLiveData.postValue(lista)
        }
        return listaRolesLiveData
    }

}