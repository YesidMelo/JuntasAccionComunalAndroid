package com.hefesto.juntasaccioncomunal.ui.base

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModel
import com.hefesto.juntasaccioncomunal.di.ui.BaseActivityDagger
import com.hefesto.juntasaccioncomunal.ui.navegacion.NavegacionAplicacion
import com.hefesto.juntasaccioncomunal.ui.navegacion.NodosNavegacionActividades
import javax.inject.Inject


/**
 * documentacion lifecycle aqui : https://developer.android.com/topic/libraries/architecture/lifecycle
 */
abstract class BaseActivity<T: ViewModel> : BaseActivityDagger<T>(), LifecycleOwner {

    //region variables
    //region inyecciones
    @Inject
    lateinit var navegacionAplicacion : NavegacionAplicacion
    //endregion
    private var lifecycleRegistry: LifecycleRegistry? = null
    //endregion

    //region ciclo de vida normal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navegacionAplicacion.con(context = this)
        configuracionCicloVida(savedInstanceState = savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        lifecycleRegistry?.markState(Lifecycle.State.STARTED)
    }
    //endregion

    //region ciclo vida seguros
    open fun safeOnCreate(savedInstanceState: Bundle?) {}

    override fun getLifecycle(): Lifecycle {
        if(lifecycleRegistry == null) {
            lifecycleRegistry = LifecycleRegistry(this)
        }
        return lifecycleRegistry!!
    }
    abstract fun traerNodoNavegacion(): NodosNavegacionActividades
    //endregion

    //region metodos privados

    //region configuracion ciclo vida
    private fun configuracionCicloVida(savedInstanceState: Bundle?){
        try {
            lifecycle
            lifecycleRegistry?.markState(Lifecycle.State.CREATED)
            safeOnCreate(savedInstanceState = savedInstanceState)
        } catch (e: Exception) {
            Log.e("Error", "ha surgido un error",e)
        }
    }
    //endregion
    //endregion



}