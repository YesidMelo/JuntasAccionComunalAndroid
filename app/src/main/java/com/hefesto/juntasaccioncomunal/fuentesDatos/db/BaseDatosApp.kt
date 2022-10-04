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
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.EstadoAfiliacionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.JacDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.RolAfiliacionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.general.FuncionesRolAppEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.general.RolesAppEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.general.TipoDocumentoEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.general.TipoTelefonoEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.AfiliadoEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.EstadoAfiliacionEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.JACEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.RolAfiliacionEntity
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FuncionesRolApp

@Database(
    entities = [
        AfiliadoEntity::class,
        EstadoAfiliacionEntity::class,
        RolesAppEntity::class,
        FuncionesRolAppEntity::class,
        JACEntity::class,
        RolAfiliacionEntity::class,
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
    abstract fun EstadoAfiliacionDao() : EstadoAfiliacionDao
    abstract fun funcionesRolAppDao() : FuncionesRolAppDao
    abstract fun JacDao() : JacDao
    abstract fun rolAfiliacionDao(): RolAfiliacionDao
    abstract fun rolesAppDao() : RolesAppDao
    abstract fun TipoDocumentoDao() : TipoDocumentoDao
    abstract fun TipoTelefonoDao() : TipoTelefonoDao
}