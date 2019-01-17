package uk.gov.hmcts.reform.em.stitching.service;

import uk.gov.hmcts.reform.em.stitching.domain.BundleDocument;
import uk.gov.hmcts.reform.em.stitching.service.impl.DocumentTaskProcessingException;

import java.io.File;
import java.util.List;
import java.util.stream.Stream;

public interface DmStoreDownloader {

    Stream<File> downloadFiles(List<BundleDocument> bundleDocuments) throws DocumentTaskProcessingException;

}
