package com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.helpers.helpersFragments

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.constraintlayout.solver.widgets.Helper
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseActivity
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.AccionesNavGrap
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import org.jetbrains.annotations.NotNull

class HelperFragment {

    //region variables
    private lateinit var a: NodosNavegacionFragments
    private lateinit var accion : AccionesNavGrap
    private lateinit var activity: BaseActivity<*>
    private lateinit var de: NodosNavegacionFragments
    private var idNavGraph: Int? = null
    private var idNavHostFragment: Int? = null
    private var escuchadorCambioNodo : ((NodosNavegacionFragments)->Unit)? = null
    private var bundle : Bundle? = null

    private var frameLayoutContenedorFragmentosId: Int? = null
    private var fragmentInicial : NodosNavegacionFragments? = null

    private var navHostFragment: Fragment? = null
    private var navController: NavController? = null
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

    //region navegacion con supportFragmentManager
    fun conFragmentInicial(fragmentInicial : NodosNavegacionFragments?) : HelperFragment {
        this.fragmentInicial = fragmentInicial
        return this
    }

    fun conFrameLayoutContenedorFragmentosId(@IdRes frameLayoutContenedorFragmentosId: Int? ) : HelperFragment{
        this.frameLayoutContenedorFragmentosId = frameLayoutContenedorFragmentosId
        return this
    }
    //endregion

    //region navegacion con nav_graph
    fun  conIdNavGraph(idNavGraph: Int) : HelperFragment {
        this.idNavGraph = idNavGraph
        return this
    }

    fun conIdNavHostFragment(@NotNull @IdRes idNavHostFragment: Int?) : HelperFragment{
        this.idNavHostFragment = idNavHostFragment
        return this
    }
    //endregion

    fun conEscuchadorCambioNodo (escuchadorCambioNodo : ((NodosNavegacionFragments)->Unit)?) : HelperFragment {
        this.escuchadorCambioNodo = escuchadorCambioNodo
        return this
    }

    fun conBundle(bundle: Bundle?) : HelperFragment {
        this.bundle = bundle
        return this
    }

    fun reportarNavegacion() = true

    fun cargarFragmentInicial() {

    }

    fun cambiarFragment() {
        val navHostFragmentId = idNavHostFragment?: return

        if ( navHostFragment == null) {
            navHostFragment = activity.supportFragmentManager.findFragmentById(navHostFragmentId)
        }
        if(navController == null) {
            navController = navHostFragment?.findNavController()?:return
        }

        navController?.navigate(accion.traerIdAccion(), bundle)
        escuchadorCambioNodo?.invoke(a)
    }


}