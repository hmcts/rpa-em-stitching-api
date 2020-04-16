package uk.gov.hmcts.reform.em.stitching.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import uk.gov.hmcts.reform.em.stitching.domain.enumeration.TaskState;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the DocumentTask entity.
 */
@Getter
@Setter
@ToString(callSuper = true)
public class DocumentTaskDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private BundleDTO bundle;

    private TaskState taskState;

    private String failureDescription;

    @Valid
    private CallbackDto callback;

    @JsonIgnore
    private String jwt;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DocumentTaskDTO documentTaskDTO = (DocumentTaskDTO) o;
        if (documentTaskDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), documentTaskDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
