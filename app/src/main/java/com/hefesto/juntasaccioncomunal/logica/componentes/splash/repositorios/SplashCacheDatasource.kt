package com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.fuentesDatos.cache.MemoriaCache
import com.hefesto.juntasaccioncomunal.logica.excepciones.LogicaExcepcion
import com.hefesto.juntasaccioncomunal.logica.utilidades.IdentificadorElementosCacheEnum
import javax.inject.Inject

interface SplashCacheDatasource {
    fun inicializarEscuchadorExcepciones()
}

class SplashCacheDatasourceImpl constructor(
    @JvmField @Inject var cache: MemoriaCache
): SplashCacheDatasource {

    override fun inicializarEscuchadorExcepciones() {
        cache.AdicionarActualizarElemento(Pair(
            first = IdentificadorElementosCacheEnum.EXCEPCIONES_APP,
            second = MutableLiveData<LogicaExcepcion?>()
        ))
    }
}