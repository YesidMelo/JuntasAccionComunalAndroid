package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.CrearActaViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.subfragment.DetallePuntoSubfragment
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.PuntoReunionParaCreacionActaModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoReunion

class ViewPagerPuntosReunionAdapter constructor(
    fragment: Fragment,
    private val listaPuntos : List<PuntoReunionParaCreacionActaModel>,
    private val crearActaViewModel: CrearActaViewModel,
    private val tipoReunion: TipoReunion?
) : FragmentStateAdapter(fragment) {

    //region variables
    var listaFragments = emptyList<DetallePuntoSubfragment>().toMutableList()
    //endregion

    init {
        inicializarFragments()
    }

    fun inicializarFragments() {
        listaFragments.clear()
        for(contador in listaPuntos.indices) {
            val fragment = DetallePuntoSubfragment()
            fragment.crearActaViewModel = crearActaViewModel
            fragment.puntoNo = contador + 1
            fragment.tipoReunion = tipoReunion
            fragment.puntoReunionParaCreacionActaModel = listaPuntos[contador]
            listaFragments.add(fragment)
        }
    }

    override fun getItemCount(): Int = listaFragments.size

    override fun createFragment(position: Int): Fragment {
        return listaFragments[position]
    }
}