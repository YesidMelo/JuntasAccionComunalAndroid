package com.hefesto.juntasaccioncomunal.ui.activities.login

import androidx.lifecycle.ViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.login.ui.LoginActivityUI
import javax.inject.Inject

class LoginActivityViewModel constructor(
    @JvmField @Inject var loginActivityUI: LoginActivityUI
) : ViewModel() {
}