package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.helper

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.CrearActaFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.CrearActaViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.adapters.ViewPagerFormulariosActaReunionAdapter

class HelperViewPagerFormulariosCompletarActas {

    //region variables
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var viewPagerFormulariosActaReunionAdapter: ViewPagerFormulariosActaReunionAdapter
    private lateinit var crearActaFragment: CrearActaFragment
    private lateinit var crearActaViewModel: CrearActaViewModel
    //endregion

    fun conCrearActaFragment(crearActaFragment: CrearActaFragment) : HelperViewPagerFormulariosCompletarActas {
        this.crearActaFragment = crearActaFragment
        return this
    }

    fun conCrearActaViewModel(crearActaViewModel: CrearActaViewModel) : HelperViewPagerFormulariosCompletarActas {
        this.crearActaViewModel = crearActaViewModel
        return this
    }

    fun conTabLayout(tabLayout: TabLayout) : HelperViewPagerFormulariosCompletarActas {
        this.tabLayout = tabLayout
        return this
    }

    fun conViewPager(viewPager: ViewPager2) : HelperViewPagerFormulariosCompletarActas {
        this.viewPager = viewPager
        return this
    }

    fun configurarPaginas() {
        configurarViewPager()
    }

    //region metodos privados
    private fun configurarViewPager() {
        viewPager.post {
            viewPagerFormulariosActaReunionAdapter = ViewPagerFormulariosActaReunionAdapter(
                fragment = crearActaFragment,
                crearActaViewModel = crearActaViewModel
            )

            viewPager.adapter = viewPagerFormulariosActaReunionAdapter
        }
    }
    //endregion
}