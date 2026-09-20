package com.novaos.ai;

/**
 * Callback for real-time AI token streaming.
 * @hide
 */
interface INovaAICallback {
    void onToken(String token);
    void onComplete();
    void onError(String error);
}
