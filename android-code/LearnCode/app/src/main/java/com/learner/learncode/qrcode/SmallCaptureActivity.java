package com.learner.learncode.qrcode;

import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.CompoundBarcodeView;
import com.learner.learncode.R;

/**
 * This activity has a margin.
 */
public class SmallCaptureActivity extends CaptureActivity {
    @Override
    protected CompoundBarcodeView initializeContent() {
        setContentView(R.layout.capture_small);
        return (CompoundBarcodeView)findViewById(R.id.zxing_barcode_scanner);
    }
}
