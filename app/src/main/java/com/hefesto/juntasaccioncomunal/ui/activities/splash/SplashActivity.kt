package com.hefesto.juntasaccioncomunal.ui.activities.splash

import android.os.Bundle
import android.os.Handler
import android.util.Log
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

    //region bindings
    lateinit var binding: ActivitySplashBinding
    //endregion

    //endregion

    //region heredadas
    override fun getViewModel(): SplashActivityViewModel = viewModelActivity

    override fun traerNodoNavegacion(): NodosNavegacionActividades = NodosNavegacionActividades.SPLASH_ACTIVITY

    override fun safeOnCreate(savedInstanceState: Bundle?) {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.safeOnCreate(savedInstanceState)
        ponerEscuchadoresViewModels()
    }
    //endregion

    //region metodos privados
    fun ponerEscuchadoresViewModels() {
        ponerEscuchadorPrecargaAplicacion()
    }

    fun ponerEscuchadorPrecargaAplicacion() {
        viewModelActivity.iniciarPrecarga().observe(this) {
            respuesta ->
            if (respuesta == null) return@observe
            if (!respuesta) return@observe
            navegacionAplicacion.navegar(
                de = traerNodoNavegacion(),
                a = NodosNavegacionActividades.LOGIN_ACTIVITY,
                parVistaTrancicion = Pair(first = "logoApp", binding.lottieLogoApp)
            )
            Handler().postDelayed({
                this@SplashActivity.finish()
            },5000)
        }
    }
    //endregion
}