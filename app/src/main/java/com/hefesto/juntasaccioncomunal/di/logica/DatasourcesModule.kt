package com.hefesto.juntasaccioncomunal.di.logica

import com.hefesto.juntasaccioncomunal.fuentesDatos.cache.MemoriaCache
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.FuncionesRolAppDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.RolesAppDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.TipoDocumentoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.TipoTelefonoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.AfiliadoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Correo_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Jac_EstadoAfiliacionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.CorreoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.EstadoAfiliacionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.JacDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.RolAfiliacionDao
import com.hefesto.juntasaccioncomunal.logica.componentes.base.repositorios.BaseCacheDatasource
import com.hefesto.juntasaccioncomunal.logica.componentes.base.repositorios.BaseCacheDatasourceImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.base.repositorios.BaseDBDatasource
import com.hefesto.juntasaccioncomunal.logica.componentes.base.repositorios.BaseDBDatasourceImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.HomeApiDatasource
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.HomeApiDatasourceImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.HomeCacheDatasource
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.HomeCacheDatasourceImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.HomeDBDatasource
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.HomeDBDatasourceImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.HomeSharedPreferencesDatasource
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.HomeSharedPreferencesDatasourceImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.LoginApiDatasource
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.LoginApiDatasourceImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.LoginCacheDatasource
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.LoginCacheDatasourceImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.LoginSharedPreferencesDatasource
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.LoginSharedPreferencesDatasourceImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.LoginDBDatasource
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.LoginDBDatasourceImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.HelperIniciarSesion
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.HelperRegistroAfilado
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.HelperRegistroJAC
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.HelperTraerDetalleUsuarioSesion
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.HelperTraerListaJACSRegistrados
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.SplashApiDatasource
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.SplashApiDatasourceImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.SplashCacheDatasource
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.SplashCacheDatasourceImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.SplashDBDatasource
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.SplashDBDatasourceImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.SplashSharedPreferencesDatasource
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.SplashSharedPreferencesDatasourceImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.HelperCargarTipos
import dagger.Module
import dagger.Provides

@Module
class DatasourcesModule {

    //region base
    @Provides
    fun providesBaseCacheDatasource(memoriaCache: MemoriaCache) : BaseCacheDatasource = BaseCacheDatasourceImpl(memoriaCache = memoriaCache)

    @Provides
    fun providesBaseDBDatasource(memoriaCache: MemoriaCache) : BaseDBDatasource = BaseDBDatasourceImpl(memoriaCache = memoriaCache)
    //endregion

    //region home
    @Provides
    fun providesHomeApiDatasource() : HomeApiDatasource = HomeApiDatasourceImpl()

    @Provides
    fun providesHomeCacheDatasource(
        memoriaCache: MemoriaCache
    ) : HomeCacheDatasource = HomeCacheDatasourceImpl(
        memoriaCache = memoriaCache
    )

    @Provides
    fun providesHomeDBDatasource(): HomeDBDatasource = HomeDBDatasourceImpl()

    @Provides
    fun providesHomeSharedPreferencesDatesources(): HomeSharedPreferencesDatasource = HomeSharedPreferencesDatasourceImpl()

    //endregion

    //region login
    @Provides
    fun providesLoginApiDatasource() : LoginApiDatasource = LoginApiDatasourceImpl()

    @Provides
    fun providesLoginCacheDatasource(baseCacheDatasource: BaseCacheDatasource): LoginCacheDatasource = LoginCacheDatasourceImpl(baseCacheDatasource = baseCacheDatasource)

    @Provides
    fun providesLoginDbDatasource(
        memoriaCache: MemoriaCache,
        helperRegistroJAC: HelperRegistroJAC,
        helperIniciarSesion: HelperIniciarSesion,
        helperTraerListaJACSRegistrados: HelperTraerListaJACSRegistrados,
        helperRegistroAfilado: HelperRegistroAfilado,
        helperTraerDetalleUsuarioSesion: HelperTraerDetalleUsuarioSesion
    )  : LoginDBDatasource
    = LoginDBDatasourceImpl(
        memoriaCacheLocal = memoriaCache,
        helperRegistroJAC = helperRegistroJAC,
        helperIniciarSesion = helperIniciarSesion,
        helperTraerListaJACSRegistrados = helperTraerListaJACSRegistrados,
        helperRegistroAfilado = helperRegistroAfilado,
        helperTraerDetalleUsuarioSesion = helperTraerDetalleUsuarioSesion
    )

    @Provides
    fun providesLoginSharedPreferencesDatasource(): LoginSharedPreferencesDatasource = LoginSharedPreferencesDatasourceImpl()
    //endregion

    //region splash
    @Provides
    fun providesSplashApiDatasource(): SplashApiDatasource = SplashApiDatasourceImpl()

    @Provides
    fun providesSplashCacheDatasource(memoriaCache: MemoriaCache): SplashCacheDatasource = SplashCacheDatasourceImpl(cache = memoriaCache)

    @Provides
    fun providesSplashDBDatasource(
        helperCargarTipos : HelperCargarTipos
    ): SplashDBDatasource = SplashDBDatasourceImpl(
        helperCargarTipos = helperCargarTipos
    )

    @Provides
    fun provideSplashSharedPreferencesDatasource(): SplashSharedPreferencesDatasource = SplashSharedPreferencesDatasourceImpl()

    //endregion

}