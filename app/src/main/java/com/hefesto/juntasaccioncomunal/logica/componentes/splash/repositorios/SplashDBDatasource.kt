package com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.FuncionesRolAppDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.RolesAppDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.TipoDocumentoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.TipoTelefonoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.EstadoAfiliacionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.RolAfiliacionDao
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.HelperCargarTipos
import javax.inject.Inject

interface SplashDBDatasource {
    suspend fun cargarTipos()
}

class SplashDBDatasourceImpl constructor(
    @JvmField @Inject var estadoAfiliacionDao: EstadoAfiliacionDao,
    @JvmField @Inject var funcionesRolAppDao: FuncionesRolAppDao,
    @JvmField @Inject var rolAfiliacionDao: RolAfiliacionDao,
    @JvmField @Inject var rolesAppDao: RolesAppDao,
    @JvmField @Inject var tipoDocumentoDao: TipoDocumentoDao,
    @JvmField @Inject var tipoTelefonoDao: TipoTelefonoDao
) : SplashDBDatasource {

    //region variables
    private val helperCargarTipos = HelperCargarTipos(
        estadoAfiliacionDao = estadoAfiliacionDao,
        funcionesRolAppDao = funcionesRolAppDao,
        rolAfiliacionDao = rolAfiliacionDao,
        rolesAppDao = rolesAppDao,
        tipoDocumentoDao = tipoDocumentoDao,
        tipoTelefonoDao = tipoTelefonoDao
    )
    //endregion

    override suspend fun cargarTipos() {
        helperCargarTipos.cargarEstadosAfiliacion()
        helperCargarTipos.cargarFuncionesApp()
        helperCargarTipos.cargarRolAfiliacion()
        helperCargarTipos.cargarRolesApp()
        helperCargarTipos.cargarTiposDocumento()
        helperCargarTipos.cargarTiposTelefono()
    }
}