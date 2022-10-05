package com.hefesto.juntasaccioncomunal.di.logica

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.AfiliadoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Correo_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Direccion_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Jac_EstadoAfiliacionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.CorreoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.DireccionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.JacDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Jac_Afiliado_Direccion_Dao
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.HelperIniciarSesion
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.HelperRegistroAfilado
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.HelperRegistroJAC
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.HelperTraerListaJACSRegistrados
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.registroAfiliado.HelperRegistroAfiliadoEntity
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.registroAfiliado.HelperRegistroCorreoAfiliadoEntity
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.registroAfiliado.HelperRegistroDireccionAfiliadoEntity
import dagger.Module
import dagger.Provides

@Module
class HelpersDBDatasourceModule {

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
    fun provideHelperRegistroCorreoAfiliadoEntity(
        afiliadoCorreoDao: Afiliado_Correo_Dao,
        afiliadoDao: AfiliadoDao,
        correoDao: CorreoDao
    ) : HelperRegistroCorreoAfiliadoEntity = HelperRegistroCorreoAfiliadoEntity(
        afiliadoCorreoDao = afiliadoCorreoDao,
        afiliadoDao = afiliadoDao,
        correoDao = correoDao
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

    //endregion

    //region segundo nivel

    @Provides
    fun providesHelperRegistroAfiliado(
        helperRegistroAfiliadoEntity : HelperRegistroAfiliadoEntity,
        helperRegistroCorreoAfiliadoEntity : HelperRegistroCorreoAfiliadoEntity,
        helperRegistroDireccionAfiliadoEntity: HelperRegistroDireccionAfiliadoEntity
    ) : HelperRegistroAfilado = HelperRegistroAfilado(
        helperRegistroAfiliadoEntity = helperRegistroAfiliadoEntity,
        helperRegistroCorreoAfiliadoEntity = helperRegistroCorreoAfiliadoEntity,
        helperRegistroDireccionAfiliadoEntity = helperRegistroDireccionAfiliadoEntity
    )

    @Provides
    fun providesHelperIniciarSesion(jacDao: JacDao) : HelperIniciarSesion = HelperIniciarSesion(jacDao = jacDao)

    @Provides
    fun providesHelperRegistroJAC(jacDao: JacDao) : HelperRegistroJAC = HelperRegistroJAC(jacDao = jacDao)

    @Provides
    fun providesHelperTraerListaJACSRegistrados(jacDao: JacDao) : HelperTraerListaJACSRegistrados = HelperTraerListaJACSRegistrados(jacDao = jacDao)
    //endregion
    //endregion
}