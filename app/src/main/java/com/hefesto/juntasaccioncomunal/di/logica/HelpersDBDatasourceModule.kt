package com.hefesto.juntasaccioncomunal.di.logica

import com.hefesto.juntasaccioncomunal.fuentesDatos.cache.MemoriaCache
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.FuncionesRolAppDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.RolApp_FuncionesApp_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.RolesAppDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.TipoDocumentoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.TipoTelefonoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.AfiliadoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Correo_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Direccion_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Jac_EstadoAfiliacionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Telefono_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.CorreoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.CredencialesSesionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.DireccionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.EstadoAfiliacionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.JacDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Jac_Afiliado_Direccion_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.RolAfiliacionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Telefono_Dao
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.HelperActualizarAfiliadoEnDirectivaDB
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.HelperActualizarAfiliadoEnDirectivaDBImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.HelperListaAfiliadosModificacionDirectivaDB
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.HelperIniciarSesion
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.HelperRegistroAfilado
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.HelperRegistroJAC
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.HelperTraerDetalleUsuarioSesion
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.HelperTraerListaJACSRegistrados
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.registroAfiliado.HelperRegistroAfiliadoEntity
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.registroAfiliado.HelperRegistroCorreoAfiliadoEntity
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.registroAfiliado.HelperRegistroCredencialesSesionAfiliado
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.registroAfiliado.HelperRegistroDireccionAfiliadoEntity
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.registroAfiliado.HelperRegistroTelefono
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.registroAfiliado.HelperValidarExisteUsuarioEnJAC
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.HelperCargarTipos
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos.HelperCargarEstadoAfiliacion
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos.HelperCargarFuncionesApp
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos.HelperCargarRelacionRolFunciones
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos.HelperCargarRolAfiliacion
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos.HelperCargarRolesApp
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos.HelperCargarTiposDocumento
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos.HelperCargarTiposTelefono
import dagger.Module
import dagger.Provides

@Module
class HelpersDBDatasourceModule {

    //region home

    //region primer nivel
    @Provides
    fun providesHelperListaAfiliadosModificacionDirectivaDB(
        afiliadoDao: AfiliadoDao
    ) : HelperListaAfiliadosModificacionDirectivaDB = HelperListaAfiliadosModificacionDirectivaDB(
        afiladoDao = afiliadoDao
    )

    @Provides
    fun providesHelperActualizarAfiliadoEnDirectivaDB(
        memoriaCache: MemoriaCache,
        afiliadoJacEstadoafiliaciondao: Afiliado_Jac_EstadoAfiliacionDao
    ) : HelperActualizarAfiliadoEnDirectivaDB = HelperActualizarAfiliadoEnDirectivaDBImpl(
        memoriaCache = memoriaCache,
        afiliadoJacEstadoafiliaciondao = afiliadoJacEstadoafiliaciondao
    )
    //endregion

    //region segundo nivel
    //endregion

    //endregion


    //region login

    //region primer nivel

    @Provides
    fun providesHelperRegistroAfiliadoEntity(
        afiliadoDao: AfiliadoDao,
        afiliadoJacEstadoafiliaciondao: Afiliado_Jac_EstadoAfiliacionDao
    ) : HelperRegistroAfiliadoEntity = HelperRegistroAfiliadoEntity(
        afiliadoDao = afiliadoDao,
        afiliado_Jac_EstadoAfiliacionDao = afiliadoJacEstadoafiliaciondao
    )

    @Provides
    fun providesHelperRegistroCorreoAfiliadoEntity(
        afiliadoCorreoDao: Afiliado_Correo_Dao,
        afiliadoDao: AfiliadoDao,
        correoDao: CorreoDao
    ) : HelperRegistroCorreoAfiliadoEntity = HelperRegistroCorreoAfiliadoEntity(
        afiliadoCorreoDao = afiliadoCorreoDao,
        afiliadoDao = afiliadoDao,
        correoDao = correoDao
    )

    @Provides
    fun providesHelperRegistroCredencialesSesionAfiliado(
        afiliadoDao: AfiliadoDao,
        correoDao: CorreoDao,
        credencialesSesionDao: CredencialesSesionDao
    ) : HelperRegistroCredencialesSesionAfiliado = HelperRegistroCredencialesSesionAfiliado(
        afiliadoDao = afiliadoDao,
        correoDao = correoDao,
        credencialesSesionDao = credencialesSesionDao
    )

    @Provides
    fun providesHelperRegistroDireccionAfiliadoEntity(
        afiliadoDao: AfiliadoDao,
        afiliadoDireccionDao: Afiliado_Direccion_Dao,
        direccionDao: DireccionDao,
        jacAfiliadoDireccionDao: Jac_Afiliado_Direccion_Dao
    ) : HelperRegistroDireccionAfiliadoEntity = HelperRegistroDireccionAfiliadoEntity(
        afiliadoDao = afiliadoDao,
        afiliadoDireccionDao = afiliadoDireccionDao,
        direccionDao = direccionDao,
        jacAfiliadoDireccionDao = jacAfiliadoDireccionDao
    )

    @Provides
    fun providesHelperRegistroTelefono(
        afiliadoDao: AfiliadoDao,
        afiliadoTelefonoDao: Afiliado_Telefono_Dao,
        telefonoDao: Telefono_Dao
    ) : HelperRegistroTelefono = HelperRegistroTelefono(
        afiliadoDao = afiliadoDao,
        afiliadoTelefonoDao = afiliadoTelefonoDao,
        telefonoDao = telefonoDao
    )

    @Provides
    fun providesHelperTraerDetalleUsuarioSesion(
        afiliadoDao: AfiliadoDao,
        jacDao: JacDao,
        rolApp_FuncionesApp_Dao: RolApp_FuncionesApp_Dao
    ) : HelperTraerDetalleUsuarioSesion = HelperTraerDetalleUsuarioSesion(
        afiliadoDao = afiliadoDao,
        jacDao = jacDao,
        rolApp_FuncionesApp_Dao= rolApp_FuncionesApp_Dao
    )

    @Provides
    fun providesHelperValidarExisteUsuarioEnJAC(
        afiliadoDao: AfiliadoDao,
        afiliadoJacEstadoafiliaciondao: Afiliado_Jac_EstadoAfiliacionDao
    ) : HelperValidarExisteUsuarioEnJAC = HelperValidarExisteUsuarioEnJAC(
        afiliadoDao = afiliadoDao,
        afiliadoJacEstadoafiliaciondao = afiliadoJacEstadoafiliaciondao
    )

    //endregion

    //region segundo nivel

    @Provides
    fun providesHelperRegistroAfiliado(
        helperRegistroAfiliadoEntity : HelperRegistroAfiliadoEntity,
        helperRegistroCorreoAfiliadoEntity : HelperRegistroCorreoAfiliadoEntity,
        helperRegistroDireccionAfiliadoEntity: HelperRegistroDireccionAfiliadoEntity,
        helperRegistroTelefono: HelperRegistroTelefono,
        helperValidarExisteUsuarioEnJAC: HelperValidarExisteUsuarioEnJAC,
        helperRegistroCredencialesSesionAfiliado: HelperRegistroCredencialesSesionAfiliado
    ) : HelperRegistroAfilado = HelperRegistroAfilado(
        helperRegistroAfiliadoEntity = helperRegistroAfiliadoEntity,
        helperRegistroCorreoAfiliadoEntity = helperRegistroCorreoAfiliadoEntity,
        helperRegistroDireccionAfiliadoEntity = helperRegistroDireccionAfiliadoEntity,
        helperRegistroTelefonoEntity = helperRegistroTelefono,
        helperValidarExisteUsuarioEnJAC = helperValidarExisteUsuarioEnJAC,
        helperRegistroCredencialesSesionAfiliado = helperRegistroCredencialesSesionAfiliado
    )

    @Provides
    fun providesHelperIniciarSesion(
        credencialesSesionDao: CredencialesSesionDao
    ) : HelperIniciarSesion = HelperIniciarSesion(
        credencialesSesionDao = credencialesSesionDao
    )

    @Provides
    fun providesHelperRegistroJAC(
        jacDao: JacDao,
        correoDao: CorreoDao,
        credencialesSesionDao: CredencialesSesionDao
    ) : HelperRegistroJAC = HelperRegistroJAC(
        jacDao = jacDao,
        correoDao = correoDao,
        credencialesSesionDao = credencialesSesionDao
    )

    @Provides
    fun providesHelperTraerListaJACSRegistrados(jacDao: JacDao) : HelperTraerListaJACSRegistrados = HelperTraerListaJACSRegistrados(jacDao = jacDao)
    //endregion
    //endregion

    //region splash

    //region primer nivel
    @Provides
    fun providesHelperCargarEstadoAfiliacion(
        estadoAfiliacionDao: EstadoAfiliacionDao
    ) : HelperCargarEstadoAfiliacion = HelperCargarEstadoAfiliacion(
        estadoAfiliacionDao = estadoAfiliacionDao
    )

    @Provides
    fun providesHelperCargarFuncionesApp(
        funcionesRolAppDao : FuncionesRolAppDao
    ) : HelperCargarFuncionesApp = HelperCargarFuncionesApp(
        funcionesRolAppDao = funcionesRolAppDao
    )

    @Provides
    fun providesHelperCargarRelacionRolFunciones(
        rolappFuncionesappDao: RolApp_FuncionesApp_Dao
    ) : HelperCargarRelacionRolFunciones = HelperCargarRelacionRolFunciones(
        rolappFuncionesappDao = rolappFuncionesappDao
    )

    @Provides
    fun providesHelperCargarRolAfiliacion(
        rolAfiliacionDao: RolAfiliacionDao
    ) : HelperCargarRolAfiliacion = HelperCargarRolAfiliacion(
        rolAfiliacionDao = rolAfiliacionDao
    )

    @Provides
    fun providesHelperCargarRolesApp(
        rolesAppDao: RolesAppDao
    ) : HelperCargarRolesApp = HelperCargarRolesApp(
        rolesAppDao = rolesAppDao
    )

    @Provides
    fun providesHelperCargarTiposDocumento(
        tipoDocumentoDao: TipoDocumentoDao
    ) : HelperCargarTiposDocumento = HelperCargarTiposDocumento(
        tipoDocumentoDao = tipoDocumentoDao
    )

    @Provides
    fun providesHelperCargarTiposTelefono(
        tipoTelefonoDao: TipoTelefonoDao
    ) : HelperCargarTiposTelefono = HelperCargarTiposTelefono(
        tipoTelefonoDao = tipoTelefonoDao
    )
    //endregion

    //region segundo nivel
    @Provides
    fun providesHelperCargarTipos(
        helperCargarEstadoAfiliacion : HelperCargarEstadoAfiliacion,
        helperCargarFuncionesApp: HelperCargarFuncionesApp,
        helperCargarRolAfiliacion: HelperCargarRolAfiliacion,
        helperCargarRelacionRolFunciones: HelperCargarRelacionRolFunciones,
        helperCargarRolesApp: HelperCargarRolesApp,
        helperCargarTiposDocumento: HelperCargarTiposDocumento,
        helperCargarTiposTelefono: HelperCargarTiposTelefono
    ) : HelperCargarTipos = HelperCargarTipos(
        helperCargarEstadoAfiliacion = helperCargarEstadoAfiliacion,
        helperCargarFuncionesApp= helperCargarFuncionesApp,
        helperCargarRolAfiliacion= helperCargarRolAfiliacion,
        helperCargarRelacionRolFunciones = helperCargarRelacionRolFunciones,
        helperCargarRolesApp= helperCargarRolesApp,
        helperCargarTiposDocumento= helperCargarTiposDocumento,
        helperCargarTiposTelefono= helperCargarTiposTelefono
    )
    //endregion

    //endregion
}