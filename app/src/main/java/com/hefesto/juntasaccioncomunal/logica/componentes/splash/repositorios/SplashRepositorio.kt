package com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios

import javax.inject.Inject

interface SplashRepositorio {
    fun iniciarEscuchadorExcepciones()
}

class SplashRepositorioImpl constructor(
    @JvmField @Inject var splashApiDatasource: SplashApiDatasource,
    @JvmField @Inject var splashCacheDatasource: SplashCacheDatasource,
    @JvmField @Inject var splashDBDatasource: SplashDBDatasource,
    @JvmField @Inject var splashSharedPreferencesDatasource: SplashSharedPreferencesDatasource,
):  SplashRepositorio {

    override fun iniciarEscuchadorExcepciones() = splashCacheDatasource.inicializarEscuchadorExcepciones()

}