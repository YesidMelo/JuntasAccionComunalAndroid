package com.hefesto.juntasaccioncomunal.interfaceUsuario.base

import android.os.Bundle
import android.util.Log
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.hefesto.juntasaccioncomunal.R
import com.hefesto.juntasaccioncomunal.di.ui.BaseActivityDagger
import com.hefesto.juntasaccioncomunal.logica.excepciones.LogicaExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.TiposExcepciones
import com.hefesto.juntasaccioncomunal.interfaceUsuario.dialogo.DialogoCalendario
import com.hefesto.juntasaccioncomunal.interfaceUsuario.dialogo.DialogoHora
import com.hefesto.juntasaccioncomunal.interfaceUsuario.dialogo.DialogoInformativo
import com.hefesto.juntasaccioncomunal.interfaceUsuario.dialogo.DialogoLoading
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.NavegacionAplicacion
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionActividades
import com.hefesto.juntasaccioncomunal.interfaceUsuario.utilidades.gestorPermisos.GestorPermisos
import com.hefesto.juntasaccioncomunal.interfaceUsuario.utilidades.gestorPermisos.PermisosAplicacionEnum
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
    @Inject
    lateinit var gestorPermisos: GestorPermisos
    //endregion
    private var lifecycleRegistry: LifecycleRegistry? = null

    var escuchadorAccionBotonAtras : (()->Unit)? = null
    private var escuchadorAccionAceptarFallo : (()->Unit)? = null
    //endregion

    //region ciclo de vida normal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gestorPermisos.conActivity(activity = this)
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        gestorPermisos.onRequestPermissionsResult(requestCode, permissions, grantResults)
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
            escuchadorAccionAceptarFallo = aceptarFallo
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

    fun funcionSeguraConPermisos(
        vararg permiso: PermisosAplicacionEnum,
        accionTieneTodosLosPermisos: ()-> Unit,
        aceptarFallo: (()-> Unit)? = null,
    ) {
        gestorPermisos.ejecutarAccionBasadoEnPermisos(
            permiso = permiso,
            accionTieneTodosLosPermisos = {
                try {
                    escuchadorAccionAceptarFallo = aceptarFallo
                    accionTieneTodosLosPermisos.invoke()
                } catch (e: LogicaExcepcion) {
                    mostrarDialogo(
                        tipoDialogo = if (e.tipoExcepcion == TiposExcepciones.GENERADO_USUARIO) DialogoInformativo.TipoDialogo.ERROR_USUARIO else DialogoInformativo.TipoDialogo.ERROR_SISTEMA,
                        titulo = e.stringResTitulo,
                        mensaje = e.stringResMensaje,
                        accionAceptar = aceptarFallo
                    )
                }
            },
            accionNoTieneTodosLosPermisos = {
                mostrarDialogo(
                    tipoDialogo = DialogoInformativo.TipoDialogo.ERROR_USUARIO,
                    titulo = R.string.permisos_requeridos,
                    mensaje = R.string.no_ha_seleccionado_todos_los_permisos_requeridos
                )
            }
        )
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

    fun mostrarProgress() { runOnUiThread { DialogoLoading.mostrarProgress(activity = this) } }

    fun ocultarProgress() { runOnUiThread { DialogoLoading.ocultarrProgress() }}

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

    fun mostrarDialogoHora(accionAceptar: (Date)->Unit) {
        DialogoHora.mostrarDialogo(
            activity = this,
            accionAceptar = accionAceptar
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
                    accionAceptar = {
                        escuchadorAccionAceptarFallo?.invoke()
                        escuchadorAccionAceptarFallo = null
                        ocultarProgress()
                    }
                )
            }
    }
    //endregion
}