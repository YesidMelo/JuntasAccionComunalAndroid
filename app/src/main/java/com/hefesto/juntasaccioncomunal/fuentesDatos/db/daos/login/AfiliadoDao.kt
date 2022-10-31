package com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login

import androidx.room.Dao
import androidx.room.Query
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.BaseDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.AfiliadoEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.home.AfiliadoActualizacionRegistroView
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.home.AfiliadoAsistenciaView
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.home.AfiliadoContactoRegistroActualizacionView
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.home.AfiliadoDetalleEnJacView
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.home.AfiliadoModificacionDirectivaView
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.login.AfiliadoEnSesionView
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.AfiliadoEnSesionModel

@Dao
interface AfiliadoDao : BaseDao<AfiliadoEntity> {

    @Query("SELECT t.afiliadoId FROM AfiliadoEntity t WHERE t.tipoDocumento = :tipoDocumento AND t.documento = :documento")
    fun traerIdPorTipoDocumentoYDocumento(tipoDocumento: Int, documento: String) : Int?

    @Query("SELECT * FROM AfiliadoEntity t WHERE t.tipoDocumento = :tipoDocumento AND t.documento = :documento")
    fun traerAfiliadoEntityPorTipoDocumentoYDocumento(tipoDocumento: Int, documento: String) : AfiliadoEntity?

    @Query("SELECT * FROM AfiliadoAsistenciaView t WHERE t.jacId = :jacId GROUP by afiliadoid")
    fun traerAfiliadosParaAsistencia(jacId: Int) : List<AfiliadoAsistenciaView>

    @Query("SELECT * FROM AfiliadoEnSesionView a WHERE a.correo = :correo")
    fun traerDetalleUsuarioEnSesionPorCorreo(correo: String) : AfiliadoEnSesionView?

    @Query("SELECT a.* FROM AfiliadoModificacionDirectivaView a WHERE a.jacId = :jacID")
    fun traerListaUsuariosModificacionDirectivaEntity(jacID: Int) : List<AfiliadoModificacionDirectivaView>

    @Query("SELECT t.* FROM AfiliadoActualizacionRegistroView t WHERE t.jacId = :jacID")
    fun traerListaAfiliadosParaActualizacionRegistroEntity(jacID: Int) : List<AfiliadoActualizacionRegistroView>

    @Query("SELECT t.* FROM AfiliadoContactoRegistroActualizacionView t WHERE t.afiliadoid = :afiliadoId")
    fun traerContactoDelAfiliadoPorAfiliadoId(afiliadoId: Int) : AfiliadoContactoRegistroActualizacionView?

    @Query("SELECT t.* FROM AfiliadoDetalleEnJacView t WHERE t.afiliadoId = :afiliadoId and t.jacId = :jacId")
    fun traerAfiliadoDetalleJAC(afiliadoId: Int, jacId: Int) : AfiliadoDetalleEnJacView?
}