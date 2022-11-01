package com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea

import androidx.room.Dao
import androidx.room.Query
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.BaseDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.reunionAsamblea.ReunionAsambleaEntity
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.actasParaPDF.ConvocantesReunionGenerarActaPDFModel

@Dao
interface ReunionAsambleaDao : BaseDao<ReunionAsambleaEntity> {

    @Query("SELECT * from ReunionAsambleaEntity t where t.creoActa = 0 and t.jacId = :jacId")
    fun traerListaReunionesParaCreacionActas(jacId: Int) : List<ReunionAsambleaEntity>

    @Query("SELECT * from ReunionAsambleaEntity t where t.creoActa = 1 and t.jacId = :jacId order by t.fechaRegistro desc")
    fun traerListaReunionesParaGeneracionPDF(jacId: Int) : List<ReunionAsambleaEntity>

    @Query("SELECT ae.* from  " +
            " (SELECT * From (SELECT * from Afiliado_Jac_EstadoAfiliacionEntity ajae order by fechaactualizacion desc) tabla GROUP by afiliadoid)  " +
            "    ajae, " +
            "    ReunionAsambleaEntity rae, " +
            "    AfiliadoEntity ae " +
            "WHERE  " +
            " rae.jacid = ajae.jacId and " +
            "    ajae.fechaactualizacion < rae.fechaRegistro and " +
            "    ae.afiliadoId = ajae.afiliadoid and " +
            "    ajae.rolEnLaAppId = 3 and " +
            "    rae.reunionAsambleaId = :reunionId ")
    fun traerPresidenteReunionPorReunionId(reunionId : Int) : ConvocantesReunionGenerarActaPDFModel

    @Query("SELECT ae.* from  " +
            " (SELECT * From (SELECT * from Afiliado_Jac_EstadoAfiliacionEntity ajae order by fechaactualizacion desc) tabla GROUP by afiliadoid)  " +
            "    ajae, " +
            "    ReunionAsambleaEntity rae, " +
            "    AfiliadoEntity ae " +
            "WHERE  " +
            " rae.jacid = ajae.jacId and " +
            "    ajae.fechaactualizacion < rae.fechaRegistro and " +
            "    ae.afiliadoId = ajae.afiliadoid and " +
            "    ajae.rolEnLaAppId = 5 and " +
            "    rae.reunionAsambleaId = :reunionId ")
    fun traerSecretarioReunionPorReunionId(reunionId : Int) : ConvocantesReunionGenerarActaPDFModel

}