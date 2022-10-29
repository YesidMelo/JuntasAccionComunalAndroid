package com.hefesto.juntasaccioncomunal.fuentesDatos.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.converters.BooleanConverter
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.ComitesDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.FuncionesRolAppDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.RolApp_FuncionesApp_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.RolesAppDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.TipoDocumentoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.TipoReunionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.TipoTelefonoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.AfiliadoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Correo_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Direccion_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Jac_Comite_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Jac_EstadoAfiliacionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Telefono_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.CorreoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.CredencialesSesionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.DireccionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.EstadoAfiliacionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.JacDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Jac_Afiliado_Direccion_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.RolAfiliacionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Telefono_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea.ConvocatesDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea.ListaAsistenciaDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea.PuntosReunionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea.ReunionAsambleaDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.general.ComitesEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.general.FuncionesRolAppEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.general.RolApp_FuncionesApp_Entity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.general.RolesAppEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.general.TipoDocumentoEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.general.TipoReunionEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.general.TipoTelefonoEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.AfiliadoEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.Afiliado_Correo_Entity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.Afiliado_Direccion_Entity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.Afiliado_Jac_Comite_Entity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.Afiliado_Jac_EstadoAfiliacionEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.Afiliado_Telefono_Entity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.CorreosEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.CredencialesSesionEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.login.CredencialesSesionView
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.DireccionesEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.EstadoAfiliacionEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.JACEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.Jac_Afiliado_Direccion_Entity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.RolAfiliacionEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.TelefonosEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.reunionAsamblea.ConvocantesEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.reunionAsamblea.ListaAsistenciaEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.reunionAsamblea.PuntosReunionEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.reunionAsamblea.ReunionAsambleaEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.home.AfiliadoActualizacionRegistroView
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.home.AfiliadoAsistenciaView
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.home.AfiliadoContactoRegistroActualizacionView
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.home.AfiliadoDetalleEnJacView
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.home.AfiliadoModificacionDirectivaView
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.home.ConvocantesDisponiblesView
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.login.AfiliadoEnSesionView
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.login.JACEnSesionView

@Database(
    entities = [
        AfiliadoEntity::class,
        Afiliado_Correo_Entity::class,
        Afiliado_Direccion_Entity::class,
        Afiliado_Jac_Comite_Entity::class,
        Afiliado_Jac_EstadoAfiliacionEntity::class,
        Afiliado_Telefono_Entity::class,
        ConvocantesEntity::class,
        ComitesEntity::class,
        CorreosEntity::class,
        CredencialesSesionEntity::class,
        DireccionesEntity::class,
        EstadoAfiliacionEntity::class,
        FuncionesRolAppEntity::class,
        JACEntity::class,
        Jac_Afiliado_Direccion_Entity::class,
        ListaAsistenciaEntity::class,
        PuntosReunionEntity::class,
        RolAfiliacionEntity::class,
        RolesAppEntity::class,
        RolApp_FuncionesApp_Entity::class,
        ReunionAsambleaEntity::class,
        TelefonosEntity::class,
        TipoDocumentoEntity::class,
        TipoReunionEntity::class,
        TipoTelefonoEntity::class
   ],
    views = [
        AfiliadoActualizacionRegistroView::class,
        AfiliadoAsistenciaView::class,
        AfiliadoContactoRegistroActualizacionView::class,
        AfiliadoDetalleEnJacView::class,
        AfiliadoEnSesionView::class,
        AfiliadoModificacionDirectivaView::class,
        ConvocantesDisponiblesView::class,
        CredencialesSesionView::class,
        JACEnSesionView::class,
    ],
    version = 1
)
@TypeConverters(BooleanConverter::class)
abstract class BaseDatosApp : RoomDatabase() {

    companion object{
        private val NOMBRE_DB = "BaseDatos"
        private var instancia: BaseDatosApp? = null

        fun traerInstancia(context: Context) : BaseDatosApp {
            if(instancia == null){
                instancia = Room
                    .databaseBuilder(context, BaseDatosApp::class.java, NOMBRE_DB)
                    .build()
            }
            return instancia!!
        }
    }

    abstract fun AfiliadoDao(): AfiliadoDao
    abstract fun afiliado_correo_dao() : Afiliado_Correo_Dao
    abstract fun afiliado_Direccion_Dao() : Afiliado_Direccion_Dao
    abstract fun afiliado_Jac_Comite_Dao() : Afiliado_Jac_Comite_Dao
    abstract fun afiliado_Jac_EstadoAfiliacionDao() : Afiliado_Jac_EstadoAfiliacionDao
    abstract fun afiliado_Telefono_Dao() : Afiliado_Telefono_Dao
    abstract fun comitesDao() : ComitesDao
    abstract fun convocatesDao() : ConvocatesDao
    abstract fun correoDao() : CorreoDao
    abstract fun credencialesSesionDao(): CredencialesSesionDao
    abstract fun direccionDao() : DireccionDao
    abstract fun EstadoAfiliacionDao() : EstadoAfiliacionDao
    abstract fun funcionesRolAppDao() : FuncionesRolAppDao
    abstract fun JacDao() : JacDao
    abstract fun Jac_Afiliado_Direccion_Dao(): Jac_Afiliado_Direccion_Dao
    abstract fun listaAsistenciaDao(): ListaAsistenciaDao
    abstract fun puntosReunionDao(): PuntosReunionDao
    abstract fun reunionAsambleaDao(): ReunionAsambleaDao
    abstract fun rolAfiliacionDao(): RolAfiliacionDao
    abstract fun rolesAppDao() : RolesAppDao
    abstract fun rolApp_funcionApp_dao() : RolApp_FuncionesApp_Dao
    abstract fun telefonoDao() : Telefono_Dao
    abstract fun TipoDocumentoDao() : TipoDocumentoDao
    abstract fun TipoTelefonoDao() : TipoTelefonoDao
    abstract fun TipoReunionDao() : TipoReunionDao
}