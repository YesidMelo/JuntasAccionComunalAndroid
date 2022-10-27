package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.listaReunionesCreacionActa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.databinding.FragmentListareunionesCreacionactaBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.CrearActaFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.listaReunionesCreacionActa.helpers.HelperRecyclerViewListaReunionesCrearActa
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.ReunionAsambleaCreacionActaModel
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
                if(it.isEmpty()) {
                    binding.textViewListaActasPendientesNoTieneActasPendientes.visibility = View.VISIBLE
                    binding.recyclerviewListaReunionesAgendadas.visibility = View.GONE
                    return@observe
                }

                binding.textViewListaActasPendientesNoTieneActasPendientes.visibility = View.GONE
                binding.recyclerviewListaReunionesAgendadas.visibility = View.VISIBLE

                helperRecyclerViewListaReunionesCrearActa
                    .conRecycler(recyclerView = binding.recyclerviewListaReunionesAgendadas)
                    .conEscuchadorReunionSeleccionada(::navegarACreacionActa)
                    .conListaReuniones(listaReuniones = it)
                    .llenarRecycler()
            }
    }

    private fun navegarACreacionActa(reunion: ReunionAsambleaCreacionActaModel) {
        val bundle = Bundle()
        bundle.putSerializable(CrearActaFragment.REUNION_PENDIENTE_ACTA, reunion)
        navegacionAplicacion.navegarBeginTransaction(a = NodosNavegacionFragments.CREAR_ACTA_REUNION_ASAMBLEA, bundle = bundle)
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