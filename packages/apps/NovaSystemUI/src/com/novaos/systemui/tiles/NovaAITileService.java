package com.novaos.systemui.tiles;

import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.content.Intent;
import android.util.Log;

public class NovaAITileService extends TileService {
    private static final String TAG = "NovaAITileService";

    @Override
    public void onClick() {
        super.onClick();
        Tile tile = getQsTile();
        if (tile == null) return;

        // Toggle logic or open AI Hub
        Intent intent = new Intent();
        intent.setClassName("com.novaos.ai.hub", "com.novaos.ai.hub.MainActivity");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivityAndCollapse(intent);
        
        Log.d(TAG, "Nova AI Tile clicked");
    }

    @Override
    public void onStartListening() {
        super.onStartListening();
        Tile tile = getQsTile();
        if (tile != null) {
            tile.setState(Tile.STATE_ACTIVE);
            tile.setLabel("Nova AI");
            tile.updateTile();
        }
    }
}
