package com.hefesto.juntasaccioncomunal.di.fuenteDatos

import com.hefesto.juntasaccioncomunal.MiAplicacion
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.BaseDatosApp
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.BaseDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.FuncionesRolAppDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.RolesAppDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.TipoDocumentoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.TipoTelefonoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.AfiliadoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.EstadoAfiliacionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.JacDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.RolAfiliacionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.EstadoAfiliacionEntity
import dagger.Module
import dagger.Provides

@Module
class BaseDatosModule {

    @Provides
    fun providesBaseDatosLocal() : BaseDatosApp = BaseDatosApp.traerInstancia(context = MiAplicacion.traerInstancia()!!.applicationContext)

    @Provides
    fun providesAfiliadoDao(baseDatosApp: BaseDatosApp): AfiliadoDao = baseDatosApp.AfiliadoDao()

    @Provides
    fun providesEstadoAfiliacionDao(baseDatosApp: BaseDatosApp): EstadoAfiliacionDao = baseDatosApp.EstadoAfiliacionDao()

    @Provides
    fun providesFuncionesAppDao(baseDatosApp: BaseDatosApp): FuncionesRolAppDao = baseDatosApp.funcionesRolAppDao()

    @Provides
    fun providesJacDao(baseDatosApp: BaseDatosApp): JacDao = baseDatosApp.JacDao()

    @Provides
    fun providesRolAfiliacionDao(baseDatosApp: BaseDatosApp): RolAfiliacionDao = baseDatosApp.rolAfiliacionDao()

    @Provides
    fun providesRolesAppDao(baseDatosApp: BaseDatosApp): RolesAppDao = baseDatosApp.rolesAppDao()

    @Provides
    fun providesTipoDocumentoDao(baseDatosApp: BaseDatosApp): TipoDocumentoDao = baseDatosApp.TipoDocumentoDao()

    @Provides
    fun providesTipoTelefonoDao(baseDatosApp: BaseDatosApp): TipoTelefonoDao = baseDatosApp.TipoTelefonoDao()


}