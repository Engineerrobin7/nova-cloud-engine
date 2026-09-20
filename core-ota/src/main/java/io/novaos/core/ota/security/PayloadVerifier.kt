package io.novaos.core.ota.security

import android.os.Build
import android.util.Log
import java.io.File
import java.security.KeyFactory
import java.security.Signature
import java.security.spec.X509EncodedKeySpec
import javax.inject.Inject
import javax.inject.Singleton

/**
 * PayloadVerifier: Ensures OTA payloads are authentic and safe to apply.
 */
@Singleton
class PayloadVerifier @Inject constructor() {

    private val TAG = "NovaOtaVerifier"
    
    // In a production environment, this would be a hardware-backed public key
    private val NOVA_PUBLIC_KEY_BASE64 = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA..." 

    /**
     * Verifies the cryptographic signature of the OTA payload.
     */
    fun verifySignature(payloadFile: File, signatureBytes: ByteArray): Boolean {
        return try {
            val publicKeyBytes = android.util.Base64.decode(NOVA_PUBLIC_KEY_BASE64, android.util.Base64.DEFAULT)
            val keySpec = X509EncodedKeySpec(publicKeyBytes)
            val keyFactory = KeyFactory.getInstance("RSA")
            val publicKey = keyFactory.generatePublic(keySpec)

            val signature = Signature.getInstance("SHA256withRSA")
            signature.initVerify(publicKey)
            
            // For efficiency, we should stream this in chunks
            signature.update(payloadFile.readBytes())
            
            signature.verify(signatureBytes)
        } catch (e: Exception) {
            Log.e(TAG, "Signature verification failed", e)
            false
        }
    }

    /**
     * Prevents rollback attacks by checking incremental version and security patch levels.
     */
    fun isRollbackSafe(newIncremental: String, newSecurityPatch: String): Boolean {
        val currentIncremental = Build.VERSION.INCREMENTAL
        val currentSecurityPatch = Build.VERSION.SECURITY_PATCH
        
        // Basic check: version must be greater than or equal to current
        // Implementation would involve comparing date strings for security patch
        return newIncremental >= currentIncremental && newSecurityPatch >= currentSecurityPatch
    }
}
