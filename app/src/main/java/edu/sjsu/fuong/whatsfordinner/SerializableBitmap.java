package edu.sjsu.fuong.whatsfordinner;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

/**
 * Created by franc on Mar/15/2018.
 */

public class SerializableBitmap implements Serializable{
    private static final long serialVersionUID = 0L;

    private byte[] _compressedBitmap;

    // constructor compresses bitmap into Serializable form
    public SerializableBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        _compressedBitmap = stream.toByteArray();
    }

    // reconstructs Bitmap for actual use
    public Bitmap getBitmap() {
        Bitmap bitmap = BitmapFactory.decodeByteArray(_compressedBitmap, 0,
                _compressedBitmap.length);

        return bitmap;
    }
}
