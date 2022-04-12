package com.example.androidapponlinevotingsystem.serverProblemActivity

import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream


class ImageConvertor {

   companion object{

       fun getBitmapAsByteArray(bitmap: Bitmap): ByteArray {
           val outputStream = ByteArrayOutputStream()
           bitmap.compress(CompressFormat.PNG, 0, outputStream)
           return outputStream.toByteArray()
       }

       fun getBitmapImage(byteArray: ByteArray): Bitmap{

           return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
       }
   }
}