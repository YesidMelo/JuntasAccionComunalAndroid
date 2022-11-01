package com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea

import androidx.room.Dao
import androidx.room.Query
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.BaseDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.reunionAsamblea.ConvocantesEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.home.ConvocantesDisponiblesView
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.actasParaPDF.ConvocantesReunionGenerarActaPDFModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.reunionParaConvocatoriaPDF.ConvocanteReunionGenerarConvocatoriaPDFModel

@Dao
interface ConvocatesDao : BaseDao<ConvocantesEntity> {

    @Query("Select t.* FROM ConvocantesDisponiblesView t WHERE t.jacId = :jacId")
    fun traerListaConvocantesPorJacId(jacId:Int): List<ConvocantesDisponiblesView>

    @Query("Select ae.* from AfiliadoEntity ae, ConvocantesEntity ce where ce.afiliadoId = ae.afiliadoId and ce.reunionId = :reunionId")
    fun traerListaConvocantesConvocatoriaPorReunionId(reunionId: Int) :List<ConvocanteReunionGenerarConvocatoriaPDFModel>

    @Query("Select ae.* from AfiliadoEntity ae, ConvocantesEntity ce where ce.afiliadoId = ae.afiliadoId and ce.reunionId = :reunionId")
    fun traerListaConvocantesActaPorReunionId(reunionId: Int) :List<ConvocantesReunionGenerarActaPDFModel>
}