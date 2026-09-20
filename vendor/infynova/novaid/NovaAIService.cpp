/**
 * NovaAID — InfYNova Native AI System Daemon
 *
 * This daemon provides a Binder interface to a local LLM/SLM engine.
 * It uses libbinder for IPC and interfaces with llama.cpp.
 */

#define LOG_TAG "NovaAID"

#include <binder/IBinder.h>
#include <binder/IPCThreadState.h>
#include <binder/IServiceManager.h>
#include <binder/ProcessState.h>
#include <utils/Log.h>
#include <utils/String16.h>

#include "com/infynova/novaid/BnNovaAIService.h"

using namespace android;

namespace com {
namespace infynova {
namespace novaid {

class NovaAIService : public BnNovaAIService {
public:
    static char const* getServiceName() { return "novaid"; }

    binder::Status queryModel(const String16& prompt, const os::Bundle& params, String16* _aidl_return) override {
        ALOGI("queryModel: %s", String8(prompt).c_str());

        // Mock inference logic - in production, this would call llama.cpp entry points
        std::string response = "NovaAID [SLM 1B]: I received your prompt. Local inference active.";
        *_aidl_return = String16(response.c_str());

        return binder::Status::ok();
    }

    binder::Status streamQuery(const String16& prompt, const sp<INovaAIStreamCallback>& callback) override {
        ALOGI("streamQuery: %s", String8(prompt).c_str());

        if (callback != nullptr) {
            callback->onToken(String16("Streaming "));
            callback->onToken(String16("from "));
            callback->onToken(String16("NovaAID..."));
            callback->onComplete();
        }

        return binder::Status::ok();
    }
};

} // namespace novaid
} // namespace infynova
} // namespace com

int main(int argc, char** argv) {
    ALOGI("NovaAID starting...");

    sp<ProcessState> proc(ProcessState::self());
    sp<IServiceManager> sm = defaultServiceManager();

    using com::infynova::novaid::NovaAIService;
    sm->addService(String16(NovaAIService::getServiceName()), new NovaAIService());

    ProcessState::self()->startThreadPool();
    IPCThreadState::self()->joinThreadPool();

    return 0;
}
