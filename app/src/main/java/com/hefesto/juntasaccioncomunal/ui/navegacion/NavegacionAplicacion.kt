package com.hefesto.juntasaccioncomunal.ui.navegacion

import android.view.View
import com.hefesto.juntasaccioncomunal.ui.base.BaseActivity

class NavegacionAplicacion {

    //region variables
    private lateinit var activity: BaseActivity<*>
    //endregion

    fun con(context: BaseActivity<*>) {
        this.activity = context
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

    fun navegar(de: NodosNavegacionFragments, a: NodosNavegacionFragments) {}

}