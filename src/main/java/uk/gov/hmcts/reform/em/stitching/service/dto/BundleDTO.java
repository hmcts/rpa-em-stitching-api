package uk.gov.hmcts.reform.em.stitching.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BundleDTO extends AbstractAuditingDTO implements Serializable {

    @JsonIgnore
    private Long id;

    private String bundleTitle;
    private String description;
    private String stitchedDocumentURI;
    private String stitchStatus;
    private List<BundleFolderDTO> folders = new ArrayList<>();
    private List<BundleDocumentDTO> documents = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBundleTitle() {
        return bundleTitle;
    }

    public void setBundleTitle(String bundleTitle) {
        this.bundleTitle = bundleTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStitchedDocumentURI() {
        return stitchedDocumentURI;
    }

    public void setStitchedDocumentURI(String stitchedDocumentURI) {
        this.stitchedDocumentURI = stitchedDocumentURI;
    }

    public String getStitchStatus() {
        return stitchStatus;
    }

    public void setStitchStatus(String stitchStatus) {
        this.stitchStatus = stitchStatus;
    }

    public List<BundleFolderDTO> getFolders() {
        return folders;
    }

    public void setFolders(List<BundleFolderDTO> folders) {
        this.folders = folders;
    }

    public List<BundleDocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(List<BundleDocumentDTO> documents) {
        this.documents = documents;
    }

}

