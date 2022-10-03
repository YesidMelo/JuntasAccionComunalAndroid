package com.hefesto.juntasaccioncomunal.fuentesDatos.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.AfiliadoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.JacDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.AfiliadoEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.JACEntity

@Database(
    entities = [
        AfiliadoEntity::class,
        JACEntity::class
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
    abstract fun JacDao() : JacDao
}