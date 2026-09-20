/**
 * Copyright (C) 2026 InfYNova NovaOS
 */

package com.infynova.novaid;

interface INovaAIService {
    /**
     * Sends a prompt to the local SLM engine and receives a response.
     */
    String queryModel(String prompt, in Bundle params);

    /**
     * Streams inference results back to the caller.
     */
    void streamQuery(String prompt, INovaAIStreamCallback callback);
}
