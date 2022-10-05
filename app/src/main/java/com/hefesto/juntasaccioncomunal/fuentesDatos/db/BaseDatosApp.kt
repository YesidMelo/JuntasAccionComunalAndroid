package com.hefesto.juntasaccioncomunal.fuentesDatos.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.FuncionesRolAppDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.RolesAppDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.TipoDocumentoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.TipoTelefonoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.AfiliadoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Correo_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Jac_EstadoAfiliacionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.CorreoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.EstadoAfiliacionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.JacDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.RolAfiliacionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.general.FuncionesRolAppEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.general.RolesAppEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.general.TipoDocumentoEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.general.TipoTelefonoEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.AfiliadoEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.Afiliado_Correo_Entity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.Afiliado_Jac_EstadoAfiliacionEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.CorreosEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.EstadoAfiliacionEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.JACEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.RolAfiliacionEntity

@Database(
    entities = [
        AfiliadoEntity::class,
        Afiliado_Correo_Entity::class,
        Afiliado_Jac_EstadoAfiliacionEntity::class,
        CorreosEntity::class,
        EstadoAfiliacionEntity::class,
        FuncionesRolAppEntity::class,
        JACEntity::class,
        RolAfiliacionEntity::class,
        RolesAppEntity::class,
        TipoDocumentoEntity::class,
        TipoTelefonoEntity::class
   ],
    version = 1
)
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
    abstract fun afiliado_Jac_EstadoAfiliacionDao() : Afiliado_Jac_EstadoAfiliacionDao
    abstract fun afiliado_correo_dao() : Afiliado_Correo_Dao
    abstract fun correoDao() : CorreoDao
    abstract fun EstadoAfiliacionDao() : EstadoAfiliacionDao
    abstract fun funcionesRolAppDao() : FuncionesRolAppDao
    abstract fun JacDao() : JacDao
    abstract fun rolAfiliacionDao(): RolAfiliacionDao
    abstract fun rolesAppDao() : RolesAppDao
    abstract fun TipoDocumentoDao() : TipoDocumentoDao
    abstract fun TipoTelefonoDao() : TipoTelefonoDao
}