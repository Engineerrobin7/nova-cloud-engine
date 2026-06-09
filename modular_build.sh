#!/bin/bash
# NovaOS Modular Build Script
# This script allows you to build specific parts of the OS to save time and system resources.

# 1. Setup environment
source build/envsetup.sh
TARGET=${1:-nova_emu_x86_64-userdebug}
lunch $TARGET

# Safe threads for Ryzen 5 5625U
export BUILD_THREADS=6

echo "------------------------------------------------"
echo "NovaOS Modular Build Menu"
echo "1) Build SystemUI (Status Bar, QS, Lockscreen mods)"
echo "2) Build Settings (Advanced Customization Center)"
echo "3) Build Framework Services (Volume Skip, Advanced Reboot)"
echo "4) Build Launcher (NovaLauncher)"
echo "q) Exit"
echo "------------------------------------------------"
read -p "Select a component to build: " choice

case $choice in
    1)
        echo "Building SystemUI..."
        m SystemUI -j$BUILD_THREADS
        ;;
    2)
        echo "Building Settings..."
        m Settings -j$BUILD_THREADS
        ;;
    3)
        echo "Building Framework Services..."
        m services -j$BUILD_THREADS
        ;;
    4)
        echo "Building Launcher..."
        m NovaLauncher -j$BUILD_THREADS
        ;;
    q)
        exit 0
        ;;
    *)
        echo "Invalid selection."
        ;;
esac

if [ $? -eq 0 ]; then
    echo "Module Build Successful!"
else
    echo "Module Build Failed."
fi
