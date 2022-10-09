package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FuncionesRolApp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

interface HomeRepositorio {
    fun traerFuncionalidadesRol() : MutableLiveData<List<FuncionesRolApp>>
}

class HomeRepositorioImpl constructor(
    @JvmField @Inject var homeApiDatasource: HomeApiDatasource,
    @JvmField @Inject var homeCacheDatasource: HomeCacheDatasource,
    @JvmField @Inject var homeDBDatasource: HomeDBDatasource,
    @JvmField @Inject var homeSharedPreferencesDatasource: HomeSharedPreferencesDatasource,
) : HomeRepositorio{

    //region variables
    private val funcionesLiveData =  MutableLiveData<List<FuncionesRolApp>>()
    //endregion


    override fun traerFuncionalidadesRol(): MutableLiveData<List<FuncionesRolApp>> {
        GlobalScope.launch {
            homeCacheDatasource
                .cargarFuncionalidades()
                .collect{
                    funcionesLiveData.postValue(it)
                }
        }
        return funcionesLiveData
    }

}