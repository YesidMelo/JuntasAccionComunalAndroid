package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.AfiliadoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Correo_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Jac_EstadoAfiliacionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.CorreoDao
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.registroAfiliado.HelperAfiliadoEntity
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.registroAfiliado.HelperCorreoAfiliadoEntity
import com.hefesto.juntasaccioncomunal.logica.excepciones.LogicaExcepcion
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.AfiliadoARegistrarModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HelperRegistroAfilado constructor(
    private val afiliadoDao: AfiliadoDao,
    private val afiliadoARegistrarModel: AfiliadoARegistrarModel,
    private val afiliadoCorreoDao: Afiliado_Correo_Dao,
    private val afiliado_Jac_EstadoAfiliacionDao : Afiliado_Jac_EstadoAfiliacionDao,
    private val correoDao: CorreoDao,
    private val escuchadorExcepciones: MutableLiveData<LogicaExcepcion?>,
    private val escuchadorRegistroAfiliadoExitoso: MutableLiveData<Boolean?>,
) {

    //region variables
    private val helperAfiliadoEntity = HelperAfiliadoEntity(
        afiliadoARegistrarModel = afiliadoARegistrarModel,
        afiliadoDao = afiliadoDao,
        afiliado_Jac_EstadoAfiliacionDao = afiliado_Jac_EstadoAfiliacionDao
    )

    private val helperCorreoAfiliadoEntity = HelperCorreoAfiliadoEntity(
        afiliadoDao = afiliadoDao,
        afiliadoARegistrarModel = afiliadoARegistrarModel,
        afiliadoCorreoDao = afiliadoCorreoDao,
        correoDao = correoDao
    )
    //endregion


    fun registrar() {
        GlobalScope.launch {
            escuchadorRegistroAfiliadoExitoso.postValue(null)
            helperAfiliadoEntity.guardarAfiliado()
            helperCorreoAfiliadoEntity.guardarCorreo()
            delay(5000)
            escuchadorRegistroAfiliadoExitoso.postValue(true)
        }
    }
}