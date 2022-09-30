package com.hefesto.juntasaccioncomunal.ui.navegacion

import androidx.annotation.IdRes
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.hefesto.juntasaccioncomunal.ui.base.BaseActivity

class HelperFragment constructor(
    private val a: NodosNavegacionFragments,
    private val accion : AccionesNavGrap,
    private val activity: BaseActivity<*>,
    private val de: NodosNavegacionFragments,
    @IdRes private val idNavGraph: Int
) {

    fun cambiarFragment() {
        val navController = activity.findNavController(idNavGraph)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        //activity.setupActionBarWithNavController(navController = navController, appBarConfiguration)

        navController.navigate(accion.traerIdAccion())
    }

}