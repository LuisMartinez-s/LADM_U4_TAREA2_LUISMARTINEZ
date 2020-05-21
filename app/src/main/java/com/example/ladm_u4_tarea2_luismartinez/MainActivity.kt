package com.example.ladm_u4_tarea2_luismartinez

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CallLog
import android.provider.Telephony
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.READ_CALL_LOG)!=PackageManager.PERMISSION_GRANTED){
         ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.READ_CALL_LOG),1)
        }//if
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.READ_SMS)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.READ_SMS),2)
        }//if

        btnLlamadas.setOnClickListener {
            obtenerLlamadas()
        }//btnLlamadas
        btnBorrador.setOnClickListener {
            obtenerBorradores()
        }//BORRADOR
    }//onCreate

    @SuppressLint("MissingPermission")
    private fun obtenerLlamadas() {
        var llamadas=""
        var cursor=contentResolver.query(CallLog.Calls.CONTENT_URI,null,null,null,null)

        if(cursor!!.moveToFirst()){
            var numero=cursor.getColumnIndex(CallLog.Calls.NUMBER)
            var tipo=cursor.getColumnIndex(CallLog.Calls.TYPE)

            do{
                llamadas+="Numero: "+cursor.getString(numero)+"\nTipo: "+cursor.getString(tipo)+"\n\n"

            }while(cursor.moveToNext())
        }//if
        else{
            llamadas="No hay data"
        }//else
        textLlamadas.setText(llamadas)
    }//obtenerLlamadas


    private fun obtenerBorradores() {
        var borradores=""
        var cursor=contentResolver.query(Telephony.Sms.Draft.CONTENT_URI,null,null,null,null)

        if(cursor!!.moveToFirst()){
            var numero=cursor.getColumnIndex(Telephony.Sms.Draft.ADDRESS)
            var mensaje=cursor.getColumnIndex(Telephony.Sms.Draft.BODY)

            do{
                borradores+="Numero: "+cursor.getString(numero)+"\nMensaje: "+cursor.getString(mensaje)+"\n\n"

            }while(cursor.moveToNext())
        }//if
        else{
            borradores="No hay data"
        }//else
        textLlamadas.setText(borradores)
    }//obtenerLlamadas

}//Main
