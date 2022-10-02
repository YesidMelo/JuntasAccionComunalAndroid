package com.hefesto.juntasaccioncomunal.di.logica

import com.hefesto.juntasaccioncomunal.fuentesDatos.cache.MemoriaCache
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.JacDao
import com.hefesto.juntasaccioncomunal.logica.componentes.base.repositorios.BaseCacheDatasource
import com.hefesto.juntasaccioncomunal.logica.componentes.base.repositorios.BaseCacheDatasourceImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.base.repositorios.BaseDBDatasource
import com.hefesto.juntasaccioncomunal.logica.componentes.base.repositorios.BaseDBDatasourceImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.*
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.LoginDBDatasource
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.LoginDBDatasourceImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.*
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


    //region login
    @Provides
    fun providesLoginApiDatasource() : LoginApiDatasource = LoginApiDatasourceImpl()

    @Provides
    fun providesLoginCacheDatasource(baseCacheDatasource: BaseCacheDatasource): LoginCacheDatasource = LoginCacheDatasourceImpl(baseCacheDatasource = baseCacheDatasource)

    @Provides
    fun providesLoginDbDatasource(memoriaCache: MemoriaCache, jacDao: JacDao)  : LoginDBDatasource
    = LoginDBDatasourceImpl(memoriaCacheLocal = memoriaCache, jacDao = jacDao)

    @Provides
    fun providesLoginSharedPreferencesDatasource(): LoginSharedPreferencesDatasource = LoginSharedPreferencesDatasourceImpl()
    //endregion

    //region splash
    @Provides
    fun providesSplashApiDatasource(): SplashApiDatasource = SplashApiDatasourceImpl()

    @Provides
    fun providesSplashCacheDatasource(memoriaCache: MemoriaCache): SplashCacheDatasource = SplashCacheDatasourceImpl(cache = memoriaCache)

    @Provides
    fun providesSplashDBDatasource(): SplashDBDatasource = SplashDBDatasourceImpl()

    @Provides
    fun provideSplashSharedPreferencesDatasource(): SplashSharedPreferencesDatasource = SplashSharedPreferencesDatasourceImpl()

    //endregion

}