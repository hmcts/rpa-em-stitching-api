package uk.gov.hmcts.reform.em.stitching.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import uk.gov.hmcts.reform.em.stitching.domain.enumeration.CallbackState;
import uk.gov.hmcts.reform.em.stitching.domain.validation.CallableEndpoint;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the DocumentTask entity.
 */
@Getter
@Setter
@ToString(callSuper = true)
public class CallbackDto extends AbstractAuditingDTO implements Serializable {

    private Long id;

    private CallbackState callbackState = CallbackState.NEW;

    private String failureDescription;

    @NotNull
    @CallableEndpoint
    private String callbackUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CallbackDto callbackDto = (CallbackDto) o;
        if (callbackDto.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), callbackDto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
