package com.hefesto.juntasaccioncomunal.logica.componentes.base.repositorios

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.logica.excepciones.LogicaExcepcion
import javax.inject.Inject

interface BaseRepositorio {
    fun traerExcepcionesLiveData(): MutableLiveData<LogicaExcepcion?>
}

class BaseRepositorioImpl constructor(
    @JvmField @Inject var baseCacheDatasource: BaseCacheDatasource
): BaseRepositorio {

    override fun traerExcepcionesLiveData(): MutableLiveData<LogicaExcepcion?> = baseCacheDatasource.traerExcepcionesLiveData()

}