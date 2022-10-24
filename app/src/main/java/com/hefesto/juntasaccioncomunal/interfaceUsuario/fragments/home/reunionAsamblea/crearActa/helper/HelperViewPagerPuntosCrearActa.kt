package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.helper

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.CrearActaViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.adapters.ViewPagerPuntosReunionAdapter
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.subfragment.PuntosSubfragment

class HelperViewPagerPuntosCrearActa {

    //region variables
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var adapter : ViewPagerPuntosReunionAdapter
    private lateinit var puntosSubfragment: PuntosSubfragment
    private lateinit var crearActaViewModel: CrearActaViewModel
    //endregion

    fun conDetallePuntoSubfragment(puntoSubfragment: PuntosSubfragment): HelperViewPagerPuntosCrearActa {
        this.puntosSubfragment = puntoSubfragment
        return this
    }

    fun conCrearActaViewModel(crearActaViewModel: CrearActaViewModel) : HelperViewPagerPuntosCrearActa {
        this.crearActaViewModel = crearActaViewModel
        return this
    }

    fun conViewPager(viewPager: ViewPager2) : HelperViewPagerPuntosCrearActa {
        this.viewPager = viewPager
        return this
    }

    fun conTabLayout(tabLayout: TabLayout): HelperViewPagerPuntosCrearActa {
        this.tabLayout = tabLayout
        return this
    }


    fun cargarPaginas() {
        configurarViewPager()
    }

    //region metodos privados
    private fun configurarViewPager() {
        viewPager.post {
            adapter = ViewPagerPuntosReunionAdapter(
                fragment = puntosSubfragment,
                listaPuntos = crearActaViewModel.traerDetalleReunionLiveData().value?.listaPuntos?: emptyList(),
                crearActaViewModel = crearActaViewModel
            )
            viewPager.adapter = adapter
        }
    }
    //endregion

}