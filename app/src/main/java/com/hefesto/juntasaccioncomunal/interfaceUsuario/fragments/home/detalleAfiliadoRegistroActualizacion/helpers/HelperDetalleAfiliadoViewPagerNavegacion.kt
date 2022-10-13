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
    private lateinit var listaFragmentos: List<Fragment>
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

    fun conListaFragmentos(listaFragments: List<Fragment>) : HelperDetalleAfiliadoViewPagerNavegacion {
        this.listaFragmentos = listaFragments
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
            val stringRes = when(position) {
                0 -> R.string.datos_afiliado
                1 -> R.string.contacto_afiliado
                2 -> R.string.detalle_en_jac
                3 -> R.string.seguridad_afiliado
                else -> R.string.datos_afiliado
            }
            tab.setText(stringRes)
        }.attach()
    }
    //endregion

    //region viewPager
    private fun configurarViewPager() {
        viewPagerRegistroAfiliadoAdapter = ViewPagerRegistroAfiliadoAdapter(
            fragment = detalleAfiliadoRegistroActualizacionFragment,
            listaPasosRegistroFragment = listaFragmentos,
            bundle = bundle
        )
        viewPager.adapter = viewPagerRegistroAfiliadoAdapter
    }
    //endregion

    //endregion
}