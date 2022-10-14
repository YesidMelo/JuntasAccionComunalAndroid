package com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.helpers.helpersFragments

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseActivity
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments

class HelperNavegacionFragmentConBeginTransaction {

    //region variable
    private lateinit var activity: BaseActivity<*>
    private var contenedorFragments : Int? = null
    private val colaFragmentos = emptyList<NodosNavegacionFragments>().toMutableList()
    private var nodoInicial: NodosNavegacionFragments? = null
    private var escuchadorNodoActual: ((NodosNavegacionFragments) -> Unit)? = null
    //endregion

    fun conActivity(activity: BaseActivity<*>) : HelperNavegacionFragmentConBeginTransaction {
        this.activity = activity
        return this
    }

    fun conNodoInicial(nodoInicial: NodosNavegacionFragments?) : HelperNavegacionFragmentConBeginTransaction {
        this.nodoInicial = nodoInicial
        return this
    }

    fun conContenedorFragmentos(@IdRes contenedorFragmentos: Int) :  HelperNavegacionFragmentConBeginTransaction{
        this.contenedorFragments = contenedorFragmentos
        return this
    }

    fun conEscuchadorNodoActual(escuchadorNodoActual: ((NodosNavegacionFragments) -> Unit)?) : HelperNavegacionFragmentConBeginTransaction {
        this.escuchadorNodoActual = escuchadorNodoActual
        return this
    }

    fun ir(a: NodosNavegacionFragments, bundle: Bundle? = null) {
        val contenedorId = contenedorFragments?:return
        val classFragment = a.fragment?:return

        val fragment = classFragment.newInstance() as Fragment
        fragment.arguments = bundle

        colaFragmentos.add(a)
        escuchadorNodoActual?.invoke(a)
        activity
            .supportFragmentManager
            .beginTransaction()
            .replace(contenedorId, fragment)
            .addToBackStack(a.identificador)
            .commit()
    }

    fun volver(onBackPressed: () -> Unit) {
        val contador = activity.supportFragmentManager.backStackEntryCount
        if (contador > 1) {
            activity.supportFragmentManager.popBackStackImmediate()
            colaFragmentos.removeLast()
            if (colaFragmentos.isEmpty()) return
            escuchadorNodoActual?.invoke(colaFragmentos.last())
            return
        }
        if (contador == 1) {
            onBackPressed.invoke()
            onBackPressed.invoke()
            return
        }
        onBackPressed.invoke()
    }

}