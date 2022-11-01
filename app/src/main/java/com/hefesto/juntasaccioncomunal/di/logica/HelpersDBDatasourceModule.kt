package com.hefesto.juntasaccioncomunal.di.logica

import com.hefesto.juntasaccioncomunal.fuentesDatos.cache.MemoriaCache
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.ComitesDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.FuncionesRolAppDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.RolApp_FuncionesApp_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.RolesAppDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.TipoDocumentoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.TipoReunionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.TipoTelefonoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.AfiliadoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Correo_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Direccion_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Jac_Comite_Dao
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
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea.ConvocatesDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea.ListaAsistenciaDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea.PuntosReunionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea.ReunionAsambleaDao
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.HelperActualizarAfiliadoEnDirectivaDB
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.HelperActualizarAfiliadoEnDirectivaDBImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.HelperListaAfiliadosModificacionDirectivaDB
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.HelperListaAfiliadosRegistroActualizacionDB
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.HelperListaAfiliadosRegistroActualizacionImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.HelperRegistrarActualizarAfiliadoDB
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.HelperRegistrarActualizarAfiliadoDBImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.HelperReunionAsambleaDB
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.HelperReunionAsambleaDBImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.listaAfiliadosRegistroActualizacion.HelperListaAfiliadosCargarDatosBasicos
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.listaAfiliadosRegistroActualizacion.HelperListaAfiliadosCargarDatosBasicosImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.listaAfiliadosRegistroActualizacion.HelperListaAfiliadosCargarDatosContacto
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.listaAfiliadosRegistroActualizacion.HelperListaAfiliadosCargarDatosContactoImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.listaAfiliadosRegistroActualizacion.HelperListaAfiliadosCargarDatosSeguridad
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.listaAfiliadosRegistroActualizacion.HelperListaAfiliadosCargarDatosSeguridadImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.listaAfiliadosRegistroActualizacion.HelperListaAfiliadosCargarDetalleJAC
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.listaAfiliadosRegistroActualizacion.HelperListaAfiliadosCargarDetalleJACImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.registroAfiliado.HelperRegistroActualizacionAfiliadoDetalleEnJacHome
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.registroAfiliado.HelperRegistroActualizacionAfiliadoDetalleEnJacHomeImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.registroAfiliado.HelperRegistroActualizacionContactoAfiliadoHome
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.registroAfiliado.HelperRegistroActualizacionContactoAfiliadoHomeImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.registroAfiliado.HelperRegistroActualizacionDatosBasicosAfiliadoHome
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.registroAfiliado.HelperRegistroActualizacionDatosBasicosAfiliadoHomeImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.registroAfiliado.HelperRegistroActualizacionSeguridadAfiliadoHome
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.registroAfiliado.HelperRegistroActualizacionSeguridadAfiliadoHomeImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.reunionAsamblea.HelperAgendarReunionDB
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.reunionAsamblea.HelperAgendarReunionDBImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.reunionAsamblea.HelperCrearActaDB
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.reunionAsamblea.HelperCrearActaDBImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.reunionAsamblea.HelperListaAfiliadosAsistenciaDB
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.reunionAsamblea.HelperListaAfiliadosAsistenciaDBImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.reunionAsamblea.HelperListaConvocantesReunionDisponiblesDB
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.reunionAsamblea.HelperListaConvocantesReunionDisponiblesDBImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.reunionAsamblea.HelperListaReunionesParaConvocatoriasPDFDB
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.reunionAsamblea.HelperListaReunionesParaConvocatoriasPDFDBImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.reunionAsamblea.HelperListaReunionesParaCrearActasDB
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.reunionAsamblea.HelperListaReunionesParaCrearActasDBImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.reunionAsamblea.HelperListaReunionesParaCrearPDFDB
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.reunionAsamblea.HelperListaReunionesParaCrearPDFDBImpl
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
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos.HelperCargarComites
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos.HelperCargarEstadoAfiliacion
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos.HelperCargarFuncionesApp
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos.HelperCargarRelacionRolFunciones
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos.HelperCargarRolAfiliacion
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos.HelperCargarRolesApp
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos.HelperCargarTiposDocumento
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos.HelperCargarTiposReunion
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos.HelperCargarTiposReunionImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.cargarTipos.HelperCargarTiposTelefono
import dagger.Module
import dagger.Provides

@Module
class HelpersDBDatasourceModule {

    //region home

    //region primer nivel

    //region afiliado
    @Provides
    fun providesHelperActualizarAfiliadoEnDirectivaDB(
        memoriaCache: MemoriaCache,
        afiliadoJacEstadoafiliaciondao: Afiliado_Jac_EstadoAfiliacionDao
    ) : HelperActualizarAfiliadoEnDirectivaDB = HelperActualizarAfiliadoEnDirectivaDBImpl(
        memoriaCache = memoriaCache,
        afiliadoJacEstadoafiliaciondao = afiliadoJacEstadoafiliaciondao
    )

    @Provides
    fun providesHelperListaAfiliadosModificacionDirectivaDB(
        afiliadoDao: AfiliadoDao
    ) : HelperListaAfiliadosModificacionDirectivaDB = HelperListaAfiliadosModificacionDirectivaDB(
        afiladoDao = afiliadoDao
    )

    @Provides
    fun providesHelperListaAfiliadosCargarDatosBasicos(
        afiliadoDao: AfiliadoDao,
        memoriaCache: MemoriaCache
    ) : HelperListaAfiliadosCargarDatosBasicos = HelperListaAfiliadosCargarDatosBasicosImpl(
        afiliadoDao = afiliadoDao,
        memoriaCache = memoriaCache
    )

    @Provides
    fun providesHelperListaAfiliadosCargarDetalleJAC(
        afiliadoDao: AfiliadoDao
    ) : HelperListaAfiliadosCargarDetalleJAC = HelperListaAfiliadosCargarDetalleJACImpl(
        afiliadoDao = afiliadoDao
    )

    @Provides
    fun providesHelperListaAfiliadosCargarDatosContacto(
        afiliadoDao: AfiliadoDao
    ) : HelperListaAfiliadosCargarDatosContacto = HelperListaAfiliadosCargarDatosContactoImpl(
        afiliadoDao = afiliadoDao
    )

    @Provides
    fun providesHelperListaAfiliadosCargarDatosSeguridad(
        credencialesSesionDao: CredencialesSesionDao
    ) : HelperListaAfiliadosCargarDatosSeguridad =  HelperListaAfiliadosCargarDatosSeguridadImpl(
        credencialesSesionDao = credencialesSesionDao
    )

    @Provides
    fun providesHelperRegistroActualizacionAfiliadoDetalleEnJacHome(
        afiliadoJacComiteDao: Afiliado_Jac_Comite_Dao,
        afiliadoJacEstadoafiliaciondao: Afiliado_Jac_EstadoAfiliacionDao,
        memoriaCache: MemoriaCache
    ): HelperRegistroActualizacionAfiliadoDetalleEnJacHome = HelperRegistroActualizacionAfiliadoDetalleEnJacHomeImpl(
        afiliadoJacComiteDao = afiliadoJacComiteDao,
        afiliadoJacEstadoafiliaciondao = afiliadoJacEstadoafiliaciondao,
        memoriaCache = memoriaCache
    )

    @Provides
    fun providesHelperRegistroActualizacionContactoAfiliadoHome(
        afiliadoCorreoDao: Afiliado_Correo_Dao,
        direccionDao: DireccionDao,
        afiliadoDireccionDao: Afiliado_Direccion_Dao,
        telefonoDao: Telefono_Dao,
        afiliadoTelefonoDao: Afiliado_Telefono_Dao,
    ) : HelperRegistroActualizacionContactoAfiliadoHome = HelperRegistroActualizacionContactoAfiliadoHomeImpl(
        afiliadoCorreoDao = afiliadoCorreoDao,
        direccionDao = direccionDao,
        afiliadoDireccionDao = afiliadoDireccionDao,
        telefonoDao = telefonoDao,
        afiliadoTelefonoDao = afiliadoTelefonoDao,
    )

    @Provides
    fun providesHelperRegistroActualizacionDatosBasicosAfiliado(
        afiliadoDao: AfiliadoDao
    ) : HelperRegistroActualizacionDatosBasicosAfiliadoHome = HelperRegistroActualizacionDatosBasicosAfiliadoHomeImpl (
        afiliadoDao = afiliadoDao
    )

    @Provides
    fun provideHelperRegistroActualizacionSeguridadAfiliadoHome(
        correoDao: CorreoDao,
        credencialesSesionDao: CredencialesSesionDao,
    ) : HelperRegistroActualizacionSeguridadAfiliadoHome = HelperRegistroActualizacionSeguridadAfiliadoHomeImpl (
        correoDao = correoDao,
        credencialesSesionDao = credencialesSesionDao,
    )
    //endregion

    //region reunion asamblea
    @Provides
    fun providesHelperAgendarReunionDB(
        convocatesDao: ConvocatesDao,
        reunionAsambleaDao: ReunionAsambleaDao,
        puntosReunionDao: PuntosReunionDao,
        memoriaCache: MemoriaCache
    ) : HelperAgendarReunionDB = HelperAgendarReunionDBImpl(
        convocatesDao= convocatesDao,
        reunionAsambleaDao = reunionAsambleaDao,
        puntosReunionDao = puntosReunionDao,
        memoriaCache = memoriaCache
    )

    @Provides
    fun providesHelperCrearActaDB(
        reunionDao: ReunionAsambleaDao,
        puntosReunionDao: PuntosReunionDao,
        listaAsambleaDao: ListaAsistenciaDao
    ) : HelperCrearActaDB = HelperCrearActaDBImpl(
        reunionDao = reunionDao,
        puntosReunionDao = puntosReunionDao,
        listaAsambleaDao = listaAsambleaDao
    )

    @Provides
    fun providesHelperListaAfiliadosAsistenciaDB(
        afiliadoDao: AfiliadoDao,
        memoriaCache: MemoriaCache
    ) : HelperListaAfiliadosAsistenciaDB = HelperListaAfiliadosAsistenciaDBImpl(
        afiliadoDao = afiliadoDao,
        memoriaCache = memoriaCache
    )

    @Provides
    fun providesHelperListaReunionesParaCrearActasDB(
        reunionAsambleaDao: ReunionAsambleaDao,
        puntosReunionDao: PuntosReunionDao,
        memoriaCache: MemoriaCache
    ) : HelperListaReunionesParaCrearActasDB = HelperListaReunionesParaCrearActasDBImpl(
        reunionAsambleaDao = reunionAsambleaDao,
        puntosReunionDao = puntosReunionDao,
        memoriaCache = memoriaCache
    )

    @Provides
    fun providesHelperListaReunionesParaCrearPDFDB(
        reunionDao: ReunionAsambleaDao,
        puntosReunionDao: PuntosReunionDao,
        listaAsistenciaDao: ListaAsistenciaDao,
        convocatesDao: ConvocatesDao,
        memoriaCache: MemoriaCache
    ) : HelperListaReunionesParaCrearPDFDB = HelperListaReunionesParaCrearPDFDBImpl(
        reunionAsambleaDao = reunionDao,
        puntosReunionDao = puntosReunionDao,
        listaAsistenciaDao = listaAsistenciaDao,
        convocantesDao = convocatesDao,
        memoriaCache = memoriaCache
    )

    @Provides
    fun providesHelperListaConvocantesReunionDisponiblesDB(
        convocatesDao: ConvocatesDao,
        memoriaCache: MemoriaCache
    ) : HelperListaConvocantesReunionDisponiblesDB = HelperListaConvocantesReunionDisponiblesDBImpl(
        convocatesDao = convocatesDao,
        memoriaCache = memoriaCache
    )

    @Provides
    fun providesHelperListaReunionesParaConvocatoriasPDFDB(
        reunionDao: ReunionAsambleaDao,
        puntosReunionDao: PuntosReunionDao,
        convocatesDao: ConvocatesDao,
        memoriaCache: MemoriaCache
    ) : HelperListaReunionesParaConvocatoriasPDFDB = HelperListaReunionesParaConvocatoriasPDFDBImpl(
        reunionAsambleaDao = reunionDao,
        puntosReunionDao = puntosReunionDao,
        convocantesDao = convocatesDao,
        memoriaCache = memoriaCache
    )

    //endregion

    //endregion

    //region segundo nivel

    //region afiliado
    @Provides
    fun providesHelperListaAfiliadosRegistroActualizacion(
        helperListaAfiliadosCargarDatosBasicos: HelperListaAfiliadosCargarDatosBasicos,
        helperListaAfiliadosCargarDatosContacto: HelperListaAfiliadosCargarDatosContacto,
        helperListaAfiliadosCargarDatosSeguridad: HelperListaAfiliadosCargarDatosSeguridad,
        helperListaAfiliadosCargarDetalleJAC: HelperListaAfiliadosCargarDetalleJAC
    ) : HelperListaAfiliadosRegistroActualizacionDB = HelperListaAfiliadosRegistroActualizacionImpl(
        helperListaAfiliadosCargarDatosBasicos = helperListaAfiliadosCargarDatosBasicos,
        helperListaAfiliadosCargarDatosContacto = helperListaAfiliadosCargarDatosContacto,
        helperListaAfiliadosCargarDatosSeguridad = helperListaAfiliadosCargarDatosSeguridad,
        helperListaAfiliadosCargarDetalleJAC = helperListaAfiliadosCargarDetalleJAC
    )

    @Provides
    fun providesHelperRegistrarActualizarAfiliado(
        helperRegistroActualizacionAfiliadoDetalleEnJacHome: HelperRegistroActualizacionAfiliadoDetalleEnJacHome,
        helperRegistroActualizacionContactoAfiliadoHome: HelperRegistroActualizacionContactoAfiliadoHome,
        helperRegistroActualizacionDatosBasicosAfiliadoHome: HelperRegistroActualizacionDatosBasicosAfiliadoHome,
        helperRegistroActualizacionSeguridadAfiliadoHome: HelperRegistroActualizacionSeguridadAfiliadoHome
    ) : HelperRegistrarActualizarAfiliadoDB = HelperRegistrarActualizarAfiliadoDBImpl(
        helperRegistroActualizacionAfiliadoDetalleEnJacHome = helperRegistroActualizacionAfiliadoDetalleEnJacHome,
        helperRegistroActualizacionContactoAfiliadoHome = helperRegistroActualizacionContactoAfiliadoHome,
        helperRegistroActualizacionDatosBasicosAfiliadoHome = helperRegistroActualizacionDatosBasicosAfiliadoHome,
        helperRegistroActualizacionSeguridadAfiliadoHome = helperRegistroActualizacionSeguridadAfiliadoHome
    )
    //endregion

    //region reunion / asamblea
    @Provides
    fun providesHelperReunionAsambleaDB(
        helperAgendarReunionDB: HelperAgendarReunionDB,
        helperListaReunionesParaCrearActasDB: HelperListaReunionesParaCrearActasDB,
        helperListaAfiliadosAsistenciaDB: HelperListaAfiliadosAsistenciaDB,
        helperCrearActaDB: HelperCrearActaDB,
        helperListaReunionesParaCrearPDFDB: HelperListaReunionesParaCrearPDFDB,
        helperListaConvocantesReunionDisponiblesDB : HelperListaConvocantesReunionDisponiblesDB,
        helperListaReunionesParaConvocatoriasPDFDB: HelperListaReunionesParaConvocatoriasPDFDB
    ) : HelperReunionAsambleaDB = HelperReunionAsambleaDBImpl(
        helperAgendarReunionDB = helperAgendarReunionDB,
        helperListaReunionesParaCrearActasDB = helperListaReunionesParaCrearActasDB,
        helperListaAfiliadosAsistenciaDB = helperListaAfiliadosAsistenciaDB,
        helperCrearActaDB = helperCrearActaDB,
        helperListaReunionesParaCrearPDFDB = helperListaReunionesParaCrearPDFDB,
        helperListaConvocantesReunionDisponiblesDB = helperListaConvocantesReunionDisponiblesDB,
        helperListaReunionesParaConvocatoriasPDFDB = helperListaReunionesParaConvocatoriasPDFDB
    )
    //endregion
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
    fun providesHelperIniciarSesion(
        credencialesSesionDao: CredencialesSesionDao
    ) : HelperIniciarSesion = HelperIniciarSesion(
        credencialesSesionDao = credencialesSesionDao
    )

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
    fun providesHelperCargarComites (
        comitesDao: ComitesDao
    ) : HelperCargarComites = HelperCargarComites(
        comitesDao = comitesDao
    )

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
    fun providesHelperCargarTiposReunion(
        tipoReunionDao: TipoReunionDao
    ) : HelperCargarTiposReunion = HelperCargarTiposReunionImpl(
        tipoReunionDao = tipoReunionDao
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
        helperCargarComites: HelperCargarComites,
        helperCargarEstadoAfiliacion : HelperCargarEstadoAfiliacion,
        helperCargarFuncionesApp: HelperCargarFuncionesApp,
        helperCargarRolAfiliacion: HelperCargarRolAfiliacion,
        helperCargarRelacionRolFunciones: HelperCargarRelacionRolFunciones,
        helperCargarRolesApp: HelperCargarRolesApp,
        helperCargarTiposDocumento: HelperCargarTiposDocumento,
        helperCargarTiposReunion : HelperCargarTiposReunion,
        helperCargarTiposTelefono: HelperCargarTiposTelefono
    ) : HelperCargarTipos = HelperCargarTipos(
        helperCargarComites = helperCargarComites,
        helperCargarEstadoAfiliacion = helperCargarEstadoAfiliacion,
        helperCargarFuncionesApp= helperCargarFuncionesApp,
        helperCargarRolAfiliacion= helperCargarRolAfiliacion,
        helperCargarRelacionRolFunciones = helperCargarRelacionRolFunciones,
        helperCargarRolesApp= helperCargarRolesApp,
        helperCargarTiposDocumento= helperCargarTiposDocumento,
        helperCargarTiposReunion = helperCargarTiposReunion,
        helperCargarTiposTelefono= helperCargarTiposTelefono
    )
    //endregion

    //endregion

}