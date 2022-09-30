package com.hefesto.juntasaccioncomunal.ui.base

import android.os.Bundle
import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModel
import com.hefesto.juntasaccioncomunal.di.ui.BaseFragmentDagger
import com.hefesto.juntasaccioncomunal.ui.dialogo.DialogoInformativo
import com.hefesto.juntasaccioncomunal.ui.navegacion.NavegacionAplicacion
import com.hefesto.juntasaccioncomunal.ui.navegacion.NodosNavegacionActividades
import com.hefesto.juntasaccioncomunal.ui.navegacion.NodosNavegacionFragments
import org.jetbrains.annotations.NotNull
import javax.inject.Inject

abstract class BaseFragment<T : ViewModel> : BaseFragmentDagger<T>(), LifecycleOwner {

    //region inyecciones
    @Inject
    lateinit var navegacionAplicacion : NavegacionAplicacion
    //endregion

    private var lifecycleRegistry: LifecycleRegistry? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navegacionAplicacion.conActivity(context = activity as BaseActivity<*>)
        configuracionCicloVida()
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

    fun funcionSegura(funcion : (()->Unit)) = (activity as BaseActivity<*>).funcionSegura(funcion = funcion)

    fun mostrarDialogo(
        tipoDialogo: DialogoInformativo.TipoDialogo,
        @StringRes @NotNull titulo: Int,
        @StringRes @NotNull mensaje: Int,
        accionAceptar: (()->Unit)? = null,
        accionCancelar: (()->Unit)? = null,
    ) = (activity as BaseActivity<*>).mostrarDialogo(
        accionAceptar = accionAceptar,
        accionCancelar = accionCancelar,
        tipoDialogo = tipoDialogo,
        titulo = titulo,
        mensaje = mensaje,
    )

    //region configuracion ciclo vida
    private fun configuracionCicloVida(){
        try {
            lifecycle
            lifecycleRegistry?.markState(Lifecycle.State.CREATED)
        } catch (e: Exception) {
            Log.e("Error", "ha surgido un error",e)
        }
    }
    //endregion
}