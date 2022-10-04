package com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.FuncionesRolAppDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.RolesAppDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.TipoDocumentoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.TipoTelefonoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.EstadoAfiliacionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.RolAfiliacionDao
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos.HelperCargarTiposDocumento
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos.HelperCargarTiposTelefono
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos.HelperCargarEstadoAfiliacion
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos.HelperCargarFuncionesApp
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos.HelperCargarRolAfiliacion
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos.HelperCargarRolesApp

class HelperCargarTipos constructor(
    var estadoAfiliacionDao: EstadoAfiliacionDao,
    var funcionesRolAppDao: FuncionesRolAppDao,
    var rolAfiliacionDao: RolAfiliacionDao,
    var rolesAppDao: RolesAppDao,
    var tipoDocumentoDao: TipoDocumentoDao,
    var tipoTelefonoDao: TipoTelefonoDao
) {

    suspend fun cargarEstadosAfiliacion() = HelperCargarEstadoAfiliacion(estadoAfiliacionDao = estadoAfiliacionDao).cargar()
    suspend fun cargarFuncionesApp() = HelperCargarFuncionesApp(funcionesRolAppDao = funcionesRolAppDao).cargar()
    suspend fun cargarRolAfiliacion() = HelperCargarRolAfiliacion(rolAfiliacionDao = rolAfiliacionDao).cargar()
    suspend fun cargarRolesApp() = HelperCargarRolesApp(rolesAppDao = rolesAppDao).cargar()
    suspend fun cargarTiposDocumento() = HelperCargarTiposDocumento(tipoDocumentoDao = tipoDocumentoDao).cargar()
    suspend fun cargarTiposTelefono() = HelperCargarTiposTelefono(tipoTelefonoDao = tipoTelefonoDao).cargar()
}