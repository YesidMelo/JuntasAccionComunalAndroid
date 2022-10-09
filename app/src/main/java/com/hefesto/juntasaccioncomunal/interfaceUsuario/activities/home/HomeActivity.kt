package com.hefesto.juntasaccioncomunal.interfaceUsuario.activities.home

import android.os.Bundle
import android.view.View
import com.hefesto.juntasaccioncomunal.databinding.ActivityHomeBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseActivity
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionActividades
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import javax.inject.Inject

class HomeActivity : BaseActivity<HomeActivityViewModel>() {

    //region variables
    @Inject
    lateinit var homeActivityViewModel: HomeActivityViewModel
    lateinit var binding : ActivityHomeBinding
    //endregion

    override fun getViewModel(): HomeActivityViewModel = homeActivityViewModel

    override fun traerNodoNavegacion(): NodosNavegacionActividades = NodosNavegacionActividades.HOME_ACTIVITY

    override fun safeOnCreate(savedInstanceState: Bundle?) {

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configurarEncabezadoPanelControl(actual = NodosNavegacionFragments.PANEL_CONTROL)
        configurarEscuchadorFragmentActual()
        configurarCerrarSesion()
    }

    //region metodos privados
    private fun configurarEncabezadoPanelControl(actual: NodosNavegacionFragments) {
        configurarTitulo(actual = actual)
        configurarSubtitulo(actual = actual)
    }

    private fun configurarEscuchadorFragmentActual() {
        navegacionAplicacion.notificacionCambioNodo (::configurarEncabezadoPanelControl)
    }

    private fun configurarTitulo(actual: NodosNavegacionFragments) {
        if (actual.titulo == null) {
            binding.encabezadoHome.textViewTituloHome.visibility = View.GONE
            return
        }
        binding.encabezadoHome.textViewTituloHome.visibility = View.VISIBLE
        binding.encabezadoHome.textViewTituloHome.setText(actual.titulo!!)
    }

    private fun configurarSubtitulo(actual: NodosNavegacionFragments) {
        if (actual.subtitulo == null) {
            binding.encabezadoHome.textViewSubtituloHome.visibility = View.GONE
            return
        }
        binding.encabezadoHome.textViewSubtituloHome.visibility = View.VISIBLE
        binding.encabezadoHome.textViewSubtituloHome.setText(actual.subtitulo!!)
    }

    private fun  configurarCerrarSesion() {
        binding.encabezadoHome.lottieCerrar.setOnClickListener {
            navegacionAplicacion.navegar(
                de = NodosNavegacionActividades.HOME_ACTIVITY,
                a = NodosNavegacionActividades.LOGIN_ACTIVITY
            )
        }
    }

    //endregion
}