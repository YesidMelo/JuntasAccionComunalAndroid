package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.HomeDBDatasource
import com.hefesto.juntasaccioncomunal.logica.modelos.home.AfiliadoEnDirectivaModificadoModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.AfiliadoParaModificacionDirectivaModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.DatosBasicosAfiliadoActualizarRegistrarInformacionModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.CompiladoInformacionAfiliadoParaRegistroModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FuncionesRolApp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface HomeRepositorio {
    fun actualizarAfiliadoEnDirectiva(afiliadoEnDirectivaModificadoModel: AfiliadoEnDirectivaModificadoModel) : Flow<Boolean?>
    fun registrarActualizarAfiliado(compiladoInformacionAfiliadoParaRegistroModel: CompiladoInformacionAfiliadoParaRegistroModel) :  Flow<Boolean?>
    fun traerListaAfiliadosRegistroActualizacion(): Flow<List<DatosBasicosAfiliadoActualizarRegistrarInformacionModel>?>
    fun traerFuncionalidadesRol() : MutableLiveData<List<FuncionesRolApp>>
    fun traerListaAfiliadosModificacionRolDirectiva() : MutableLiveData<List<AfiliadoParaModificacionDirectivaModel>>
}

class HomeRepositorioImpl constructor(
    @JvmField @Inject var homeApiDatasource: HomeApiDatasource,
    @JvmField @Inject var homeCacheDatasource: HomeCacheDatasource,
    @JvmField @Inject var homeDBDatasource: HomeDBDatasource,
    @JvmField @Inject var homeSharedPreferencesDatasource: HomeSharedPreferencesDatasource,
) : HomeRepositorio{

    //region variables
    private val funcionesLiveData =  MutableLiveData<List<FuncionesRolApp>>()
    private val listaAfiliadosModificacionRolDirectivaLiveData = MutableLiveData<List<AfiliadoParaModificacionDirectivaModel>>()
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

    override fun traerListaAfiliadosModificacionRolDirectiva(): MutableLiveData<List<AfiliadoParaModificacionDirectivaModel>> {
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

    override fun actualizarAfiliadoEnDirectiva(afiliadoEnDirectivaModificadoModel: AfiliadoEnDirectivaModificadoModel): Flow<Boolean?>
    = homeDBDatasource.actualizarAfiliadoModificacionRolDirectiva(afiliadoEnDirectivaModificadoModel = afiliadoEnDirectivaModificadoModel)

    override fun registrarActualizarAfiliado(compiladoInformacionAfiliadoParaRegistroModel: CompiladoInformacionAfiliadoParaRegistroModel): Flow<Boolean?>
    = homeDBDatasource.registrarActualizarAfiliado(compiladoInformacionAfiliadoParaRegistroModel = compiladoInformacionAfiliadoParaRegistroModel)

    override fun traerListaAfiliadosRegistroActualizacion(): Flow<List<DatosBasicosAfiliadoActualizarRegistrarInformacionModel>?>
    = homeDBDatasource.traerListaAfiliadosActualizacionRegistro()


}