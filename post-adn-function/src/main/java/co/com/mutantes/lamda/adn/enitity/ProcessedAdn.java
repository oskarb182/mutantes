package co.com.mutantes.lamda.adn.enitity;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "ProcessedAdn")
public class ProcessedAdn {
	
	private String id;
	private String type;
	private List<String>  adn;
	
	 @DynamoDBHashKey(attributeName = "id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	 @DynamoDBAttribute(attributeName = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	 @DynamoDBAttribute(attributeName = "adn")
	public List<String> getAdn() {
		return adn;
	}
	public void setAdn(List<String>adn) {
		this.adn = adn;
	}
}
