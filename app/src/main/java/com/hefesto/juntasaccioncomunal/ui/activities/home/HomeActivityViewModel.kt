package com.hefesto.juntasaccioncomunal.ui.activities.home

import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.HomeActivityUI
import com.hefesto.juntasaccioncomunal.ui.base.BaseViewModel
import javax.inject.Inject

class HomeActivityViewModel constructor(
    @JvmField @Inject var homeActivityUI : HomeActivityUI
): BaseViewModel() {

    override fun traerBaseUI(): BaseUI = homeActivityUI
}