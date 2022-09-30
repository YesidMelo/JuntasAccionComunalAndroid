package com.hefesto.juntasaccioncomunal.ui.navegacion

import android.view.View
import androidx.annotation.IdRes
import com.hefesto.juntasaccioncomunal.ui.base.BaseActivity
import org.jetbrains.annotations.NotNull

class NavegacionAplicacion {

    //region variables
    private lateinit var activity: BaseActivity<*>
    private var idNavGraph: Int = 0
    //endregion

    fun conActivity(context: BaseActivity<*>) {
        this.activity = context
    }

    fun conIdNavGraph(@IdRes @NotNull idNavGraph: Int) {
        this.idNavGraph = idNavGraph
    }


    fun navegar(
        de: NodosNavegacionActividades,
        a: NodosNavegacionActividades,
        listaPutExtra: List<Pair<String, Any?>> = emptyList(),
        parVistaTrancicion: Pair<String, View>? = null
    ) {
        if(de.traerClaseActivity() != activity.javaClass) return
        HelperActivities(
            activity =  activity,
            de = de,
            a = a,
            listaPutExtra = listaPutExtra,
            parVistaTrancicion = parVistaTrancicion
        )
            .crearIntent()
            .crearPutExtras()
            .crearActivityOptionsCompat()
            .navegarAActivity()
    }

    fun navegar(
        a: NodosNavegacionFragments,
        accion: AccionesNavGrap,
        de: NodosNavegacionFragments
    ) {
        HelperFragment(
            a = a,
            accion = accion,
            activity = activity,
            de = de,
            idNavGraph = idNavGraph
        ).cambiarFragment()
    }

}