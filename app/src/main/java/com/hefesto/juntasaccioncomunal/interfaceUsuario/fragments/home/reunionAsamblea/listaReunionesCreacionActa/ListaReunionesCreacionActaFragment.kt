package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.listaReunionesCreacionActa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.databinding.FragmentListareunionesCreacionactaBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.listaReunionesCreacionActa.helpers.HelperRecyclerViewListaReunionesCrearActa
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import javax.inject.Inject

class ListaReunionesCreacionActaFragment :  BaseFragment<ListaReunionesCreacionActaViewModel>() {

    //region variables
    @Inject
    lateinit var listaReunionesCreacionActaViewModel : ListaReunionesCreacionActaViewModel
    @Inject
    lateinit var helperRecyclerViewListaReunionesCrearActa: HelperRecyclerViewListaReunionesCrearActa

    private lateinit var binding : FragmentListareunionesCreacionactaBinding
    //endregion

    override fun traerViewModel(): ListaReunionesCreacionActaViewModel = listaReunionesCreacionActaViewModel

    override fun traerNodoNavegacion(): NodosNavegacionFragments = NodosNavegacionFragments.CREAR_ACTA_REUNION_ASAMBLEA

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListareunionesCreacionactaBinding.inflate(inflater)
        configurarBotones()
        precargarVista()
        return binding.root
    }


    //region metodos privados

    //region botones
    private fun configurarBotones() {
        configurarBotonAtras()
    }

    private fun configurarBotonAtras() {
        conEscuchadorAccionBotonAtras { navegarAtras() }
    }
    //endregion

    //region precarga
    private fun precargarVista() {
        precargarLiveData()
        ejecutarConsultaPrecarga()
    }

    //region liveData

    private fun precargarLiveData() {
        precargarLoadingLiveData()
        precargarListaReunionesLiveData()
    }

    private fun precargarLoadingLiveData() {
        traerViewModel()
            .traerHaCargadoLiveData()
            .observe(viewLifecycleOwner) {
                if (!it) {
                    mostrarLoading()
                    return@observe
                }
                ocultarLoading()
            }
    }

    private fun precargarListaReunionesLiveData() {
        traerViewModel()
            .traerListaReunionesCrearActaLiveData()
            .observe(viewLifecycleOwner) {
                helperRecyclerViewListaReunionesCrearActa
                    .conRecycler(recyclerView = binding.recyclerviewListaReunionesAgendadas)
                    .conEscuchadorReunionSeleccionada {  }
                    .conListaReuniones(listaReuniones = it)
                    .llenarRecycler()
            }
    }

    //endregion

    //region consultas de precarga

    private fun ejecutarConsultaPrecarga() {
        traerViewModel().cargarListaReunionesParaCreacionActas()
    }

    //endregion

    //endregion

    //endregion

}