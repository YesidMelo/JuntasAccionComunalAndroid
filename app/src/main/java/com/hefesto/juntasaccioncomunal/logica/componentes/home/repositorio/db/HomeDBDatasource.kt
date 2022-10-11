package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db

import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.HelperActualizarAfiliadoEnDirectivaDB
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.HelperListaAfiliadosModificacionDirectivaDB
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.HelperListaAfiliadosRegistroActualizacionDB
import com.hefesto.juntasaccioncomunal.logica.modelos.home.AfiliadoEnDirectivaModificadoModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.AfiliadoParaModificacionDirectivaModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.DatosBasicosAfiliadoActualizarRegistrarInformacionModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface HomeDBDatasource {
    fun actualizarAfiliadoModificacionRolDirectiva(afiliadoEnDirectivaModificadoModel: AfiliadoEnDirectivaModificadoModel) : Flow<Boolean?>
    fun traerListaAfiliadosActualizacionRegistro(): Flow<List<DatosBasicosAfiliadoActualizarRegistrarInformacionModel>?>
    fun traerListaAfiliadosModificacionRolDirectiva(jacId : Int) : Flow<List<AfiliadoParaModificacionDirectivaModel>>
}

class HomeDBDatasourceImpl constructor(
    @JvmField @Inject var helperActualizarAfiliadoEnDirectivaDB : HelperActualizarAfiliadoEnDirectivaDB,
    @JvmField @Inject var helperListaAfiliadosModificacionDirectivaDB : HelperListaAfiliadosModificacionDirectivaDB,
    @JvmField @Inject var helperListaAfiliadosRegistroActualizacionDB: HelperListaAfiliadosRegistroActualizacionDB
) : HomeDBDatasource {

    override fun traerListaAfiliadosModificacionRolDirectiva(jacId : Int): Flow<List<AfiliadoParaModificacionDirectivaModel>>
    = helperListaAfiliadosModificacionDirectivaDB.traerListaAfiliadosModificacionRolDirectiva(jacId = jacId)

    override fun actualizarAfiliadoModificacionRolDirectiva(afiliadoEnDirectivaModificadoModel: AfiliadoEnDirectivaModificadoModel) : Flow<Boolean?>
    = helperActualizarAfiliadoEnDirectivaDB.actualizarAfiliadoEnDirectiva(afiliadoEnDirectivaModificadoModel = afiliadoEnDirectivaModificadoModel)

    override fun traerListaAfiliadosActualizacionRegistro(): Flow<List<DatosBasicosAfiliadoActualizarRegistrarInformacionModel>?>
    = helperListaAfiliadosRegistroActualizacionDB.traerListaAfiliados()

}