package com.hefesto.juntasaccioncomunal.interfaceUsuario.dialogo

import android.app.DatePickerDialog
import android.widget.DatePicker
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseActivity
import java.util.*

class DialogoCalendario: DatePickerDialog.OnDateSetListener {

    //region variables
    private lateinit var activity: BaseActivity<*>
    private lateinit var calendar: Calendar
    private lateinit var datePickerDialog: DatePickerDialog

    private var calendarFechaMaximaSeleccion: Calendar? = null
    private var calendarFechaMinimaSeleccion: Calendar? = null
    private var accionAceptar : ((Date)->Unit)? = null
    //endregion

    private fun inicializar() {
        datePickerDialog = DatePickerDialog(
            activity,
            this,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        configurarFechaMaxima()
        configurarFechaMinima()
        datePickerDialog.show()
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        instancia = null
        val calendarRespuesta = Calendar.getInstance()
        calendarRespuesta.set(Calendar.YEAR, p1)
        calendarRespuesta.set(Calendar.MONTH, p2)
        calendarRespuesta.set(Calendar.DAY_OF_MONTH, p3)
        accionAceptar?.invoke(calendarRespuesta.time)
    }

    private fun configurarFechaMaxima() {
        if(calendarFechaMaximaSeleccion == null) return
        datePickerDialog.datePicker.maxDate = calendarFechaMaximaSeleccion!!.time.time
    }

    private fun configurarFechaMinima() {
        if(calendarFechaMinimaSeleccion == null) return
        datePickerDialog.datePicker.minDate = calendarFechaMinimaSeleccion!!.time.time
    }

    companion object {

        private var instancia : DialogoCalendario? = null

        fun mostrarDialogo(
            activity: BaseActivity<*>,
            calendar: Calendar,
            accionFechaSeleccionada : ((Date)->Unit)? = null,
            calendarFechaMaximaSeleccion: Calendar? = null,
            calendarFechaMinimaSeleccion: Calendar? = null
        )
        {
            if(instancia != null) return
            instancia = DialogoCalendario()
            instancia?.activity = activity
            instancia?.accionAceptar = accionFechaSeleccionada
            instancia?.calendar = calendar
            instancia?.calendarFechaMaximaSeleccion = calendarFechaMaximaSeleccion
            instancia?.calendarFechaMinimaSeleccion= calendarFechaMinimaSeleccion
            instancia?.inicializar()
        }

    }

}