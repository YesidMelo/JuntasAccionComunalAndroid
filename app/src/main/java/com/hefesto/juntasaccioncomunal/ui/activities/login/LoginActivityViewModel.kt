package com.hefesto.juntasaccioncomunal.ui.activities.login

import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.login.ui.LoginActivityUI
import com.hefesto.juntasaccioncomunal.ui.base.BaseViewModel
import javax.inject.Inject

class LoginActivityViewModel constructor(
    @JvmField @Inject var loginActivityUI: LoginActivityUI
) : BaseViewModel() {

    override fun traerBaseUI(): BaseUI = loginActivityUI

}