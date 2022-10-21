package com.hefesto.juntasaccioncomunal.interfaceUsuario.dialogo

import android.app.TimePickerDialog
import android.util.Log
import android.widget.TimePicker
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseActivity
import java.util.*

class DialogoHora : TimePickerDialog.OnTimeSetListener {

    //region variables
    private lateinit var activity: BaseActivity<*>
    private lateinit var timePickerDialog: TimePickerDialog
    private lateinit var accionAceptar: (Date)->Unit
    private var hora: Int = 0
    private var minuto: Int = 0
    //endregion

    fun inicializar() {
        timePickerDialog = TimePickerDialog(activity, this, hora, minuto, false)
        timePickerDialog.show()
    }

    override fun onTimeSet(p0: TimePicker?, hourOfDay: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        accionAceptar.invoke(calendar.time)
        instancia = null
    }


    companion object {

        private var instancia : DialogoHora? = null

        fun mostrarDialogo(
            activity: BaseActivity<*>,
            accionAceptar: (Date)->Unit
        ) {
            if (instancia != null) return
            instancia = DialogoHora()
            instancia?.activity = activity
            instancia?.accionAceptar = accionAceptar
            instancia?.inicializar()
        }
    }
}