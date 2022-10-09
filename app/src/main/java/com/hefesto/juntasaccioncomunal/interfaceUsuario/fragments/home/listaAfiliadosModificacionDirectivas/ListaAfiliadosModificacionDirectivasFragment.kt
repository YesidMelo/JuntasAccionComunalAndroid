package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.listaAfiliadosModificacionDirectivas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.databinding.FragmentListaAfiliadosModificacionDirectivasBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import javax.inject.Inject

class ListaAfiliadosModificacionDirectivasFragment : BaseFragment<ListaAfiliadosModificacionDirectivasFragmentViewModel>() {

    //region variables
    @Inject
    lateinit var listaAfiliadosFragmentViewModel: ListaAfiliadosModificacionDirectivasFragmentViewModel
    lateinit var binding: FragmentListaAfiliadosModificacionDirectivasBinding
    //endregion


    override fun traerViewModel(): ListaAfiliadosModificacionDirectivasFragmentViewModel = listaAfiliadosFragmentViewModel
    override fun traerNodoNavegacion(): NodosNavegacionFragments = NodosNavegacionFragments.LISTA_AFILIADOS_MODIFICACION_DIRECTIVAS

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListaAfiliadosModificacionDirectivasBinding.inflate(inflater)
        return binding.root
    }
}