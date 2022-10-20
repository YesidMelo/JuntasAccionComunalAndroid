package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.panelControl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hefesto.juntasaccioncomunal.databinding.FragmentPanelControlBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.panelControl.helpers.HelperFuncionalidadesReciclerview
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FuncionesRolApp
import javax.inject.Inject

class PanelControlFragment : BaseFragment<PanelControlFragmentViewModel>() {

    //region variables
    @Inject
    lateinit var panelControlFragmentViewModel: PanelControlFragmentViewModel
    @Inject
    lateinit var helperFuncionalidadesReciclerview: HelperFuncionalidadesReciclerview
    private lateinit var binding: FragmentPanelControlBinding
    //endregion

    override fun traerViewModel(): PanelControlFragmentViewModel = panelControlFragmentViewModel

    override fun traerNodoNavegacion(): NodosNavegacionFragments = NodosNavegacionFragments.PANEL_CONTROL

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPanelControlBinding.inflate(inflater)
        precargaVista()
        return binding.root
    }


    //region metodos privados
    private fun precargaVista() {
        precargarFuncionalidades()
    }

    private fun precargarFuncionalidades() {
        panelControlFragmentViewModel
            .traerFuncionalidades()
            .observe(viewLifecycleOwner) {
                helperFuncionalidadesReciclerview
                    .conListaFuncionalidades(listaFuncionalidades = it)
                    .conRecyclerView(binding.reciclerviewFunciones)
                    .conItemSeleccionado (itemSeleccionado = ::gestionarNavegacionFunciones)
                    .cargar()
            }
    }

    private fun gestionarNavegacionFunciones(funcionSeleccionada : FuncionesRolApp) {
        when(funcionSeleccionada) {
            FuncionesRolApp.ASIGNAR_ROL_DENTRO_DE_JAC -> navegacionAplicacion.navegarBeginTransaction(a = NodosNavegacionFragments.LISTA_AFILIADOS_MODIFICACION_DIRECTIVAS)
            FuncionesRolApp.REGISTRAR_AFILIADO_JAC ->  navegacionAplicacion.navegarBeginTransaction(a = NodosNavegacionFragments.REGISTRAR_AFILIADO_HOME)
            FuncionesRolApp.AGENDAR_REUNION ->  navegacionAplicacion.navegarBeginTransaction(a = NodosNavegacionFragments.AGENDAR_REUNION_ASAMBLEA)
        }
    }
    //endregion

}