package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.helpers

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hefesto.juntasaccioncomunal.R
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.DetalleAfiliadoRegistroActualizacionFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.adapters.ViewPagerRegistroAfiliadoAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HelperDetalleAfiliadoViewPagerNavegacion {

    //region
    private lateinit var tabLayout : TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var mapFragments: Map<Fragment, Int>
    private lateinit var detalleAfiliadoRegistroActualizacionFragment: DetalleAfiliadoRegistroActualizacionFragment
    private lateinit var viewPagerRegistroAfiliadoAdapter : ViewPagerRegistroAfiliadoAdapter
    private var bundle: Bundle? = null
    private val finalizoLaCarga = MutableLiveData<Boolean>()
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

    fun configurarPaginas() : MutableLiveData<Boolean> {
        GlobalScope.launch {
            finalizoLaCarga.postValue(false)
            configurarViewPager()
            configurarTabLayout()
            cargarPaginas()
            finalizoLaCarga.postValue(true)
        }
        return finalizoLaCarga
    }

    //region metodos privados

    //region tablayout
    private fun configurarTabLayout() {
        viewPager.post {
            TabLayoutMediator(tabLayout, viewPager){
                    tab, position ->
                tab.setText(mapFragments.values.toList()[position])
            }.attach()
        }
    }
    //endregion

    //region viewPager
    private fun configurarViewPager() {
        viewPager.post {
            viewPagerRegistroAfiliadoAdapter = ViewPagerRegistroAfiliadoAdapter(
                fragment = detalleAfiliadoRegistroActualizacionFragment,
                listaPasosRegistroFragment = mapFragments.keys.toList(),
                bundle = bundle
            )
            viewPager.adapter = viewPagerRegistroAfiliadoAdapter
        }
    }

    private suspend fun cargarPaginas() {
        delay(5000)
        for (contador in 0 until mapFragments.size) {
            delay(1000)
            viewPager.post { viewPager.currentItem = contador }
        }
        viewPager.post { viewPager.currentItem = 0 }
    }
    //endregion

    //endregion
}