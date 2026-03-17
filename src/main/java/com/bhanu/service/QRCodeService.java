package com.bhanu.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class QRCodeService {

    public byte[] generateQR(String url){

        try{

            QRCodeWriter writer = new QRCodeWriter();

            BitMatrix matrix = writer.encode(url, BarcodeFormat.QR_CODE,250,250);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            MatrixToImageWriter.writeToStream(matrix,"PNG",stream);

            return stream.toByteArray();

        }catch(Exception e){

            throw new RuntimeException("QR generation failed");
        }

    }
}
