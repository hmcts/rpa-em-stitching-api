package uk.gov.hmcts.reform.em.stitching.service.impl;

import org.junit.Assert;
import org.junit.Test;

public class DmStoreUriFormatterTest {

    private static final String mockBaseUri = "http://test-dm-store-uri";

    private final DmStoreUriFormatter dmStoreUriFormatter = new DmStoreUriFormatter(mockBaseUri);

    @Test
    public void processesWhenDocumentsIsInString() {
        String mockDocumentDetails = "/documents/12345";
        String mockCorruptedDocumentUri = "http://test-dm-store-uri:443/documents/12345";
        String result = dmStoreUriFormatter.formatDmStoreUri(mockCorruptedDocumentUri);
        Assert.assertEquals(mockBaseUri + mockDocumentDetails, result);
    }

    @Test
    public void doesNotProcessWhenDocumentsNotInString() {
        String mockUri = mockBaseUri + "/12345";
        Assert.assertEquals(mockUri, dmStoreUriFormatter.formatDmStoreUri(mockUri));
    }
}
