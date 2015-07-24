/**
 * 
 */
package ph.fortunato.backend.common.domain;

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
	protected Boolean isDeleted;
	
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
}
