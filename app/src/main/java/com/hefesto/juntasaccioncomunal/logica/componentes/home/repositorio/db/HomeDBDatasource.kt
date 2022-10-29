package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db

import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.HelperActualizarAfiliadoEnDirectivaDB
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.HelperListaAfiliadosModificacionDirectivaDB
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.HelperListaAfiliadosRegistroActualizacionDB
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.HelperRegistrarActualizarAfiliadoDB
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.HelperReunionAsambleaDB
import com.hefesto.juntasaccioncomunal.logica.modelos.home.AfiliadoEnDirectivaModificadoModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.AfiliadoParaModificacionDirectivaModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.DatosBasicosAfiliadoActualizarRegistrarInformacionModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.CompiladoInformacionAfiliadoParaRegistroModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.actasParaPDF.ReunionParaGenerarPDFModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.agendarReunion.ConvocanteReunionAsambleaAAgendarModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.agendarReunion.DetalleReunionAAgendarModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.AfiliadoActaAsistenciaModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.ReunionAsambleaCreacionActaModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.reunionParaConvocatoriaPDF.ReunionParaGenerarConvocatoriaPDFModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface HomeDBDatasource {
    //region afiliado
    fun actualizarAfiliadoModificacionRolDirectiva(afiliadoEnDirectivaModificadoModel: AfiliadoEnDirectivaModificadoModel) : Flow<Boolean?>
    fun registrarActualizarAfiliado(compiladoInformacionAfiliadoParaRegistroModel: CompiladoInformacionAfiliadoParaRegistroModel) : Flow<Boolean?>
    fun traerListaAfiliadosActualizacionRegistro(): Flow<List<DatosBasicosAfiliadoActualizarRegistrarInformacionModel>?>
    fun traerListaAfiliadosModificacionRolDirectiva(jacId : Int) : Flow<List<AfiliadoParaModificacionDirectivaModel>>
    //endregion

    //region reuniones/asambleas
    fun agendarReunion(detalleReunionAAgendarModel: DetalleReunionAAgendarModel) : Flow<Boolean>
    fun traerListaReunionesParaCrearActa() : Flow<List<ReunionAsambleaCreacionActaModel>>
    fun traerListaAfiliadosAsistencia(): Flow<List<AfiliadoActaAsistenciaModel>>
    fun guardarActa( asistencia: MutableList<AfiliadoActaAsistenciaModel>, detalleReunion: ReunionAsambleaCreacionActaModel ) :  Flow<Boolean>
    fun traerListaReunionesParaGenerarPDF(): Flow<List<ReunionParaGenerarPDFModel>>
    fun traerListaPosiblesConvocantesReunion() : Flow<List<ConvocanteReunionAsambleaAAgendarModel>>
    fun traerListaReunionesParaConvocatoriasPDF(): Flow<List<ReunionParaGenerarConvocatoriaPDFModel>>
    //endregion
}

class HomeDBDatasourceImpl constructor(

    //region afiliado
    @JvmField @Inject var helperActualizarAfiliadoEnDirectivaDB : HelperActualizarAfiliadoEnDirectivaDB,
    @JvmField @Inject var helperListaAfiliadosModificacionDirectivaDB : HelperListaAfiliadosModificacionDirectivaDB,
    @JvmField @Inject var helperListaAfiliadosRegistroActualizacionDB: HelperListaAfiliadosRegistroActualizacionDB,
    @JvmField @Inject var helperRegistrarActualizarAfiliadoDB: HelperRegistrarActualizarAfiliadoDB,
    //endregion

    //region reunion/asamblea
    @JvmField @Inject var helperReunionAsambleaDB: HelperReunionAsambleaDB
    //endregion

) : HomeDBDatasource {

    //region afiliado
    override fun traerListaAfiliadosModificacionRolDirectiva(jacId : Int): Flow<List<AfiliadoParaModificacionDirectivaModel>>
    = helperListaAfiliadosModificacionDirectivaDB.traerListaAfiliadosModificacionRolDirectiva(jacId = jacId)

    override fun actualizarAfiliadoModificacionRolDirectiva(afiliadoEnDirectivaModificadoModel: AfiliadoEnDirectivaModificadoModel) : Flow<Boolean?>
    = helperActualizarAfiliadoEnDirectivaDB.actualizarAfiliadoEnDirectiva(afiliadoEnDirectivaModificadoModel = afiliadoEnDirectivaModificadoModel)

    override fun registrarActualizarAfiliado(compiladoInformacionAfiliadoParaRegistroModel: CompiladoInformacionAfiliadoParaRegistroModel)
    = helperRegistrarActualizarAfiliadoDB.registrarActualizarAfiliado(compiladoInformacionAfiliadoParaRegistroModel = compiladoInformacionAfiliadoParaRegistroModel)

    override fun traerListaAfiliadosActualizacionRegistro(): Flow<List<DatosBasicosAfiliadoActualizarRegistrarInformacionModel>?>
    = helperListaAfiliadosRegistroActualizacionDB.traerListaAfiliados()
    //endregion

    //region reunion / asamblea
    override fun agendarReunion(detalleReunionAAgendarModel: DetalleReunionAAgendarModel): Flow<Boolean>
    = helperReunionAsambleaDB.agendarReunion(detalleReunionAAgendarModel = detalleReunionAAgendarModel)

    override fun traerListaReunionesParaCrearActa(): Flow<List<ReunionAsambleaCreacionActaModel>>
    = helperReunionAsambleaDB.traerListaReunionesParaCrearActa()

    override fun traerListaAfiliadosAsistencia(): Flow<List<AfiliadoActaAsistenciaModel>>
    = helperReunionAsambleaDB.traerListaAfiliadosAsistencia()

    override fun guardarActa(
        asistencia: MutableList<AfiliadoActaAsistenciaModel>,
        detalleReunion: ReunionAsambleaCreacionActaModel
    ): Flow<Boolean> = helperReunionAsambleaDB.guardarActa(
        asistencia = asistencia,
        detalleReunion = detalleReunion
    )

    override fun traerListaReunionesParaGenerarPDF(): Flow<List<ReunionParaGenerarPDFModel>>
    = helperReunionAsambleaDB.traerListaReunionesParaGenerarPDF()

    override fun traerListaPosiblesConvocantesReunion(): Flow<List<ConvocanteReunionAsambleaAAgendarModel>>
    = helperReunionAsambleaDB.traerListaPosiblesConvocantesReunion()

    override fun traerListaReunionesParaConvocatoriasPDF(): Flow<List<ReunionParaGenerarConvocatoriaPDFModel>>
    = helperReunionAsambleaDB.traerListaReunionesParaConvocatoriasPDF()
    //endregion

}