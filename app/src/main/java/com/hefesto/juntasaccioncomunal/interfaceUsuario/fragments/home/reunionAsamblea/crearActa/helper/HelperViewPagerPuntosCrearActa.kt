package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.helper

import androidx.lifecycle.MutableLiveData
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.CrearActaViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.adapters.ViewPagerPuntosReunionAdapter
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.subfragment.PuntosSubfragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HelperViewPagerPuntosCrearActa {

    //region variables
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var adapter : ViewPagerPuntosReunionAdapter
    private lateinit var puntosSubfragment: PuntosSubfragment
    private lateinit var crearActaViewModel: CrearActaViewModel
    private val cargaPaginasFinalizo = MutableLiveData<Boolean>()
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

    fun traerCargaPaginasFinalizo() = cargaPaginasFinalizo

    //region metodos privados
    private fun configurarViewPager() {
        viewPager.post {
            adapter = ViewPagerPuntosReunionAdapter(
                fragment = puntosSubfragment,
                listaPuntos = crearActaViewModel.traerDetalleReunionLiveData().value?.listaPuntos?: emptyList(),
                crearActaViewModel = crearActaViewModel,
                tipoReunion = crearActaViewModel.traerDetalleReunionLiveData().value?.tipoReunion
            )
            viewPager.adapter = adapter
            configurarTablayout()
        }
    }

    private fun configurarTablayout() {
        tabLayout.post {
            adapter.listaFragments.forEach {
                tabLayout.addTab(tabLayout.newTab())
            }
            TabLayoutMediator(tabLayout, viewPager){
                tab, position ->
                tab.setText("Punto ${adapter.listaFragments[position].puntoNo}")
            }.attach()
            inicializarPaginas()
        }
    }

    private fun inicializarPaginas() {

        GlobalScope.launch {
            delay(1000)
            for (pagina in 0 until adapter.listaFragments.size) {
                viewPager.post { viewPager.currentItem = pagina }
                delay(500)
            }
            for(pagina in adapter.listaFragments.size downTo 0) {
                viewPager.post { viewPager.currentItem = pagina }
                delay(500)
            }
            cargaPaginasFinalizo.postValue(true)
        }
    }
    //endregion

}