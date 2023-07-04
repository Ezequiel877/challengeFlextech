package com.example.challengeflexttech.utils

import android.app.DownloadManager
import android.content.pm.PackageManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import android.Manifest
import android.content.Context
import android.net.Uri
import android.util.DisplayMetrics
import android.view.WindowManager
import android.widget.Toast


fun ImageView.Load(
    url: String,
) {

    Glide.with(this.context).load(url).into(this)


}

fun AppCompatActivity.downloadPhoto(url: String) {
    // Verificar si se ha otorgado el permiso de escritura en el almacenamiento externo
    if (ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        // Si el permiso no se ha otorgado, solicitarlo al usuario
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            REQUEST_CODE_PERMISSION
        )
        return
    }

    // Crear una solicitud de descarga utilizando la URL de la foto
    val request = DownloadManager.Request(Uri.parse(url))
        .setTitle("Photo Download") // Título de la descarga
        .setDescription("Downloading photo...") // Descripción de la descarga
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        .setAllowedOverMetered(true) // Permitir descargas en redes móviles
        .setAllowedOverRoaming(true) // Permitir descargas en roaming

    // Obtener el servicio de descarga del sistema
    val downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

    // Enviar la solicitud de descarga y obtener el ID de la descarga
    val downloadId = downloadManager.enqueue(request)

    // Mostrar un mensaje al usuario indicando que la descarga ha comenzado
    Toast.makeText(this, "Photo download started", Toast.LENGTH_SHORT).show()
}

// Constante para identificar el código de solicitud de permiso
private const val REQUEST_CODE_PERMISSION = 1

fun ImageView.expandToFullScreen() {
    // Obtener el tamaño de la pantalla
    val displayMetrics = DisplayMetrics()
    (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getMetrics(displayMetrics)
    val screenWidth = displayMetrics.widthPixels
    val screenHeight = displayMetrics.heightPixels

    // Establecer los parámetros de diseño de la imagen para que ocupe toda la pantalla
    layoutParams.width = screenWidth
    layoutParams.height = screenHeight
    scaleType = ImageView.ScaleType.CENTER_CROP

    // Actualizar la vista con los nuevos parámetros
    requestLayout()
}