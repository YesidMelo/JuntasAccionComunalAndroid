package com.hefesto.juntasaccioncomunal.ui.activities.login

import android.os.Bundle
import com.hefesto.juntasaccioncomunal.databinding.ActivityLoginBinding
import com.hefesto.juntasaccioncomunal.ui.base.BaseActivity
import com.hefesto.juntasaccioncomunal.ui.navegacion.NodosNavegacionActividades
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
    override fun getViewModel(): LoginActivityViewModel = viewModelActivity
    override fun traerNodoNavegacion(): NodosNavegacionActividades = NodosNavegacionActividades.LOGIN_ACTIVITY
    override fun safeOnCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.safeOnCreate(savedInstanceState)
    }
    //endregion
}