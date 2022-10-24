package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.CrearActaViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.subfragment.PuntosSubfragment

class ViewPagerFormulariosActaReunionAdapter constructor(
    fragment: Fragment,
    private val crearActaViewModel: CrearActaViewModel
) : FragmentStateAdapter(fragment) {

    private lateinit var listaFragments : MutableList<Fragment>

    init {
        inicializarLista()
    }

    private fun inicializarLista() {
        listaFragments = emptyList<Fragment>().toMutableList()
        listaFragments.add(traerDetallePuntoSubfragment())
        listaFragments.add(traerDetallePuntoSubfragment())
    }

    private fun traerDetallePuntoSubfragment() : PuntosSubfragment {
        val fragment = PuntosSubfragment()
        fragment.crearActaViewModel = crearActaViewModel
        return fragment
    }

    override fun getItemCount(): Int = listaFragments.size

    override fun createFragment(position: Int): Fragment {
        val fragmentSeleccionado = listaFragments[position]
        return fragmentSeleccionado
    }
}