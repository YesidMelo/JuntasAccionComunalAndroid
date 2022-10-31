package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarActaPdf.helpers

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.actasParaPDF.ReunionParaGenerarPDFModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HelperGeneradorPDFActa {

    //region variables
    private lateinit var reunionParaGenerarPDFModel: ReunionParaGenerarPDFModel
    private lateinit var mostrarLoading : ()->Unit
    private lateinit var ocultarLoading : ()->Unit
    private val creoPDFLiveData = MutableLiveData<Boolean>()
    //endregion

    fun conReunionParaGenerarPDFModel(reunionParaGenerarPDFModel: ReunionParaGenerarPDFModel) : HelperGeneradorPDFActa {
        this.reunionParaGenerarPDFModel = reunionParaGenerarPDFModel
        return this
    }

    fun conMostrarLoading(mostrarLoading : ()->Unit) : HelperGeneradorPDFActa {
        this.mostrarLoading = mostrarLoading
        return this
    }

    fun conOcultarLoading(ocultarLoading : ()->Unit) : HelperGeneradorPDFActa{
        this.ocultarLoading = ocultarLoading
        return this
    }

    fun traerCreoPDFLiveData() = creoPDFLiveData

    fun crearPDF() {
        GlobalScope.launch {
            creoPDFLiveData.postValue(false)
            mostrarLoading.invoke()
            delay(5000)
            ocultarLoading.invoke()
            creoPDFLiveData.postValue(true)
        }
    }
}