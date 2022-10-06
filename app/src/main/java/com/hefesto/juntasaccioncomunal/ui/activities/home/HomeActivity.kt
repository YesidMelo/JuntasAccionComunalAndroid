package com.hefesto.juntasaccioncomunal.ui.activities.home

import com.hefesto.juntasaccioncomunal.ui.base.BaseActivity
import com.hefesto.juntasaccioncomunal.ui.navegacion.NodosNavegacionActividades
import javax.inject.Inject

class HomeActivity  :BaseActivity<HomeActivityViewModel>() {

    //region variables
    @Inject
    lateinit var homeActivityViewModel : HomeActivityViewModel
    //endregion

    override fun getViewModel(): HomeActivityViewModel = homeActivityViewModel

    override fun traerNodoNavegacion(): NodosNavegacionActividades = NodosNavegacionActividades.HOME_ACTIVITY
}