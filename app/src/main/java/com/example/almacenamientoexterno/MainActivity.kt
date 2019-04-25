package com.example.almacenamientoexterno

import android.app.Activity
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*

class MainActivity : AppCompatActivity() {

    var mNombreArchivo : EditText ?= null
    var mContenidoArchivo : EditText ?= null
    var BtnGuardar : Button?= null
    var BtnConsultar : Button ?= null

            override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


                 mNombreArchivo = et_nombreArchivo
                mContenidoArchivo = et_contenidoArchivo
                BtnGuardar = btn_guardar
                BtnConsultar = btn_consultar


        BtnGuardar?.setOnClickListener(View.OnClickListener {

            try {

            val TarjetaSD : File ?= Environment.getExternalStorageDirectory()
            Toast.makeText(this,TarjetaSD?.path, Toast.LENGTH_SHORT).show()
            val RutaArchivi : File ?= File(TarjetaSD?.path, mNombreArchivo?.text.toString())
            val crearArchivo : OutputStreamWriter ?= OutputStreamWriter(openFileOutput(mNombreArchivo?.text.toString(), Activity.MODE_PRIVATE))
            crearArchivo?.write(mContenidoArchivo?.text.toString())
            crearArchivo?.flush()
            crearArchivo?.close()


        }catch (e : IOException){
                Toast.makeText(this,"No se pudo guardar el archivo", Toast.LENGTH_SHORT).show()
        }

        })

        BtnConsultar?.setOnClickListener(View.OnClickListener {

            try {
                val TarjetaSD : File ?= Environment.getExternalStorageDirectory()
                val RutaArchivi : File ?= File(TarjetaSD?.path, mNombreArchivo?.text.toString())

                val abrirArchivo : InputStreamReader ?= InputStreamReader(openFileInput(mNombreArchivo?.text.toString()))

                val leerArchivo : BufferedReader ?= BufferedReader(abrirArchivo)
                var leerLinea : String ?= leerArchivo?.readLine()
                var contenido : String ?= ""

                while (leerLinea != null ){
                    contenido = contenido + leerLinea + "\n"
                    leerLinea = leerArchivo?.readLine()

                }

                leerArchivo?.close()
                mContenidoArchivo?.setText(contenido)


            }catch (e : IOException){}



        })


    }

}
