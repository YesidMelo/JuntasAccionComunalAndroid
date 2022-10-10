package com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.logica.excepciones.LogicaExcepcion
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> Flow<T?>.ManejarErrores(escuchadorErrores: MutableLiveData<LogicaExcepcion?>) : Flow<T?> = flow {
    try {
        collect{ value -> emit(value) }
    }
    catch (e: LogicaExcepcion) {
        escuchadorErrores.postValue(e)
    }
    catch (e: Exception) {
        Log.e("Error", "Ha surgido un error", e)
        escuchadorErrores.postValue(LogicaExcepcion())
    }
}