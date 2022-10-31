package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.helper

import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.RadioButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.CrearActaViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.adapters.ListaAfiliadosAsistenciaAdapter
import com.hefesto.juntasaccioncomunal.interfaceUsuario.utilidades.ArrayAdapterPersonalizado
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.AfiliadoActaAsistenciaModel

class HelperFiltroBusquedaAfiliadoActaAsistencia {

    //region variables
    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private lateinit var listaAfiliados: List<AfiliadoActaAsistenciaModel>
    private lateinit var adapterAutocompleteText: ArrayAdapter<AfiliadoActaAsistenciaModel>
    private lateinit var radioButtonDocumento : RadioButton
    private lateinit var radioButtonNombres : RadioButton
    private lateinit var recyclerview: RecyclerView
    private lateinit var adapterRecyclerView: ListaAfiliadosAsistenciaAdapter
    private lateinit var crearActaViewModel: CrearActaViewModel
    private val listaAfiliadosEnFiltro = emptyList<AfiliadoActaAsistenciaModel>().toMutableList()
    private val listaAfiliadosEnRecycler = emptyList<AfiliadoActaAsistenciaModel>().toMutableList()
    private var filtro = Filtro.NOMBRE_APELLIDOS
    //endregion

    fun conAutocompleteTextview(autoCompleteTextView: AutoCompleteTextView) : HelperFiltroBusquedaAfiliadoActaAsistencia{
        this.autoCompleteTextView = autoCompleteTextView
        return this
    }

    fun conListaAfiliados(listaAfiliados: List<AfiliadoActaAsistenciaModel>) : HelperFiltroBusquedaAfiliadoActaAsistencia {
        this.listaAfiliados = listaAfiliados

        for(item in listaAfiliados) {
            val listaFiltrada = listaAfiliadosEnFiltro.filter { return@filter it.afiliadoId == item.afiliadoId  }
            if (listaFiltrada.isNotEmpty()) continue
            listaAfiliadosEnFiltro.add(item)
        }
        return this
    }

    fun conRadioButtonDocumento(radioButtonDocumento : RadioButton) : HelperFiltroBusquedaAfiliadoActaAsistencia {
        this.radioButtonDocumento = radioButtonDocumento
        return this
    }

    fun conRadioButtonNombres(radioButtonNombres : RadioButton) : HelperFiltroBusquedaAfiliadoActaAsistencia {
        this.radioButtonNombres = radioButtonNombres
        return this
    }

    fun conCrearActaViewModel(crearActaViewModel: CrearActaViewModel) : HelperFiltroBusquedaAfiliadoActaAsistencia {
        this.crearActaViewModel = crearActaViewModel
        return this
    }

    fun conRecyclerView(recyclerView: RecyclerView) : HelperFiltroBusquedaAfiliadoActaAsistencia {
        this.recyclerview = recyclerView
        return this
    }

    fun cargarAutocomplete() {
        configurarRadiogroup()
        configurarAutocomplete()
        configurarRecyclerView()
    }

    //region metodos privados

    //region configurar radiobuttons

    private fun configurarRadiogroup() {
        revisarRadiobuttonSeleccionadoPorDefecto()
        configurarRadiobuttonNombres()
        configurarRadiobuttonNumeroDocumento()
    }

    private fun revisarRadiobuttonSeleccionadoPorDefecto() {
        if (radioButtonDocumento.isChecked) {
            filtro =  Filtro.DOCUMENTO
            return
        }
        filtro = Filtro.NOMBRE_APELLIDOS
    }

    private fun configurarRadiobuttonNombres() {
        radioButtonNombres.setOnCheckedChangeListener {
            _, checked ->
            autoCompleteTextView.setText("")
            filtro = if (checked) Filtro.NOMBRE_APELLIDOS else Filtro.DOCUMENTO
        }
    }

    private fun configurarRadiobuttonNumeroDocumento() {
        radioButtonDocumento.setOnCheckedChangeListener {
                _, checked ->
            autoCompleteTextView.setText("")
            filtro = if (checked) Filtro.DOCUMENTO else Filtro.NOMBRE_APELLIDOS
        }
    }
    //endregion

    //region configurar Autocomplete
    private fun configurarAutocomplete() {
        adapterAutocompleteText = ArrayAdapterPersonalizado(
            contextLocal = autoCompleteTextView.context,
            vistaFuenteId = android.R.layout.select_dialog_item,
            textViewAMostrarId = android.R.id.text1,
            listaElementos = listaAfiliadosEnFiltro,
            textoAMostrar = ::textoAMostrar,
            filtro = {
                item, string ->
                val stringValidado = string?:return@ArrayAdapterPersonalizado false
                if (filtro == Filtro.DOCUMENTO)  return@ArrayAdapterPersonalizado (item.numeroDocumento?:"").lowercase().startsWith(stringValidado)
                return@ArrayAdapterPersonalizado "${item.nombres?:""} ${item.apellidos?:""}".lowercase().startsWith(stringValidado)
            }
        )
        autoCompleteTextView.setAdapter(adapterAutocompleteText)
        autoCompleteTextView.setOnItemClickListener {
            adapterView, _, position, _ ->
            autoCompleteTextView.setText("")
            val item : AfiliadoActaAsistenciaModel = adapterView.getItemAtPosition(position) as AfiliadoActaAsistenciaModel
            adicionarElementoARecycler(itemAAdicionar = item)
            generarNuevoListaParaFiltro()

            configurarAutocomplete()
            configurarRecyclerView()
        }
    }

    private fun textoAMostrar(item : AfiliadoActaAsistenciaModel?) : String {
        return "${item?.nombres?:""} ${item?.apellidos?:""}"
    }

    private fun adicionarElementoARecycler(itemAAdicionar : AfiliadoActaAsistenciaModel) {
        val listaRecycler = listaAfiliadosEnRecycler.filter { return@filter it.afiliadoId == itemAAdicionar.afiliadoId }
        if (listaRecycler.isNotEmpty()) return
        listaAfiliadosEnRecycler.add(itemAAdicionar)
    }

    private fun generarNuevoListaParaFiltro() {
        listaAfiliadosEnFiltro.clear()
        for (item in listaAfiliados) {
            val listaRecycler = listaAfiliadosEnRecycler.filter { return@filter it.afiliadoId == item.afiliadoId }
            if (listaRecycler.isNotEmpty()) continue
            listaAfiliadosEnFiltro.add(item)
        }
    }

    //endregion

    //region configurar recyclerView

    private fun configurarRecyclerView() {
        adapterRecyclerView = ListaAfiliadosAsistenciaAdapter(
            listaAfiliados = listaAfiliadosEnRecycler,
            afiliadoSeleccionado = ::accionAfiliadoSeleccionadoRecyclerView
        )
        recyclerview.layoutManager = LinearLayoutManager(recyclerview.context)
        recyclerview.setHasFixedSize(true)
        recyclerview.adapter = adapterRecyclerView
        crearActaViewModel.listaAfiliadosAsistieron = listaAfiliadosEnRecycler
    }

    private fun accionAfiliadoSeleccionadoRecyclerView(seleccionado : AfiliadoActaAsistenciaModel) {
        listaAfiliadosEnRecycler.remove(seleccionado)
        listaAfiliadosEnFiltro.clear()
        for (elemento in listaAfiliados) {
            val listaSeleccionados = listaAfiliadosEnRecycler.filter { return@filter it.afiliadoId == elemento.afiliadoId }
            if (listaSeleccionados.isNotEmpty()) continue
            listaAfiliadosEnFiltro.add(elemento)
        }
        configurarAutocomplete()
        configurarRecyclerView()
    }

    //endregion

    //endregion

    private enum class Filtro {
        NOMBRE_APELLIDOS,
        DOCUMENTO,
    }
}