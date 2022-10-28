package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarActaPdf

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.databinding.FragmentGeneraractaPdfBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarActaPdf.helpers.HelperRecyclerListaActasReunionesParaPDF
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarActaPdf.subfragments.DetalleActaWebFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import javax.inject.Inject

class ListaGenerarActaPdfFragment : BaseFragment<GenerarActaPdfViewModel>() {

    //region variables
    @Inject
    lateinit var generarActaPdfViewModel: GenerarActaPdfViewModel

    @Inject
    lateinit var helperRecyclerListaActasReunionesParaPDF: HelperRecyclerListaActasReunionesParaPDF

    private lateinit var binding: FragmentGeneraractaPdfBinding
    //endregion

    override fun traerViewModel(): GenerarActaPdfViewModel = generarActaPdfViewModel

    override fun traerNodoNavegacion(): NodosNavegacionFragments = NodosNavegacionFragments.LISTA_GENERAR_ACTA_PDF

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGeneraractaPdfBinding.inflate(inflater)
        precargarVista()
        return binding.root
    }

    //region metodos privados
    private fun precargarVista() {
        configuracionBotones()
        configurarLiveData()
        cargarInformacion()
    }

    //region botones

    private fun configuracionBotones() {
        configurarBotonAtras()
    }

    private fun configurarBotonAtras() {
        conEscuchadorAccionBotonAtras { navegarAtras() }
    }
    //endregion

    //region livedata
    private fun configurarLiveData() {
        precargarHaCargadoLiveData()
        precargarListaReunionesLiveData()
    }

    private fun precargarHaCargadoLiveData() {
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
            .traerListaREunionesLiveData()
            .observe(viewLifecycleOwner) {
                helperRecyclerListaActasReunionesParaPDF
                    .conRecyclerView(recyclerView = binding.recyclerviewListasParaActa)
                    .conListaActas(listaActas = it)
                    .conEscuchadorItemSeleccionado {
                        navegacionAplicacion.navegarBeginTransaction(
                            a = NodosNavegacionFragments.DETALLE_GENERAR_ACTA_PDF,
                            bundle = Bundle().apply { putSerializable(DetalleActaWebFragment.DETALLE_ACTA, it) }
                        )
                    }
                    .cargarRecycler()
            }
    }
    //endregion

    //region carga
    private fun cargarInformacion() {
        traerViewModel().cargarListaReuniones()
    }
    //endregion

    //endregion

}