package com.amazon.samplelib.resourceleak;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPOutputStream;
import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

// adding a comment to trigger PR new revision
public class ResourceLeak {

    private AmazonS3 amazonS3;

    public Writer test(final String outputFile) throws IOException {
        OutputStream out = new FileOutputStream(outputFile);

        if (outputFile.endsWith(".gz")) {
            try {
                out = new GZIPOutputStream(out);
            } catch (IOException e) {
                out.close();
                throw e;
            }
        }
        return new OutputStreamWriter(out, StandardCharsets.UTF_8);
    }

    public String testS3(final String bucket, final String key) throws IOException {
        S3Object s3Object = amazonS3.getObject(new GetObjectRequest(bucket, key));
        S3ObjectInputStream objectData = s3Object.getObjectContent();
        String rawJson = IOUtils.toString(objectData);
        return rawJson;
    }
    
    public Object readObjectFromFile(final String objectFile) {
        try {
            final ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(objectFile)));
            final Object object = in.readObject();
            in.close();
            return object;
        } catch (final Exception e) {
            return null;
        }
    }

}
