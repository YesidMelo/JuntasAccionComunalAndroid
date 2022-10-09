package com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion

import android.view.View
import androidx.annotation.IdRes
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseActivity
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.AccionesNavGrap
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionActividades
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.helpers.helpersActivity.HelperActivities
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.helpers.helpersFragments.HelperFragment
import org.jetbrains.annotations.NotNull
import javax.inject.Inject

interface NavegacionAplicacion {

    fun conActivity(activity: BaseActivity<*>) : NavegacionAplicacion

    fun conIdNavGraph(@IdRes @NotNull idNavGraph: Int) : NavegacionAplicacion

    fun traerResultadoNavegacionFragments() : Boolean

    fun notificacionCambioNodo(escuchadorNodoActual: ((NodosNavegacionFragments) -> Unit)? = null)

    fun navegar(
        de: NodosNavegacionActividades,
        a: NodosNavegacionActividades,
        listaPutExtra: List<Pair<String, Any?>> = emptyList(),
        parVistaTrancicion: Pair<String, View>? = null
    ) : NavegacionAplicacion

    fun navegar(
        a: NodosNavegacionFragments,
        accion: AccionesNavGrap,
        de: NodosNavegacionFragments
    ): NavegacionAplicacion
}

class NavegacionAplicacionImpl constructor(
    @JvmField @Inject var helperActivities: HelperActivities,
    @JvmField @Inject var helperFragment: HelperFragment,
) : NavegacionAplicacion {

    //region variables
    private lateinit var activity: BaseActivity<*>
    private var idNavGraph: Int? = null
    //endregion

    override fun conActivity(activity: BaseActivity<*>): NavegacionAplicacion {
        this.activity = activity
        return this
    }

    override fun conIdNavGraph(idNavGraph: Int): NavegacionAplicacion {
        this.idNavGraph = idNavGraph
        return this
    }

    override fun traerResultadoNavegacionFragments(): Boolean = helperFragment.reportarNavegacion()

    override fun notificacionCambioNodo(escuchadorNodoActual: ((NodosNavegacionFragments) -> Unit)? ) {
        helperFragment.conEscuchadorCambioNodo(escuchadorCambioNodo = escuchadorNodoActual)
    }


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

    override fun navegar(
        a: NodosNavegacionFragments,
        accion: AccionesNavGrap,
        de: NodosNavegacionFragments
    ): NavegacionAplicacion {
        val navGraphId = idNavGraph?: return this
        helperFragment
            .conA(a = a)
            .conAccion(accion = accion)
            .conActivity(activity = activity)
            .conDe(de = de)
            .conIdNavGraph(idNavGraph = navGraphId)
            .cambiarFragment()
        return this
    }

}