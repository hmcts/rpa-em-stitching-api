package uk.gov.hmcts.reform.em.stitching.template;

import okhttp3.*;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uk.gov.hmcts.reform.em.stitching.service.impl.DocumentTaskProcessingException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Component
public class TemplateRenditionClient {

    private String docmosisEndpoint;
    private String docmosisAccessKey;
    private final OkHttpClient client;

    @Autowired
    public TemplateRenditionClient(@Autowired OkHttpClient client,
                                   @Value("doc-assembly-app.base-url") String docmosisEndpoint,
                                   @Value("${docmosis.accessKey}") String docmosisAccessKey) {
        this.client = client;
        this.docmosisEndpoint = docmosisEndpoint;
        this.docmosisAccessKey = docmosisAccessKey;
    }

    public File renderTemplate(String templateId, String payload) throws IOException, DocumentTaskProcessingException {
        String tempFileName = String.format("%s%s",
                UUID.randomUUID().toString(), ".pdf");

        MultipartBody requestBody = new MultipartBody
                .Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(
                        "templateName",
                        templateId)
                .addFormDataPart(
                        "accessKey",
                        docmosisAccessKey)
                .addFormDataPart(
                        "outputName",
                        tempFileName)
                .addFormDataPart(
                        "data",
                        payload)
                .build();

        Request request = new Request.Builder()
                .url(docmosisEndpoint)
                .method("POST", requestBody)
                .build();

        Response response =  client.newCall(request).execute();

        if (response.isSuccessful()) {
            File file = File.createTempFile(
                    "docmosis-rendition",
                    ".pdf");
            IOUtils.copy(response.body().byteStream(), new FileOutputStream(file));
            return file;
        } else {
            throw new DocumentTaskProcessingException(
                    "Could not render Cover Page template. HTTP response: " + response.code());
        }
    }
}
