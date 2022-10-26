package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.subfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.databinding.SubfragmentModificarlistaasistenciaBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.CrearActaViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.helper.HelperFiltroBusquedaAfiliadoActaAsistencia
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import javax.inject.Inject

class AsistenciaReunionFragment : BaseFragment<CrearActaViewModel>() {

    //region variables
    @Inject
    lateinit var helperFiltroBusquedaAfiliadoActaAsistencia: HelperFiltroBusquedaAfiliadoActaAsistencia
    lateinit var crearActaViewModel: CrearActaViewModel
    private lateinit var binding : SubfragmentModificarlistaasistenciaBinding
    //endregion


    override fun traerViewModel(): CrearActaViewModel = crearActaViewModel

    override fun traerNodoNavegacion(): NodosNavegacionFragments = NodosNavegacionFragments.LISTA_ASISTENCIA_REUNION

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SubfragmentModificarlistaasistenciaBinding.inflate(inflater)
        precargarVista()
        return binding.root
    }

    //region metodos privados

    //region precarga

    private fun precargarVista() {
        precargarLiveData()
        cargarDatos()
    }

    //region liveData
    private fun precargarLiveData() {
        precargarListaAfiliadosLiveData()
    }

    private fun precargarListaAfiliadosLiveData() {
        traerViewModel().cargo(value = false)
        traerViewModel()
            .traerListaAfiliadosJACLiveData()
            .observe(viewLifecycleOwner) {

                helperFiltroBusquedaAfiliadoActaAsistencia
                    .conListaAfiliados(listaAfiliados = it)
                    .conAutocompleteTextview(autoCompleteTextView = binding.autoCompleteTextViewFiltroAfiliado)
                    .conRadioButtonDocumento(radioButtonDocumento = binding.radioButtonCrearActaNumeroDocumento)
                    .conRadioButtonNombres(radioButtonNombres = binding.radioButtonCrearActeNombre)
                    .conRecyclerView(recyclerView = binding.recyclerViewListaAfiliadosAsistieron)
                    .conCrearActaViewModel(crearActaViewModel = crearActaViewModel)
                    .cargarAutocomplete()
                traerViewModel().cargo(value = true)
            }
    }
    //endregion

    //region carga datos

    private fun cargarDatos() {
        cargarListaAsistencia()
    }

    private fun cargarListaAsistencia() {
        traerViewModel().traerListaAfiliadosJAc()
    }
    //endregion

    //endregion

    //endregion
}