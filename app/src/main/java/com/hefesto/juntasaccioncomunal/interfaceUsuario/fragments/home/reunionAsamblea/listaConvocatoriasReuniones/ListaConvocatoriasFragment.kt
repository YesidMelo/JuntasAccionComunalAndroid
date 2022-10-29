package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.listaConvocatoriasReuniones

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.databinding.FragmentListareunionesConvocatoriasBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarConvocatoriaReunionPdf.DetalleConvocatoriaFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.listaConvocatoriasReuniones.helpers.HelperRecyclerListaReunionesParaConvocatoria
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import javax.inject.Inject

class ListaConvocatoriasFragment : BaseFragment<ListaConvocatoriasViewModel>() {

    //region variables
    @Inject
    lateinit var listaConvocatoriasViewModel: ListaConvocatoriasViewModel
    @Inject
    lateinit var helperRecyclerListaReunionesParaConvocatoria: HelperRecyclerListaReunionesParaConvocatoria
    private lateinit var binding : FragmentListareunionesConvocatoriasBinding
    //endregion

    override fun traerViewModel(): ListaConvocatoriasViewModel = listaConvocatoriasViewModel

    override fun traerNodoNavegacion(): NodosNavegacionFragments = NodosNavegacionFragments.LISTA_GENERAR_CONVOCATORIA_PDF

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListareunionesConvocatoriasBinding.inflate(inflater)
        configurarBotones()
        cargarLiveData()
        cargarElementosViewModel()
        return binding.root
    }

    //region metodos privados

    //region configurar botones
    private fun configurarBotones() {
        configurarBotonAtras()
    }

    private fun configurarBotonAtras() {
        conEscuchadorAccionBotonAtras { navegarAtras() }
    }
    //endregion

    //region livedata
    private fun cargarLiveData() {
        cargarListaReunionesConvocatorias()
    }

    private fun cargarListaReunionesConvocatorias() {
        traerViewModel()
            .traerListaReunionesLiveData()
            .observe(viewLifecycleOwner) {
                helperRecyclerListaReunionesParaConvocatoria
                    .conListaReuniones(listaReuniones = it)
                    .conRecyclerView(recyclerView = binding.recyclerviewListaConvocatorias)
                    .conItemSeleccionado {
                        navegacionAplicacion.navegarBeginTransaction(
                            a = NodosNavegacionFragments.DETALLE_GENERAR_CONVOCATORIA_PDF,
                            bundle = Bundle().apply { putSerializable(DetalleConvocatoriaFragment.DETALLE_CONVOCATORIA, it) }
                        )
                    }
                    .cargarRecycler()
            }
    }
    //endregion

    //region elementos viewModel
    private fun cargarElementosViewModel() {
        traerViewModel().cargarListaReuniones()
    }
    //endregion

    //endregion
}