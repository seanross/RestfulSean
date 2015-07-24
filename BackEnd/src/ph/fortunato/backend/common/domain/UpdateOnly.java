package ph.fortunato.backend.common.domain;

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
}
