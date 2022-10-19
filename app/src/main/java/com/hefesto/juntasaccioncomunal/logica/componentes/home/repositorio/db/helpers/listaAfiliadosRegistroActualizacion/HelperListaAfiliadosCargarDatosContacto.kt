package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.listaAfiliadosRegistroActualizacion

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.AfiliadoDao
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.convertirADatosContactoAfiliadoModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.DatosBasicosAfiliadoActualizarRegistrarInformacionModel
import javax.inject.Inject

interface HelperListaAfiliadosCargarDatosContacto {
    suspend fun cargarContacto(lista: List<DatosBasicosAfiliadoActualizarRegistrarInformacionModel>)
}

class HelperListaAfiliadosCargarDatosContactoImpl constructor(
    @JvmField @Inject var afiliadoDao: AfiliadoDao
) : HelperListaAfiliadosCargarDatosContacto {

    override suspend fun cargarContacto(lista: List<DatosBasicosAfiliadoActualizarRegistrarInformacionModel>) {
        for (item in lista) {
            if (item.afiliadoId == null) continue
            val afiliadoContacto = afiliadoDao.traerContactoDelAfiliadoPorAfiliadoId(afiliadoId = item.afiliadoId!!)
            afiliadoContacto?.afiliadoid = item.afiliadoId
            item.datosContactoAfiliadoModel = afiliadoContacto?.convertirADatosContactoAfiliadoModel()
        }
    }

}