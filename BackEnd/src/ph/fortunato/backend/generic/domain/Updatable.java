/**
 * 
 */
package ph.fortunato.backend.generic.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author S.FORTUNATO
 *
 */
@MappedSuperclass
public class Updatable {
	
	protected Long createdBy;
	protected String createdDate;
	protected Long updatedBy;
	protected String updatedDate;
	protected Boolean isDisabled;
	
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
	

	@Column(name="created_by")
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name="created_date")
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	@Column(name="is_disabled")
	public Boolean getIsDisabled() {
		return isDisabled;
	}
	public void setIsDisabled(Boolean isDisabled) {
		this.isDisabled = isDisabled;
	}
}
