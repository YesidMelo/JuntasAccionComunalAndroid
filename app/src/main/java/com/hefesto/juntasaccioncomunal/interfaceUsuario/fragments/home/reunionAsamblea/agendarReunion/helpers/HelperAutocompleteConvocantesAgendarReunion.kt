package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.agendarReunion.helpers

import android.widget.AutoCompleteTextView
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.agendarReunion.adapters.ListaConvocantesAgendarCitaAdapter
import com.hefesto.juntasaccioncomunal.interfaceUsuario.utilidades.ArrayAdapterPersonalizado
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.agendarReunion.ConvocanteReunionAsambleaAAgendarModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HelperAutocompleteConvocantesAgendarReunion {

    //region variables
    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioButtonDocumento: RadioButton
    private lateinit var radioButtonNombres: RadioButton
    private lateinit var adapterAutocompleteView : ArrayAdapterPersonalizado<ConvocanteReunionAsambleaAAgendarModel>
    private lateinit var listaConvocantesAgendarCitaAdapter: ListaConvocantesAgendarCitaAdapter
    private lateinit var mostrarLoading : ()-> Unit
    private lateinit var ocultarLoading : ()-> Unit

    private var filtro = Filtro.DOCUMENTO
    private var listaPosiblesConvocantes = emptyList<ConvocanteReunionAsambleaAAgendarModel>().toMutableList()
    private var listaConvocantesEnFiltro = emptyList<ConvocanteReunionAsambleaAAgendarModel>().toMutableList()
    private var listaConvocantesEnRecycler = emptyList<ConvocanteReunionAsambleaAAgendarModel>().toMutableList()
    //endregion

    //region metodos publicos
    fun conAutocompleteView(autoCompleteTextView: AutoCompleteTextView) : HelperAutocompleteConvocantesAgendarReunion {
        this.autoCompleteTextView = autoCompleteTextView
        return this
    }

    fun conRecyclerView(recyclerView: RecyclerView) : HelperAutocompleteConvocantesAgendarReunion {
        this.recyclerView = recyclerView
        return this
    }

    fun conRadioButtonGroup(radioGroup: RadioGroup) : HelperAutocompleteConvocantesAgendarReunion {
        this.radioGroup = radioGroup
        return this
    }

    fun conRadioButtonDocumento(radioButtonDocumento: RadioButton) : HelperAutocompleteConvocantesAgendarReunion{
        this.radioButtonDocumento = radioButtonDocumento
        return this
    }

    fun conRadioButtonNombres(radioButtonNombre: RadioButton) : HelperAutocompleteConvocantesAgendarReunion {
        this.radioButtonNombres = radioButtonNombre
        return this
    }

    fun conListaConvocantesDiponibles(listaConvocantes: List<ConvocanteReunionAsambleaAAgendarModel>) : HelperAutocompleteConvocantesAgendarReunion {
        this.listaPosiblesConvocantes = listaConvocantes.toMutableList()
        this.listaConvocantesEnFiltro.addAll(listaConvocantes)
        return this
    }

    fun conMostrarLoading(mostrarLoading : ()->Unit) : HelperAutocompleteConvocantesAgendarReunion {
        this.mostrarLoading = mostrarLoading
        return this
    }

    fun conOcultarLoading(ocultarLoading: ()->Unit) : HelperAutocompleteConvocantesAgendarReunion {
        this.ocultarLoading = ocultarLoading
        return this
    }

    fun traerListaConvocantes() = listaConvocantesEnRecycler

    fun configurarAutocompleteRecycler() {
        configurarRadioButtons()
        configurarAutocompleteView()
        configurarRecyclerview()
    }

    //endregion

    //region metodos privados

    //region radiobuttons
    private fun configurarRadioButtons() {
        revisarRadiobuttonSeleccionado()
        configurarRadioButtonDocumento()
        configurarRadioButtonNombres()
    }

    private fun revisarRadiobuttonSeleccionado() {
        if (radioButtonDocumento.isChecked) {
            filtro = Filtro.DOCUMENTO
            return
        }
        filtro = Filtro.NOMBRES
    }

    private fun configurarRadioButtonDocumento() {
        radioButtonDocumento.setOnCheckedChangeListener {
            _, checked ->
            autoCompleteTextView.setText("")
            filtro = if(checked) Filtro.DOCUMENTO else Filtro.NOMBRES
        }
    }

    private fun configurarRadioButtonNombres() {
        radioButtonNombres.setOnCheckedChangeListener {
            _, checked ->
            autoCompleteTextView.setText("")
            filtro = if(checked) Filtro.NOMBRES else Filtro.DOCUMENTO
        }
    }

    //endregion

    //region autocomplete
    private fun configurarAutocompleteView() {
        adapterAutocompleteView = ArrayAdapterPersonalizado(
            contextLocal = autoCompleteTextView.context,
            vistaFuenteId = android.R.layout.select_dialog_item,
            textViewAMostrarId = android.R.id.text1,
            listaElementos = listaConvocantesEnFiltro,
            textoAMostrar = ::textoAMostrar,
            filtro = {
                item, string ->
                val stringValidado = string?:return@ArrayAdapterPersonalizado false
                if(filtro == Filtro.DOCUMENTO) return@ArrayAdapterPersonalizado (item.numeroDocumento?:"").lowercase().startsWith(stringValidado)
                return@ArrayAdapterPersonalizado "${item.nombres?:""} ${item.apellidos?:""}".lowercase().startsWith(stringValidado)
            }
        )
        autoCompleteTextView.setAdapter(adapterAutocompleteView)
        autoCompleteTextView.setOnItemClickListener {
            adapterView, _, position, _ ->
            GlobalScope.launch {
                autoCompleteTextView.post {
                    mostrarLoading.invoke()
                    autoCompleteTextView.setText("")
                }
                val item : ConvocanteReunionAsambleaAAgendarModel = adapterView.getItemAtPosition(position) as ConvocanteReunionAsambleaAAgendarModel
                adicionarElementoARecycler(itemAAdicionar = item)
                generarNuevaListaParaFiltro()

                autoCompleteTextView.post {
                    configurarAutocompleteView()
                    configurarRecyclerview()
                }

                delay(2000)
                autoCompleteTextView.post {
                    ocultarLoading.invoke()
                }
            }
        }
    }

    private fun textoAMostrar(item : ConvocanteReunionAsambleaAAgendarModel?) : String {
        return "${item?.nombres?:""} ${item?.apellidos?:""}"
    }

    private fun adicionarElementoARecycler(itemAAdicionar : ConvocanteReunionAsambleaAAgendarModel) {
        val listaRecycler = listaConvocantesEnRecycler.filter { return@filter it.afiliadoId == itemAAdicionar.afiliadoId }
        if (listaRecycler.isNotEmpty()) return
        listaConvocantesEnRecycler.add(itemAAdicionar)
    }

    private fun generarNuevaListaParaFiltro() {
        listaConvocantesEnFiltro.clear()
        for (item in listaPosiblesConvocantes) {
            val listaRecycler = listaConvocantesEnRecycler.filter { return@filter it.afiliadoId === item.afiliadoId }
            if (listaRecycler.isNotEmpty()) continue
            listaConvocantesEnFiltro.add(item)
        }
    }

    //endregion

    //region recyclerView

    private fun configurarRecyclerview() {
        listaConvocantesAgendarCitaAdapter = ListaConvocantesAgendarCitaAdapter(
            listaConvocantes = listaConvocantesEnRecycler,
            selectorItem = ::accionItemSeleccionado
        )
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = listaConvocantesAgendarCitaAdapter
    }

    private fun accionItemSeleccionado(itemSeleccionado : ConvocanteReunionAsambleaAAgendarModel) {
        mostrarLoading.invoke()

        listaConvocantesEnRecycler.remove(itemSeleccionado)
        listaConvocantesEnFiltro.clear()
        for (elemento in listaPosiblesConvocantes) {
            val listaSeleccionado =
                listaConvocantesEnRecycler.filter { return@filter it.afiliadoId == elemento.afiliadoId }
            if (listaSeleccionado.isNotEmpty()) continue
            listaConvocantesEnFiltro.add(elemento)
        }

        configurarAutocompleteView()
        configurarRecyclerview()
        ocultarLoading.invoke()

    }

    //endregion

    //endregion

    private enum class Filtro {
        DOCUMENTO,
        NOMBRES
    }
}