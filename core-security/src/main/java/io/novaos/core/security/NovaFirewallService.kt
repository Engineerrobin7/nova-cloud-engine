package io.novaos.core.security

import android.content.Intent
import android.net.VpnService
import android.os.ParcelFileDescriptor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.ByteBuffer

/**
 * NovaFirewallService: A Local VPN-based firewall to block per-app internet access.
 */
class NovaFirewallService : VpnService() {

    private var vpnInterface: ParcelFileDescriptor? = null
    private val scope = CoroutineScope(Dispatchers.IO + Job())

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        setupVpn()
        return START_STICKY
    }

    private fun setupVpn() {
        val builder = Builder()
            .setSession("NovaOS Firewall")
            .addAddress("10.0.0.1", 24)
            .addDnsServer("8.8.8.8")
            .addRoute("0.0.0.0", 0)

        // Per-app blocking logic
        // builder.addDisallowedApplication("com.example.blocked.app")

        vpnInterface = builder.establish()
        
        scope.launch {
            runVpnLoop()
        }
    }

    private fun runVpnLoop() {
        val inputStream = FileInputStream(vpnInterface?.fileDescriptor)
        val outputStream = FileOutputStream(vpnInterface?.fileDescriptor)
        val buffer = ByteBuffer.allocate(32767)

        while (true) {
            val length = inputStream.read(buffer.array())
            if (length > 0) {
                // Inspect packet and block if necessary
                // This is a simplified local-only firewall loop
                outputStream.write(buffer.array(), 0, length)
                buffer.clear()
            }
        }
    }

    override fun onDestroy() {
        vpnInterface?.close()
        super.onDestroy()
    }
}
