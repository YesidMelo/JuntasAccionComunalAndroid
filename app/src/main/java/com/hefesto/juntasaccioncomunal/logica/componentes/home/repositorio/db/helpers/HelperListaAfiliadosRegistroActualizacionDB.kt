package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers

import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.listaAfiliadosRegistroActualizacion.HelperListaAfiliadosCargarDatosBasicos
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.listaAfiliadosRegistroActualizacion.HelperListaAfiliadosCargarDatosContacto
import com.hefesto.juntasaccioncomunal.logica.modelos.home.DatosBasicosAfiliadoActualizarRegistrarInformacionModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface HelperListaAfiliadosRegistroActualizacionDB {
    fun traerListaAfiliados() : Flow<List<DatosBasicosAfiliadoActualizarRegistrarInformacionModel>?>
}

class HelperListaAfiliadosRegistroActualizacionImpl constructor(
    @JvmField @Inject var helperListaAfiliadosCargarDatosBasicos : HelperListaAfiliadosCargarDatosBasicos,
    @JvmField @Inject var helperListaAfiliadosCargarDatosContacto: HelperListaAfiliadosCargarDatosContacto
) : HelperListaAfiliadosRegistroActualizacionDB {

    override fun traerListaAfiliados(): Flow<List<DatosBasicosAfiliadoActualizarRegistrarInformacionModel>?>  = flow {
       helperListaAfiliadosCargarDatosBasicos
           .traerListaAfiliados()
           .collect {
               val lista = it?: emptyList()
               helperListaAfiliadosCargarDatosContacto.cargarContacto(lista= lista)
               emit(lista)
           }
    }

}