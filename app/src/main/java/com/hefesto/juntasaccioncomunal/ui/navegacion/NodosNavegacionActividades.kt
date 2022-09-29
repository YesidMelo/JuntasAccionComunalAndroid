package com.hefesto.juntasaccioncomunal.ui.navegacion

import com.hefesto.juntasaccioncomunal.ui.activities.splash.SplashActivity

enum class NodosNavegacionActividades (private val clase: Class<*>) {
    SPLASH_ACTIVITY(SplashActivity::class.java),
    ;

    fun traerClaseActivity() = clase
}