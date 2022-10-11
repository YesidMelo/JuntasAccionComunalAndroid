package com.hefesto.juntasaccioncomunal.di.fuenteDatos

import com.hefesto.juntasaccioncomunal.MiAplicacion
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.BaseDatosApp
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.ComitesDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.FuncionesRolAppDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.RolApp_FuncionesApp_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.RolesAppDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.TipoDocumentoDao
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
import dagger.Module
import dagger.Provides

@Module
class BaseDatosModule {

    @Provides
    fun providesBaseDatosLocal() : BaseDatosApp = BaseDatosApp.traerInstancia(context = MiAplicacion.traerInstancia()!!.applicationContext)

    @Provides
    fun providesAfiliadoDao(baseDatosApp: BaseDatosApp): AfiliadoDao = baseDatosApp.AfiliadoDao()

    @Provides
    fun providesAfiliado_Correo_Dao(baseDatosApp: BaseDatosApp): Afiliado_Correo_Dao = baseDatosApp.afiliado_correo_dao()

    @Provides
    fun providesAfiliado_Jac_Comite_Dao(baseDatosApp: BaseDatosApp) : Afiliado_Jac_Comite_Dao = baseDatosApp.afiliado_Jac_Comite_Dao()

    @Provides
    fun providesAfiliado_Jac_EstadoAfiliacionDao(baseDatosApp: BaseDatosApp): Afiliado_Jac_EstadoAfiliacionDao = baseDatosApp.afiliado_Jac_EstadoAfiliacionDao()

    @Provides
    fun providesAfiliado_Telefono_Dao(baseDatosApp: BaseDatosApp): Afiliado_Telefono_Dao = baseDatosApp.afiliado_Telefono_Dao()

    @Provides
    fun providesAfiliadoDireccionDao(baseDatosApp: BaseDatosApp): Afiliado_Direccion_Dao = baseDatosApp.afiliado_Direccion_Dao()

    @Provides
    fun providesComiteDao(baseDatosApp: BaseDatosApp) : ComitesDao = baseDatosApp.comitesDao()

    @Provides
    fun providesCorreoDao(baseDatosApp: BaseDatosApp): CorreoDao = baseDatosApp.correoDao()

    @Provides
    fun providesCredencialesSesionDao(baseDatosApp: BaseDatosApp): CredencialesSesionDao = baseDatosApp.credencialesSesionDao()

    @Provides
    fun providesDireccionDao(baseDatosApp: BaseDatosApp): DireccionDao = baseDatosApp.direccionDao()

    @Provides
    fun providesEstadoAfiliacionDao(baseDatosApp: BaseDatosApp): EstadoAfiliacionDao = baseDatosApp.EstadoAfiliacionDao()

    @Provides
    fun providesFuncionesAppDao(baseDatosApp: BaseDatosApp): FuncionesRolAppDao = baseDatosApp.funcionesRolAppDao()

    @Provides
    fun providesJacDao(baseDatosApp: BaseDatosApp): JacDao = baseDatosApp.JacDao()

    @Provides
    fun providesJacAfiliadoDireccionDao(baseDatosApp: BaseDatosApp): Jac_Afiliado_Direccion_Dao = baseDatosApp.Jac_Afiliado_Direccion_Dao()

    @Provides
    fun providesRolAfiliacionDao(baseDatosApp: BaseDatosApp): RolAfiliacionDao = baseDatosApp.rolAfiliacionDao()

    @Provides
    fun providesRolesAppDao(baseDatosApp: BaseDatosApp): RolesAppDao = baseDatosApp.rolesAppDao()

    @Provides
    fun providesRolAppFuncionesAppDao(baseDatosApp: BaseDatosApp) : RolApp_FuncionesApp_Dao = baseDatosApp.rolApp_funcionApp_dao()

    @Provides
    fun providesTelefonoDao(baseDatosApp: BaseDatosApp): Telefono_Dao = baseDatosApp.telefonoDao()

    @Provides
    fun providesTipoDocumentoDao(baseDatosApp: BaseDatosApp): TipoDocumentoDao = baseDatosApp.TipoDocumentoDao()

    @Provides
    fun providesTipoTelefonoDao(baseDatosApp: BaseDatosApp): TipoTelefonoDao = baseDatosApp.TipoTelefonoDao()


}