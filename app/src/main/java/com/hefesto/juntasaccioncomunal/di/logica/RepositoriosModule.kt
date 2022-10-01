package com.hefesto.juntasaccioncomunal.di.logica

import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.*
import dagger.Module
import dagger.Provides

@Module
class RepositoriosModule {

    //region login
    @Provides
    fun providesLoginApiDatasource() : LoginApiDatasource = LoginApiDatasourceImpl()

    @Provides
    fun providesLoginCacheDatasource(): LoginCacheDatasource = LoginCacheDatasourceImpl()

    @Provides
    fun providesLoginDbDatasource()  :LoginDBDatasource = LoginDBDatasourceImpl()

    @Provides
    fun providesLoginSharedPreferencesDatasource(): LoginSharedPreferencesDatasource = LoginSharedPreferencesDatasourceImpl()

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
}