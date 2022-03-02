package com.amap.flutter.map.overlays.marker;

import com.amap.api.maps.utils.overlay.MovingPointOverlay;

import java.util.HashMap;
import java.util.Map;

import io.flutter.plugin.common.MethodChannel;

public class MarkerMoveListener implements MovingPointOverlay.MoveListener {
    private MethodChannel methodChannel;
    private String markerId;
    private MovingPointOverlay marker;

    public MarkerMoveListener(MethodChannel methodChannel, String markerId, MovingPointOverlay marker) {
        this.methodChannel = methodChannel;
        this.markerId = markerId;
        this.marker = marker;
    }

    @Override
    public void move(double v) {
        final Map<String, Object> data = new HashMap<>(2);
        data.put("markerId", markerId);
        data.put("distance", v);
        data.put("index", marker.getIndex());
        methodChannel.invokeMethod("marker#onMove", data);
    }
}
