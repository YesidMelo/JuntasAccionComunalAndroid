package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.listaAfiliadosModificacionDirectivas

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.hefesto.juntasaccioncomunal.R
import com.hefesto.juntasaccioncomunal.databinding.FragmentListaAfiliadosModificacionDirectivasBinding
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.configuracionAfiliadoEnDirectiva.ConfiguracionAfiliadoEnDirectivaFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.listaAfiliadosModificacionDirectivas.helpers.HelperRecyclerListaAfiliadosModificacionDirectiva
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.AccionesNavGrap
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores.NodosNavegacionFragments
import com.hefesto.juntasaccioncomunal.logica.modelos.home.AfiliadoParaModificacionDirectivaModel
import javax.inject.Inject

class ListaAfiliadosModificacionDirectivasFragment : BaseFragment<ListaAfiliadosModificacionDirectivasFragmentViewModel>() {

    //region variables
    @Inject
    lateinit var listaAfiliadosFragmentViewModel: ListaAfiliadosModificacionDirectivasFragmentViewModel
    @Inject
    lateinit var helperRecyclerListaAfiliadosModificacionDirectiva : HelperRecyclerListaAfiliadosModificacionDirectiva
    lateinit var binding: FragmentListaAfiliadosModificacionDirectivasBinding
    private var filtro : HelperRecyclerListaAfiliadosModificacionDirectiva.Filtro = HelperRecyclerListaAfiliadosModificacionDirectiva.Filtro.NOMBRES
    //endregion


    override fun traerViewModel(): ListaAfiliadosModificacionDirectivasFragmentViewModel = listaAfiliadosFragmentViewModel
    override fun traerNodoNavegacion(): NodosNavegacionFragments = NodosNavegacionFragments.LISTA_AFILIADOS_MODIFICACION_DIRECTIVAS

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListaAfiliadosModificacionDirectivasBinding.inflate(inflater)
        configurarBotonAtras()
        configurarEdittext()
        configurarRadiobuttons()
        precargarElementos()
        return binding.root
    }

    //region metodos privados
    private fun configurarBotonAtras() {
        conEscuchadorAccionBotonAtras {
            navegacionAplicacion.navegar(
                a = NodosNavegacionFragments.PANEL_CONTROL,
                accion =AccionesNavGrap.LISTA_AFILIADOS_MODIFICACION_DIRECTIVA_A_PANEL_CONTROL,
                de = NodosNavegacionFragments.LISTA_AFILIADOS_MODIFICACION_DIRECTIVAS
            )
        }
    }

    private fun precargarElementos() {
        precargarListaAfiliados()
    }

    private fun precargarListaAfiliados() {
        listaAfiliadosFragmentViewModel
            .traerListaAfiliadosModificacionDirectiva()
            .observe( viewLifecycleOwner) {
                llenarRecycler(lista = it)
            }
    }

    private fun llenarRecycler(lista: List<AfiliadoParaModificacionDirectivaModel>) {
        helperRecyclerListaAfiliadosModificacionDirectiva
            .conListaAfiliados(listaAfiliados = lista)
            .conRecyclerView(recyclerView = binding.recyclerviewListaAfiliadosModificacionDirectiva)
            .conEscuchadorItemSeleccionado {
                val bundle = Bundle()
                bundle.putSerializable(ConfiguracionAfiliadoEnDirectivaFragment.DETALLE_AFILIADO_EN_DIRECTIVA, it)
                navegacionAplicacion.navegar(
                    a = NodosNavegacionFragments.CONFIGURACION_AFILIADO_EN_DIRECTIVA,
                    de = NodosNavegacionFragments.LISTA_AFILIADOS_MODIFICACION_DIRECTIVAS,
                    accion = AccionesNavGrap.LISTA_AFILIADOS_MODIFICACION_DIRECTIVA_A_CONFIGURACION_AFILIADO_EN_DIRECTIVA,
                    bundle = bundle
                )
            }
            .cargarGenerarAdapters()
    }

    private fun configurarEdittext() {
        binding
            .editTextFiltroAfiliadoModificacionDirectiva
            .addTextChangedListener {
                if(it == null || it.isNullOrEmpty()) {
                    helperRecyclerListaAfiliadosModificacionDirectiva.restaurarListaFiltrada()
                    return@addTextChangedListener
                }
                helperRecyclerListaAfiliadosModificacionDirectiva.buscar(texto = it.toString(), filtro = filtro)
            }
    }

    private fun configurarRadiobuttons() {
        binding
            .radioGroupSelectorFiltro
            .setOnCheckedChangeListener { _, radiobuttonId ->
                when(radiobuttonId) {
                    R.id.radioButton_documentoAfiliado_rolApp -> {
                        filtro = HelperRecyclerListaAfiliadosModificacionDirectiva.Filtro.DOCUMENTO
                        binding.editTextFiltroAfiliadoModificacionDirectiva.setHint(R.string.numero_documento)
                    }
                    else -> {
                        filtro = HelperRecyclerListaAfiliadosModificacionDirectiva.Filtro.NOMBRES
                        binding.editTextFiltroAfiliadoModificacionDirectiva.setHint(R.string.ingresar_nombre_afiliado)
                    }
                }
            }
    }

    //endregion

}