package com.hefesto.juntasaccioncomunal.ui.navegacion

import com.hefesto.juntasaccioncomunal.ui.activities.home.HomeActivity
import com.hefesto.juntasaccioncomunal.ui.activities.login.LoginActivity
import com.hefesto.juntasaccioncomunal.ui.activities.splash.SplashActivity

enum class NodosNavegacionActividades (private val clase: Class<*>?) {
    CERRAR_APLICACION(clase = null),
    HOME_ACTIVITY(HomeActivity::class.java),
    LOGIN_ACTIVITY(LoginActivity::class.java),
    SPLASH_ACTIVITY(SplashActivity::class.java),
    ;

    fun traerClaseActivity() = clase
}