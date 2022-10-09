package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.HomeDBDatasource
import com.hefesto.juntasaccioncomunal.logica.modelos.home.AfiliadoModificacionDirectivaModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FuncionesRolApp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

interface HomeRepositorio {
    fun traerFuncionalidadesRol() : MutableLiveData<List<FuncionesRolApp>>
    fun traerListaAfiliadosModificacionRolDirectiva() : MutableLiveData<List<AfiliadoModificacionDirectivaModel>>
}

class HomeRepositorioImpl constructor(
    @JvmField @Inject var homeApiDatasource: HomeApiDatasource,
    @JvmField @Inject var homeCacheDatasource: HomeCacheDatasource,
    @JvmField @Inject var homeDBDatasource: HomeDBDatasource,
    @JvmField @Inject var homeSharedPreferencesDatasource: HomeSharedPreferencesDatasource,
) : HomeRepositorio{

    //region variables
    private val funcionesLiveData =  MutableLiveData<List<FuncionesRolApp>>()
    private val listaAfiliadosModificacionRolDirectivaLiveData = MutableLiveData<List<AfiliadoModificacionDirectivaModel>>()
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

    override fun traerListaAfiliadosModificacionRolDirectiva(): MutableLiveData<List<AfiliadoModificacionDirectivaModel>> {
        GlobalScope.launch {

            homeCacheDatasource
                .traerJacId()
                .collect{ jacId -> if (jacId == null) { listaAfiliadosModificacionRolDirectivaLiveData.postValue(emptyList()); return@collect }
                    homeDBDatasource
                        .traerListaAfiliadosModificacionRolDirectiva(jacId = jacId)
                        .collect{ listaAfiliados ->
                            listaAfiliadosModificacionRolDirectivaLiveData.postValue(listaAfiliados)
                        }
                }
        }
        return listaAfiliadosModificacionRolDirectivaLiveData
    }

}