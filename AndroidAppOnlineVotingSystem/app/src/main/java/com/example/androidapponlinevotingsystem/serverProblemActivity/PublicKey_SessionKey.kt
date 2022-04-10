package com.example.androidapponlinevotingsystem.serverProblemActivity

import android.os.Build
import androidx.annotation.RequiresApi
import java.security.KeyFactory
import java.security.PublicKey
import java.security.spec.X509EncodedKeySpec
import java.util.*
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

class PublicKey_SessionKey {

    companion object{
         var publicKey: PublicKey? = null
         var sessionKey: SecretKey? = null


        @RequiresApi(Build.VERSION_CODES.O)
        fun convertTopublicKey(stringpkey: String){

            val keySpec = X509EncodedKeySpec(Base64.getDecoder().decode(stringpkey.toByteArray()))
            val keyFactory = KeyFactory.getInstance("RSA")
            publicKey = keyFactory.generatePublic(keySpec)

            this.publicKey = publicKey
        }

        fun generateSessionKey(){
            val keygenerator = KeyGenerator.getInstance("DES")
            val myDesKey : SecretKey = keygenerator.generateKey()
            this.sessionKey = myDesKey
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun publicEncryptSessionKey(): String{

            var rsa : Cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
            rsa.init(Cipher.ENCRYPT_MODE, publicKey)

            return Base64.getEncoder().encodeToString(rsa.doFinal(sessionKey!!.encoded))
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun sessionEncryp(message: String): String{

            var des : Cipher = Cipher.getInstance("DES")
            des.init(Cipher.ENCRYPT_MODE, sessionKey)

            return Base64.getEncoder().encodeToString(des.doFinal(message.toByteArray()))

        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun seesionDecrypt(message: String): String{

            var des : Cipher = Cipher.getInstance("DES")
            des.init(Cipher.DECRYPT_MODE, sessionKey)
            var myDecryptedBytes: ByteArray = Base64.getDecoder().decode(message)

           return String(des.doFinal(myDecryptedBytes))
        }

    }
}