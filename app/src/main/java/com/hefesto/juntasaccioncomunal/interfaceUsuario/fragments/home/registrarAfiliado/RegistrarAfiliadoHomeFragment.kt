package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.registrarAfiliado

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.hefesto.juntasaccioncomunal.R
import com.hefesto.juntasaccioncomunal.databinding.FragmentRegistrarAfiliadoHomeBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.DetalleAfiliadoRegistroActualizacionFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.registrarAfiliado.helper.HelperRecyclerViewListaAfiliadosRegistrarActualizar
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.AccionesNavGrap
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import javax.inject.Inject

class RegistrarAfiliadoHomeFragment : BaseFragment<RegistrarAfiliadoHomeViewModel>() {

    //region variables
    @Inject
    lateinit var registrarAfiliadoFragmentViewModel: RegistrarAfiliadoHomeViewModel

    @Inject
    lateinit var helperRecyclerViewListaAfiliadosRegistrarActualizar: HelperRecyclerViewListaAfiliadosRegistrarActualizar

    private lateinit var binding: FragmentRegistrarAfiliadoHomeBinding
    private var filtro = HelperRecyclerViewListaAfiliadosRegistrarActualizar.Filtro.NOMBRE
    //endregion

    override fun traerViewModel(): RegistrarAfiliadoHomeViewModel = registrarAfiliadoFragmentViewModel

    override fun traerNodoNavegacion(): NodosNavegacionFragments= NodosNavegacionFragments.REGISTRAR_AFILIADO_HOME

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrarAfiliadoHomeBinding.inflate(inflater)
        configurarBotones()
        precargarUI()
        return binding.root
    }

    //region metodos privados

    //region botones
    private fun configurarBotones() {
        conEscuchadorAccionBotonAtras { navegarAtras() }
    }

    private fun navegarAtras() {
        navegacionAplicacion
            .navegar(
                de = traerNodoNavegacion(),
                a = NodosNavegacionFragments.PANEL_CONTROL,
                accion = AccionesNavGrap.REGISTRAR_AFILIADO_HOME_A_PANEL_CONTROL
            )
    }
    //endregion

    //region precarga
    private fun precargarUI() {
        precargarListaAfiliados()
        precargarEntradaTextoFiltro()
        precargarSelectorFiltro()
    }

    private fun precargarListaAfiliados() {
        registrarAfiliadoFragmentViewModel
            .traerListaAfiliadosRegistroActualizacion()
            .observe(viewLifecycleOwner) {
                helperRecyclerViewListaAfiliadosRegistrarActualizar
                    .conListaAfiliados(listaAfiliados = it?: emptyList())
                    .conRecyclerView(recyclerView = binding.recyclerviewListaAfiliadosRegistroHome)
                    .conEscuchadorItemSeleccionado {
                        val bundle = Bundle()
                        bundle.putSerializable(DetalleAfiliadoRegistroActualizacionFragment.DETALLE_AFILIADO_ACTUALIZACION, it)
                        navegacionAplicacion
                            .navegar(
                                de = NodosNavegacionFragments.REGISTRAR_AFILIADO_HOME,
                                a = NodosNavegacionFragments.DETALLE_AFILIADO_REGISTRO_HOME,
                                accion = AccionesNavGrap.REGISTRAR_AFILIADO_HOME_A_DETALLE_AFILIADO_HOME,
                                bundle = bundle
                            )
                    }
                    .cargarLista()

            }
    }

    private fun precargarEntradaTextoFiltro() {
        binding.editTextTextPersonName.addTextChangedListener {
            helperRecyclerViewListaAfiliadosRegistrarActualizar
                .filtrar(texto = it?.toString(), filtro = filtro)
        }
    }

    private fun precargarSelectorFiltro() {
        binding.radioGroupFiltrosAfiliadosRegistroHome.setOnCheckedChangeListener {
            _, radioButtonId ->
            when(radioButtonId) {
                R.id.radioButton_registrarAfiliadoHome_nombre -> filtro = HelperRecyclerViewListaAfiliadosRegistrarActualizar.Filtro.NOMBRE
                R.id.radioButton_registrarAfiliadoHome_numeroDocumento -> filtro = HelperRecyclerViewListaAfiliadosRegistrarActualizar.Filtro.DOCUMENTO
                else -> filtro = HelperRecyclerViewListaAfiliadosRegistrarActualizar.Filtro.NOMBRE
            }
        }
    }
    //endregion

    //endregion
}