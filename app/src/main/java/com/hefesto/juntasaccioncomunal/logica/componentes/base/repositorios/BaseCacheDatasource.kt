package com.hefesto.juntasaccioncomunal.logica.componentes.base.repositorios

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.fuentesDatos.cache.MemoriaCache
import com.hefesto.juntasaccioncomunal.logica.excepciones.LogicaExcepcion
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.IdentificadorElementosCacheEnum
import javax.inject.Inject

interface BaseCacheDatasource {
    fun traerExcepcionesLiveData(): MutableLiveData<LogicaExcepcion?>
    fun traerMemoriaCache(): MemoriaCache
}

open class BaseCacheDatasourceImpl constructor(
    @JvmField @Inject var memoriaCache: MemoriaCache
) : BaseCacheDatasource{

    override fun traerExcepcionesLiveData(): MutableLiveData<LogicaExcepcion?> {
        return traerEscuchadorExcepcion()?: MutableLiveData<LogicaExcepcion?>()
    }

    override fun traerMemoriaCache(): MemoriaCache = memoriaCache

    //region metodos privados
    private fun traerEscuchadorExcepcion() : MutableLiveData<LogicaExcepcion?>? {
        return memoriaCache.traerObjeto<MutableLiveData<LogicaExcepcion?>>(llave = IdentificadorElementosCacheEnum.EXCEPCIONES_APP)
    }
    //endregion
}