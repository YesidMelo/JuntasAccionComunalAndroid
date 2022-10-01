package com.hefesto.juntasaccioncomunal.ui.base

import android.os.Bundle
import android.util.Log
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModel
import com.hefesto.juntasaccioncomunal.di.ui.BaseActivityDagger
import com.hefesto.juntasaccioncomunal.logica.excepciones.LogicaExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.TiposExcepciones
import com.hefesto.juntasaccioncomunal.ui.dialogo.DialogoInformativo
import com.hefesto.juntasaccioncomunal.ui.navegacion.NavegacionAplicacion
import com.hefesto.juntasaccioncomunal.ui.navegacion.NodosNavegacionActividades
import org.jetbrains.annotations.NotNull
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
    //endregion

    //region ciclo de vida normal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navegacionAplicacion.conActivity(context = this)
        configuracionCicloVida(savedInstanceState = savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        lifecycleRegistry?.markState(Lifecycle.State.STARTED)
        configurarEscuchadorExcepciones()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        navegacionAplicacion
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

    fun funcionSegura(funcion : (()->Unit)) {
        try {
            funcion.invoke()
        } catch (e: LogicaExcepcion) {
            mostrarDialogo(
                tipoDialogo = if (e.tipoExcepcion == TiposExcepciones.GENERADO_USUARIO) DialogoInformativo.TipoDialogo.ERROR_USUARIO else DialogoInformativo.TipoDialogo.ERROR_SISTEMA,
                titulo = e.stringResTitulo,
                mensaje = e.stringResMensaje,
            )
        }
    }

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
    //region navegacion fragments en activity

    open fun configurarNavegacionFragments(@IdRes @NotNull idNavGraph: Int) {
        navegacionAplicacion.conIdNavGraph(idNavGraph = idNavGraph)
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
                )
            }
    }
    //endregion
}