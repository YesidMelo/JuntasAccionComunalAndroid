package com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios

import javax.inject.Inject

interface SplashRepositorio {
    suspend fun iniciarEscuchadorExcepciones()
    suspend fun cargarConstantesEnDB()
}

class SplashRepositorioImpl constructor(
    @JvmField @Inject var splashApiDatasource: SplashApiDatasource,
    @JvmField @Inject var splashCacheDatasource: SplashCacheDatasource,
    @JvmField @Inject var splashDBDatasource: SplashDBDatasource,
    @JvmField @Inject var splashSharedPreferencesDatasource: SplashSharedPreferencesDatasource,
):  SplashRepositorio {

    override suspend fun iniciarEscuchadorExcepciones() = splashCacheDatasource.inicializarEscuchadorExcepciones()

    override suspend fun cargarConstantesEnDB() = splashDBDatasource.cargarTipos()


}