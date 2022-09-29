package com.hefesto.juntasaccioncomunal.ui.activities.splash

import android.os.Bundle
import com.hefesto.juntasaccioncomunal.databinding.ActivitySplashBinding
import com.hefesto.juntasaccioncomunal.ui.base.BaseActivity
import com.hefesto.juntasaccioncomunal.ui.navegacion.NodosNavegacionActividades
import javax.inject.Inject

class SplashActivity : BaseActivity<SplashActivityViewModel>() {

    //region variables

    //region inyecciones
    @Inject
    lateinit var viewModelActivity : SplashActivityViewModel
    //endregion

    //region
    lateinit var binding: ActivitySplashBinding
    //endregion

    //endregion

    override fun getViewModel(): SplashActivityViewModel = viewModelActivity

    override fun traerNodoNavegacion(): NodosNavegacionActividades = NodosNavegacionActividades.SPLASH_ACTIVITY

    override fun safeOnCreate(savedInstanceState: Bundle?) {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.safeOnCreate(savedInstanceState)
    }
}