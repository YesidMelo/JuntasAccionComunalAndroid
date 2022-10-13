package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.helpers

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hefesto.juntasaccioncomunal.R
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.DetalleAfiliadoRegistroActualizacionFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.adapters.ViewPagerRegistroAfiliadoAdapter

class HelperDetalleAfiliadoViewPagerNavegacion {

    //region
    private lateinit var tabLayout : TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var mapFragments: Map<Fragment, Int>
    private lateinit var detalleAfiliadoRegistroActualizacionFragment: DetalleAfiliadoRegistroActualizacionFragment
    private lateinit var viewPagerRegistroAfiliadoAdapter : ViewPagerRegistroAfiliadoAdapter
    private var bundle: Bundle? = null
    //endregion

    fun conTabLayout(tabLayout: TabLayout) : HelperDetalleAfiliadoViewPagerNavegacion {
        this.tabLayout = tabLayout
        return this
    }

    fun conViewPager(viewPager: ViewPager2) : HelperDetalleAfiliadoViewPagerNavegacion {
        this.viewPager = viewPager
        return this
    }

    fun conMapaFragmentos(mapFragments: Map<Fragment, Int>) : HelperDetalleAfiliadoViewPagerNavegacion {
        this.mapFragments = mapFragments
        return this
    }

    fun conDetalleAfiliadoRegistroActializacionFragment(detalleAfiliadoRegistroActualizacionFragment: DetalleAfiliadoRegistroActualizacionFragment) : HelperDetalleAfiliadoViewPagerNavegacion {
        this.detalleAfiliadoRegistroActualizacionFragment = detalleAfiliadoRegistroActualizacionFragment
        return this
    }

    fun conBundle(bundle: Bundle?) : HelperDetalleAfiliadoViewPagerNavegacion {
        this.bundle = bundle
        return this
    }

    fun configurarPaginas() {
        configurarViewPager()
        configurarTabLayout()
    }

    //region metodos privados

    //region tablayout
    private fun configurarTabLayout() {
        TabLayoutMediator(tabLayout, viewPager){
            tab, position ->
            tab.setText(mapFragments.values.toList()[position])
        }.attach()
    }
    //endregion

    //region viewPager
    private fun configurarViewPager() {
        viewPagerRegistroAfiliadoAdapter = ViewPagerRegistroAfiliadoAdapter(
            fragment = detalleAfiliadoRegistroActualizacionFragment,
            listaPasosRegistroFragment = mapFragments.keys.toList(),
            bundle = bundle
        )
        viewPager.adapter = viewPagerRegistroAfiliadoAdapter
    }
    //endregion

    //endregion
}