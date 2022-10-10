package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.AfiliadoDao
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.convertirAListaAfiliadoModificacionDirectivaModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.AfiliadoParaModificacionDirectivaModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HelperListaAfiliadosModificacionDirectivaDB constructor(
    @JvmField @Inject var afiladoDao : AfiliadoDao
)
{
    fun traerListaAfiliadosModificacionRolDirectiva(jacId : Int): Flow<List<AfiliadoParaModificacionDirectivaModel>> = flow {
        val lista = afiladoDao.traerListaUsuariosModificacionDirectivaEntity(jacID = jacId)
        emit(lista.convertirAListaAfiliadoModificacionDirectivaModel())
    }
}