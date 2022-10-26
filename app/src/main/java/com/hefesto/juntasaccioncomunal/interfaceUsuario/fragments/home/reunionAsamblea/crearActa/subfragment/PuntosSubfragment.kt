package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.subfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.databinding.SubfragmentPuntoReunionBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.CrearActaViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.helper.HelperViewPagerPuntosCrearActa
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import javax.inject.Inject

class PuntosSubfragment : BaseFragment<CrearActaViewModel>() {

    //region variables
    @Inject
    lateinit var helperViewPagerPuntosCrearActa: HelperViewPagerPuntosCrearActa

    var crearActaViewModel: CrearActaViewModel? = null
    private lateinit var binding: SubfragmentPuntoReunionBinding
    //endregion

    override fun traerViewModel(): CrearActaViewModel = crearActaViewModel!!

    override fun traerNodoNavegacion(): NodosNavegacionFragments = NodosNavegacionFragments.PUNTO_REUNION


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SubfragmentPuntoReunionBinding.inflate(inflater)
        precargarVista()
        return binding.root
    }



    //region metodos privados

    //region precargar vistas

    private fun precargarVista() {
        precargarPuntos()
        precargarLiveData()
    }

    private fun precargarPuntos() {
        traerViewModel().cargo()
        helperViewPagerPuntosCrearActa
            .conDetallePuntoSubfragment(puntoSubfragment = this)
            .conCrearActaViewModel(crearActaViewModel = traerViewModel())
            .conViewPager(viewPager = binding.viewPager2DetallePunto)
            .conTabLayout(tabLayout = binding.tablayoutPuntosReunion)
            .cargarPaginas()
    }

    private fun precargarLiveData() {
        helperViewPagerPuntosCrearActa
            .traerCargaPaginasFinalizo()
            .observe(viewLifecycleOwner) {
                traerViewModel().cargo(value = true)
            }
    }
    //endregion
    //endregion

}