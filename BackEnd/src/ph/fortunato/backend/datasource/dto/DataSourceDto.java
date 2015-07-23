/**
 * 
 */
package ph.fortunato.backend.datasource.dto;

import java.util.List;

/**
 * @author Sean Ross
 *
 */
public class DataSourceDto {

	private String dataSourceKey;
	private List<DataSourceAttrDto> dataSourceAttr;
	
	public String getDataSourceKey() {
		return dataSourceKey;
	}
	public void setDataSourceKey(String dataSourceKey) {
		this.dataSourceKey = dataSourceKey;
	}
	public List<DataSourceAttrDto> getDataSourceAttr() {
		return dataSourceAttr;
	}
	public void setDataSourceAttr(List<DataSourceAttrDto> dataSourceAttr) {
		this.dataSourceAttr = dataSourceAttr;
	}
	
	
	
}
