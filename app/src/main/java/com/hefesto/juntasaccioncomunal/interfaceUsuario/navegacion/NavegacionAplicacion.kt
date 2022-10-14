package com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseActivity
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionActividades
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.helpers.helpersActivity.HelperActivities
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.helpers.helpersFragments.HelperNavegacionFragmentConBeginTransaction
import javax.inject.Inject

interface NavegacionAplicacion {

    fun conActivity(activity: BaseActivity<*>) : NavegacionAplicacion

    fun notificacionCambioNodo(escuchadorNodoActual: ((NodosNavegacionFragments) -> Unit)? = null)

    fun cargarFragmentIncial()

    fun navegar(
        de: NodosNavegacionActividades,
        a: NodosNavegacionActividades,
        listaPutExtra: List<Pair<String, Any?>> = emptyList(),
        parVistaTrancicion: Pair<String, View>? = null
    ) : NavegacionAplicacion



    //region navegacion  begin transaction
    fun conFragmentInicial(fragmentInicial : NodosNavegacionFragments?) : NavegacionAplicacion

    fun conFrameLayoutContenedorFragmentosId(@IdRes frameLayoutContenedorFragmentosId: Int? ) : NavegacionAplicacion

    fun navegarBeginTransaction(a: NodosNavegacionFragments, bundle: Bundle? = null)

    fun volverBeginTransaction(onBackPressed: ()->Unit)
    //endregion
}

class NavegacionAplicacionImpl constructor(
    @JvmField @Inject var helperActivities: HelperActivities,
    @JvmField @Inject var helperNavegacionFragmentConBeginTransaction: HelperNavegacionFragmentConBeginTransaction
) : NavegacionAplicacion {

    //region variables
    private lateinit var activity: BaseActivity<*>

    private var fragmentInicial: NodosNavegacionFragments? = null
    private var frameLayoutContenedorFragmentosId: Int? = null

    //endregion

    override fun conActivity(activity: BaseActivity<*>): NavegacionAplicacion {
        this.activity = activity
        return this
    }

    override fun notificacionCambioNodo(escuchadorNodoActual: ((NodosNavegacionFragments) -> Unit)? ) {
        helperNavegacionFragmentConBeginTransaction
            .conEscuchadorNodoActual(escuchadorNodoActual)
    }

    //region navegacion beginTransaction
    override fun conFragmentInicial(fragmentInicial: NodosNavegacionFragments?): NavegacionAplicacion {
        this.fragmentInicial = fragmentInicial
        return this
    }

    override fun conFrameLayoutContenedorFragmentosId(frameLayoutContenedorFragmentosId: Int? ) : NavegacionAplicacion {
        this.frameLayoutContenedorFragmentosId = frameLayoutContenedorFragmentosId
        return this
    }

    override fun cargarFragmentIncial() {
        val nodoInicial = fragmentInicial?:return
        val frameLayoutId = frameLayoutContenedorFragmentosId?:return
        helperNavegacionFragmentConBeginTransaction
            .conActivity(activity = activity)
            .conContenedorFragmentos(contenedorFragmentos = frameLayoutId)
            .conNodoInicial(nodoInicial = nodoInicial)
            .ir(a = nodoInicial)
    }

    override fun navegarBeginTransaction(a: NodosNavegacionFragments, bundle: Bundle?) {
        helperNavegacionFragmentConBeginTransaction
            .ir(a = a, bundle = bundle)
    }

    override fun volverBeginTransaction(onBackPressed: () -> Unit) {
        helperNavegacionFragmentConBeginTransaction.volver(onBackPressed = onBackPressed)
    }

    //endregion


    override fun navegar(
        de: NodosNavegacionActividades,
        a: NodosNavegacionActividades,
        listaPutExtra: List<Pair<String, Any?>>,
        parVistaTrancicion: Pair<String, View>?
    ) : NavegacionAplicacion {
        if(de.traerClaseActivity() != activity.javaClass) return this
        helperActivities
            .conA(a = a)
            .conActivity(activity = activity)
            .conDe(de = de)
            .conListaPutExtra(listaPutExtra = listaPutExtra)
            .conParVistaTrancicion(parVistaTrancicion = parVistaTrancicion)
            .crearIntent()
            .crearPutExtras()
            .crearActivityOptionsCompat()
            .navegarAActivity()
        return this
    }

}