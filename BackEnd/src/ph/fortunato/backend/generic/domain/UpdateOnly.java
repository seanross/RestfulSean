package ph.fortunato.backend.generic.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 
 * @author S.FORTUNATO
 *
 */
@MappedSuperclass
public class UpdateOnly {
	
	protected Long updatedBy;
	protected String updatedDate;
	private Boolean isDisabled;
	
	@Column(name="updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Column(name="updated_date")
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	@Column(name="is_disabled")
	public Boolean getIsDisabled() {
		return isDisabled;
	}
	public void setIsDisabled(Boolean isDisabled) {
		this.isDisabled = isDisabled;
	}
}
