package com.hefesto.juntasaccioncomunal.ui.activities.splash

import com.hefesto.juntasaccioncomunal.ui.base.BaseActivity
import com.hefesto.juntasaccioncomunal.ui.navegacion.NodosNavegacionActividades
import javax.inject.Inject

class SplashActivity : BaseActivity<SplashActivityViewModel>() {

    //region variables
    @Inject
    lateinit var viewModelActivity : SplashActivityViewModel
    //endregion

    override fun getViewModel(): SplashActivityViewModel = viewModelActivity

    override fun traerNodoNavegacion(): NodosNavegacionActividades = NodosNavegacionActividades.SPLASH_ACTIVITY
}