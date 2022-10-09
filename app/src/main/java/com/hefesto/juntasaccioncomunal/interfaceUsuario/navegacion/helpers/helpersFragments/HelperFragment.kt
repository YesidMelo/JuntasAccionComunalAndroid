package com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.helpers.helpersFragments

import androidx.navigation.findNavController
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
    private var escuchadorCambioNodo : ((NodosNavegacionFragments)->Unit)? = null
    //endregion

    fun  conA(a: NodosNavegacionFragments) : HelperFragment {
        this.a = a
        return this
    }

    fun  conAccion(accion : AccionesNavGrap) : HelperFragment {
        this.accion = accion
        return this
    }

    fun  conActivity(activity: BaseActivity<*>) : HelperFragment {
        this.activity = activity
        return this
    }

    fun  conDe(de: NodosNavegacionFragments) : HelperFragment {
        this.de = de
        return this
    }

    fun  conIdNavGraph(idNavGraph: Int) : HelperFragment {
        this.idNavGraph = idNavGraph
        return this
    }

    fun conEscuchadorCambioNodo (escuchadorCambioNodo : ((NodosNavegacionFragments)->Unit)?) : HelperFragment {
        this.escuchadorCambioNodo = escuchadorCambioNodo
        return this
    }

    fun cambiarFragment() {
        val navGraphId = idNavGraph?:return
        val navController = activity.findNavController(navGraphId)
        //val appBarConfiguration = AppBarConfiguration(navController.graph)
        //activity.setupActionBarWithNavController(navController = navController, appBarConfiguration)
        navController.navigate(accion.traerIdAccion())
        escuchadorCambioNodo?.invoke(a)
    }

    fun reportarNavegacion() = true

}