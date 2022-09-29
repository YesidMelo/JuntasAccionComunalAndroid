package com.hefesto.juntasaccioncomunal.ui.base

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModel
import com.hefesto.juntasaccioncomunal.di.ui.BaseFragmentDagger
import com.hefesto.juntasaccioncomunal.ui.navegacion.NavegacionAplicacion
import com.hefesto.juntasaccioncomunal.ui.navegacion.NodosNavegacionActividades
import com.hefesto.juntasaccioncomunal.ui.navegacion.NodosNavegacionFragments
import javax.inject.Inject

abstract class BaseFragment<T : ViewModel> : BaseFragmentDagger<T>(), LifecycleOwner {

    //region inyecciones
    @Inject
    lateinit var navegacionAplicacion : NavegacionAplicacion
    //endregion

    private var lifecycleRegistry: LifecycleRegistry? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configuracionCicloVida(savedInstanceState = savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        lifecycleRegistry?.markState(Lifecycle.State.STARTED)
    }

    override fun getLifecycle(): Lifecycle {
        if(lifecycleRegistry == null) {
            lifecycleRegistry = LifecycleRegistry(this)
        }
        return lifecycleRegistry!!
    }

    abstract fun traerNodoNavegacion(): NodosNavegacionFragments

    //region configuracion ciclo vida
    private fun configuracionCicloVida(savedInstanceState: Bundle?){
        try {
            lifecycle
            lifecycleRegistry?.markState(Lifecycle.State.CREATED)
        } catch (e: Exception) {
            Log.e("Error", "ha surgido un error",e)
        }
    }
    //endregion
}