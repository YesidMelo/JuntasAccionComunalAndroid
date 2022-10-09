package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db

import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.HelperListaAfiliadosModificacionDirectivaDB
import com.hefesto.juntasaccioncomunal.logica.modelos.home.AfiliadoModificacionDirectivaModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface HomeDBDatasource {
    fun traerListaAfiliadosModificacionRolDirectiva(jacId : Int) : Flow<List<AfiliadoModificacionDirectivaModel>>
}

class HomeDBDatasourceImpl constructor(
    @JvmField @Inject var helperListaAfiliadosModificacionDirectivaDB : HelperListaAfiliadosModificacionDirectivaDB
) : HomeDBDatasource {

    override fun traerListaAfiliadosModificacionRolDirectiva(jacId : Int): Flow<List<AfiliadoModificacionDirectivaModel>>
    = helperListaAfiliadosModificacionDirectivaDB.traerListaAfiliadosModificacionRolDirectiva(jacId = jacId)

}