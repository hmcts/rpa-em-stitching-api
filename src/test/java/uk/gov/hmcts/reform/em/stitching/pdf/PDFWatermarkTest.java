package uk.gov.hmcts.reform.em.stitching.pdf;

import org.junit.Before;
import org.junit.Test;
import uk.gov.hmcts.reform.em.stitching.domain.Bundle;
import uk.gov.hmcts.reform.em.stitching.domain.BundleDocument;
import uk.gov.hmcts.reform.em.stitching.domain.DocumentImage;
import uk.gov.hmcts.reform.em.stitching.domain.enumeration.ImageRendering;
import uk.gov.hmcts.reform.em.stitching.domain.enumeration.ImageRenderingLocation;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.*;

import static uk.gov.hmcts.reform.em.stitching.pdf.PDFMergerTestUtil.createFlatTestBundle;

public class PDFWatermarkTest {
    private static final File WATERMARK_FILE = new File(
            ClassLoader.getSystemResource("schmcts.png").getPath()
    );

    private static final File FILE_1 = new File(
            ClassLoader.getSystemResource("TEST_INPUT_FILE.pdf").getPath()
    );
    private static final File FILE_2 = new File(
            ClassLoader.getSystemResource("annotationTemplate.pdf").getPath()
    );

    private Bundle bundle;
    private HashMap<BundleDocument, File> documents;
    private DocumentImage documentImage;

    @Before
    public void setUp() {
        bundle = createFlatTestBundle();
        documents = new HashMap<>();

        documentImage = new DocumentImage();
        documentImage.setDocmosisAssetId("schmcts.png");
        documentImage.setImageRendering(ImageRendering.opaque);
        documentImage.setImageRenderingLocation(ImageRenderingLocation.allPages);

        documents.put(bundle.getDocuments().get(0), FILE_1);
        documents.put(bundle.getDocuments().get(1), FILE_2);
    }

    @Test
    public void processDocumentWatermarkInvalidCoordinatesTest() throws IOException {
        documentImage.setCoordinateX(1000);
        documentImage.setCoordinateY(-1);

        PDFWatermark pdfWatermark = new PDFWatermark();
        pdfWatermark.processDocumentWatermark(WATERMARK_FILE, documents, documentImage);

        assertEquals(documentImage.getCoordinateX(), 100);
        assertEquals(documentImage.getCoordinateY(), 0);
    }
}
