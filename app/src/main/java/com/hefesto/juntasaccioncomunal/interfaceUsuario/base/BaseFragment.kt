package com.hefesto.juntasaccioncomunal.interfaceUsuario.base

import android.os.Bundle
import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.hefesto.juntasaccioncomunal.di.ui.BaseFragmentDagger
import com.hefesto.juntasaccioncomunal.interfaceUsuario.dialogo.DialogoInformativo
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.NavegacionAplicacion
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import org.jetbrains.annotations.NotNull
import java.util.*
import javax.inject.Inject

abstract class BaseFragment<T : BaseViewModel> : BaseFragmentDagger<T>(), LifecycleOwner {

    //region variables
    lateinit var navegacionAplicacion : NavegacionAplicacion
    //endregion

    private var lifecycleRegistry: LifecycleRegistry? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navegacionAplicacion = (activity as BaseActivity<*>).navegacionAplicacion
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

    open fun navegarAtras() {
        navegacionAplicacion.volverBeginTransaction { activity?.onBackPressed() }
    }

    //region interaccion activity

    fun funcionSegura(funcion : (()->Unit), aceptarFallo: (()->Unit)? = null)
    = (activity as BaseActivity<*>).funcionSegura(funcion = funcion, aceptarFallo = aceptarFallo)

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

    fun mostrarLoading() = (activity as BaseActivity<*>).mostrarProgress()
    fun ocultarLoading() = (activity as BaseActivity<*>).ocultarProgress()

    fun mostrarDialogoCalendario(
        accionFechaSeleccionada: ((Date) -> Unit)?,
        calendarFechaSeleccionada: Calendar? = null,
        calendarFechaMaximaSeleccion: Calendar? = null,
        calendarFechaMinimaSeleccion: Calendar? = null
    ) = (activity as BaseActivity<*>).mostrarDialogoCalendario(
        accionFechaSeleccionada = accionFechaSeleccionada,
        calendarFechaSeleccionada = calendarFechaSeleccionada,
        calendarFechaMaximaSeleccion = calendarFechaMaximaSeleccion,
        calendarFechaMinimaSeleccion = calendarFechaMinimaSeleccion
    )

    fun conEscuchadorAccionBotonAtras(escuchadorAccionBotonAtras : (()->Unit)) {
        (activity as BaseActivity<*>).escuchadorAccionBotonAtras = escuchadorAccionBotonAtras
    }

    //endregion

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