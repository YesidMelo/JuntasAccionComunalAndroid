package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.listaAfiliadosRegistroActualizacion

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.CredencialesSesionDao
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.convertirADatosSeguridadAfiliadoModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.DatosBasicosAfiliadoActualizarRegistrarInformacionModel
import javax.inject.Inject

interface HelperListaAfiliadosCargarDatosSeguridad {
    suspend fun cargarSeguridadAfiliado(lista: List<DatosBasicosAfiliadoActualizarRegistrarInformacionModel>)
}

class HelperListaAfiliadosCargarDatosSeguridadImpl constructor(
    @JvmField @Inject var credencialesSesionDao: CredencialesSesionDao
) : HelperListaAfiliadosCargarDatosSeguridad {

    override suspend fun cargarSeguridadAfiliado(lista: List<DatosBasicosAfiliadoActualizarRegistrarInformacionModel>) {
        for(item in lista) {
            if (item.credencialesSesion == null) continue
            val seguridad = credencialesSesionDao.traerCredencialesPorCredencialesId(credencialesId = item.credencialesSesion!!)
            item.datosSeguridadAfiliadoModel = seguridad.convertirADatosSeguridadAfiliadoModel()
        }
    }

}