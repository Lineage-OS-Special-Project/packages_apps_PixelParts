/*
 * Copyright (C) 2023-2024 The LOSP Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.losp.pixelparts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.losp.pixelparts.autohbm.AutoHbmActivity;
import org.losp.pixelparts.autohbm.AutoHbmFragment;
import org.losp.pixelparts.autohbm.AutoHbmTileService;
import org.losp.pixelparts.pixeltorch.PixelTorchActivity;
import org.losp.pixelparts.pixeltorch.PixelTorchFragment;
import org.losp.pixelparts.pixeltorch.PixelTorchButtonService;
import org.losp.pixelparts.pixeltorch.PixelTorchTileService;
import org.losp.pixelparts.saturation.SaturationFragment;
import org.losp.pixelparts.utils.ComponentUtils;
import org.losp.pixelparts.utils.FileUtils;

public class Startup extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();

        // Auto hbm
        AutoHbmFragment.toggleAutoHbmService(context);

        ComponentUtils.toggleComponent(
                context,
                AutoHbmActivity.class,
                AutoHbmFragment.isHbmSupported(context)
        );

        ComponentUtils.toggleComponent(
                context,
                AutoHbmTileService.class,
                AutoHbmFragment.isHbmSupported(context)
        );

        // PixelTorch
        ComponentUtils.toggleComponent(
                context,
                PixelTorchActivity.class,
                PixelTorchFragment.hasTorch(context)
        );

        ComponentUtils.toggleComponent(
                context,
                PixelTorchButtonService.class,
                PixelTorchFragment.hasTorch(context)
        );

        ComponentUtils.toggleComponent(
                context,
                PixelTorchTileService.class,
                PixelTorchFragment.hasTorch(context)
        );

        // Saturation
        SaturationFragment saturationFragment = new SaturationFragment();
        saturationFragment.restoreSaturationSetting(context);
    }
}
