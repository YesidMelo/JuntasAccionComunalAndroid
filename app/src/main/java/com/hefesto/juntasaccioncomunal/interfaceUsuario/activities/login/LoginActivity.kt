package com.hefesto.juntasaccioncomunal.interfaceUsuario.activities.login

import android.os.Bundle
import com.hefesto.juntasaccioncomunal.R
import com.hefesto.juntasaccioncomunal.databinding.ActivityLoginBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseActivity
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionActividades
import javax.inject.Inject

class LoginActivity : BaseActivity<LoginActivityViewModel>() {
    //region variables

    //region inyecciones
    @Inject
    lateinit var viewModelActivity: LoginActivityViewModel
    //endregion

    //region bindings
    private lateinit var binding: ActivityLoginBinding
    //endregion

    //endregion

    //region heredados

    override fun onBackPressed() {
        super.onBackPressed()
        navegacionAplicacion.navegar(de = NodosNavegacionActividades.LOGIN_ACTIVITY, a= NodosNavegacionActividades.CERRAR_APLICACION)
    }

    override fun getViewModel(): LoginActivityViewModel = viewModelActivity
    override fun traerNodoNavegacion(): NodosNavegacionActividades = NodosNavegacionActividades.LOGIN_ACTIVITY

    override fun safeOnCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configurarNavegacionFragments(idNavGraph = R.id.nav_graph_login)
        super.safeOnCreate(savedInstanceState)
    }

    //endregion
}