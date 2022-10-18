package com.hefesto.juntasaccioncomunal.interfaceUsuario.base

import android.os.Bundle
import android.util.Log
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.hefesto.juntasaccioncomunal.di.ui.BaseActivityDagger
import com.hefesto.juntasaccioncomunal.logica.excepciones.LogicaExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.TiposExcepciones
import com.hefesto.juntasaccioncomunal.interfaceUsuario.dialogo.DialogoCalendario
import com.hefesto.juntasaccioncomunal.interfaceUsuario.dialogo.DialogoInformativo
import com.hefesto.juntasaccioncomunal.interfaceUsuario.dialogo.DialogoLoading
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.NavegacionAplicacion
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionActividades
import org.jetbrains.annotations.NotNull
import java.util.*
import javax.inject.Inject


/**
 * documentacion lifecycle aqui : https://developer.android.com/topic/libraries/architecture/lifecycle
 */
abstract class BaseActivity<T: BaseViewModel> : BaseActivityDagger<T>(), LifecycleOwner {

    //region variables
    //region inyecciones
    @Inject
    lateinit var navegacionAplicacion : NavegacionAplicacion
    //endregion
    private var lifecycleRegistry: LifecycleRegistry? = null

    var escuchadorAccionBotonAtras : (()->Unit)? = null
    //endregion

    //region ciclo de vida normal



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navegacionAplicacion.conActivity(activity = this)
        configuracionCicloVida(savedInstanceState = savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        lifecycleRegistry?.markState(Lifecycle.State.STARTED)
        configurarEscuchadorExcepciones()
    }

    override fun onBackPressed() {
        navegacionAplicacion.volverBeginTransaction(onBackPressed = { super.onBackPressed() })
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

    fun funcionSegura(funcion : (()->Unit), aceptarFallo: (()->Unit)? = null) {
        try {
            funcion.invoke()
        } catch (e: LogicaExcepcion) {
            mostrarDialogo(
                tipoDialogo = if (e.tipoExcepcion == TiposExcepciones.GENERADO_USUARIO) DialogoInformativo.TipoDialogo.ERROR_USUARIO else DialogoInformativo.TipoDialogo.ERROR_SISTEMA,
                titulo = e.stringResTitulo,
                mensaje = e.stringResMensaje,
                accionAceptar = aceptarFallo
            )

        }
    }

    //region dialogos

    fun mostrarDialogo(
        tipoDialogo: DialogoInformativo.TipoDialogo,
        @StringRes @NotNull titulo: Int,
        @StringRes @NotNull mensaje: Int,
        accionAceptar: (()->Unit)? = null,
        accionCancelar: (()->Unit)? = null,
    ) {
        DialogoInformativo.mostrarDialogo(
            accionAceptar = accionAceptar,
            accionCancelar = accionCancelar,
            activity = this,
            mensaje = mensaje,
            tipoDialogo = tipoDialogo,
            titulo = titulo,
        )
    }

    fun mostrarProgress() = DialogoLoading.mostrarProgress(activity = this)

    fun ocultarProgress() = DialogoLoading.ocultarrProgress()

    fun mostrarDialogoCalendario(
        accionFechaSeleccionada: ((Date) -> Unit)?,
        calendarFechaSeleccionada: Calendar? = null,
        calendarFechaMaximaSeleccion: Calendar? = null,
        calendarFechaMinimaSeleccion: Calendar? = null
    ) {
        val calendarFinalFechaSeleccionada = calendarFechaSeleccionada?:Calendar.getInstance()
        DialogoCalendario.mostrarDialogo(
            activity = this,
            accionFechaSeleccionada = accionFechaSeleccionada,
            calendar = calendarFinalFechaSeleccionada,
            calendarFechaMaximaSeleccion = calendarFechaMaximaSeleccion,
            calendarFechaMinimaSeleccion = calendarFechaMinimaSeleccion
        )
    }

    //endregion

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

    private fun configurarEscuchadorExcepciones() {
        if(getViewModel().traerBaseUI().cargarEscuchadorExcepcionesCasoUso == null) return
        getViewModel().traerBaseUI().cargarEscuchadorExcepcionesCasoUso
            .invoke()
            .observe(this) {
                if (it == null) return@observe
                mostrarDialogo(
                    tipoDialogo = if (it.tipoExcepcion == TiposExcepciones.GENERADO_USUARIO) DialogoInformativo.TipoDialogo.ERROR_USUARIO else DialogoInformativo.TipoDialogo.ERROR_SISTEMA,
                    titulo = it.stringResTitulo,
                    mensaje = it.stringResMensaje,
                    accionAceptar = { ocultarProgress() }
                )
            }
    }
    //endregion
}