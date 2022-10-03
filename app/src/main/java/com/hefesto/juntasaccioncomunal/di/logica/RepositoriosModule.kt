package com.hefesto.juntasaccioncomunal.di.logica

import com.hefesto.juntasaccioncomunal.logica.componentes.base.repositorios.BaseCacheDatasource
import com.hefesto.juntasaccioncomunal.logica.componentes.base.repositorios.BaseRepositorio
import com.hefesto.juntasaccioncomunal.logica.componentes.base.repositorios.BaseRepositorioImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.LoginApiDatasource
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.LoginCacheDatasource
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.LoginRepositorio
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.LoginRepositorioImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.LoginSharedPreferencesDatasource
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.LoginDBDatasource
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.SplashApiDatasource
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.SplashCacheDatasource
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.SplashDBDatasource
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.SplashRepositorio
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.SplashRepositorioImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.SplashSharedPreferencesDatasource
import dagger.Module
import dagger.Provides

@Module
class RepositoriosModule {

    //region base
    @Provides
    fun providesBaseRepositorio(
        baseCacheDatasource: BaseCacheDatasource
    ): BaseRepositorio = BaseRepositorioImpl(baseCacheDatasource = baseCacheDatasource)
    //endregion

    //region login

    @Provides
    fun providesLoginRepository(
        loginApiDatasource: LoginApiDatasource,
        loginCacheDatasource: LoginCacheDatasource,
        loginDBDatasource: LoginDBDatasource,
        loginSharedPreferencesDatasource: LoginSharedPreferencesDatasource
    ): LoginRepositorio = LoginRepositorioImpl(
        loginApiDatasource = loginApiDatasource,
        loginCacheDatasource= loginCacheDatasource,
        loginDBDatasource = loginDBDatasource,
        loginSharedPreferencesDatasource = loginSharedPreferencesDatasource
    )
    //endregion

    //region splash
    @Provides
    fun providesSplashRepositorio(
        splashApiDatasource: SplashApiDatasource,
        splashCacheDatasource: SplashCacheDatasource,
        splashDBDatasource: SplashDBDatasource,
        splashSharedPreferencesDatasource: SplashSharedPreferencesDatasource
    ) : SplashRepositorio = SplashRepositorioImpl(
        splashApiDatasource = splashApiDatasource,
        splashCacheDatasource = splashCacheDatasource,
        splashDBDatasource = splashDBDatasource,
        splashSharedPreferencesDatasource= splashSharedPreferencesDatasource
    )
    //endregion
}