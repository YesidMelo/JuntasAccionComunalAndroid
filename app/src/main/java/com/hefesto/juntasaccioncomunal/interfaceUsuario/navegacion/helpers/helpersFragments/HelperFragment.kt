package com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.helpers.helpersFragments

import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseActivity
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.AccionesNavGrap
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments

class HelperFragment {

    //region variables
    private lateinit var a: NodosNavegacionFragments
    private lateinit var accion : AccionesNavGrap
    private lateinit var activity: BaseActivity<*>
    private lateinit var de: NodosNavegacionFragments
    private var idNavGraph: Int? = null
    //endregion

    fun  cona(a: NodosNavegacionFragments) : HelperFragment {
        this.a = a
        return this
    }

    fun  conaccion(accion : AccionesNavGrap) : HelperFragment {
        this.accion = accion
        return this
    }

    fun  conactivity(activity: BaseActivity<*>) : HelperFragment {
        this.activity = activity
        return this
    }

    fun  conde(de: NodosNavegacionFragments) : HelperFragment {
        this.de = de
        return this
    }

    fun  conidNavGraph(idNavGraph: Int) : HelperFragment {
        this.idNavGraph = idNavGraph
        return this
    }



    fun cambiarFragment() {
        val navGraphId = idNavGraph?:return
        val navController = activity.findNavController(navGraphId)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        //activity.setupActionBarWithNavController(navController = navController, appBarConfiguration)

        navController.navigate(accion.traerIdAccion())
    }

    fun reportarNavegacion() = true

}