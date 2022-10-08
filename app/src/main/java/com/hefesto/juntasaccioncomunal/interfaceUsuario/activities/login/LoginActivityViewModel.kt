package com.hefesto.juntasaccioncomunal.interfaceUsuario.activities.login

import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.login.ui.LoginActivityUI
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseViewModel
import javax.inject.Inject

class LoginActivityViewModel constructor(
    @JvmField @Inject var loginActivityUI: LoginActivityUI
) : BaseViewModel() {

    override fun traerBaseUI(): BaseUI = loginActivityUI

}