package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.reunionAsamblea

import com.hefesto.juntasaccioncomunal.fuentesDatos.cache.MemoriaCache
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.AfiliadoDao
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.AfiliadoActaAsistenciaModel
import javax.inject.Inject

interface HelperListaAfiliadosAsistenciaDB {
    suspend fun traerListaAfiliadosAsistencia(): List<AfiliadoActaAsistenciaModel>
}

class HelperListaAfiliadosAsistenciaDBImpl constructor(
    @JvmField @Inject var afiliadoDao: AfiliadoDao,
    @JvmField @Inject var memoriaCache: MemoriaCache
) : HelperListaAfiliadosAsistenciaDB {

    override suspend fun traerListaAfiliadosAsistencia(): List<AfiliadoActaAsistenciaModel> {
        val lista = emptyList<AfiliadoActaAsistenciaModel>().toMutableList()
        for (contador in 1 until 5) {
            var documento = ""
            for (contadorDocumento in 0 until 5) {
                documento += contador.toString()
            }
            lista.add(
                AfiliadoActaAsistenciaModel(
                    afiliadoId = contador,
                    nombres = "nombre $contador",
                    apellidos = "apellido $contador",
                    numeroDocumento = documento,
                )
            )
        }
        return lista
    }

}