package co.com.mutantes.lamda.stadistics;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.amazonaws.services.dynamodbv2.model.Select;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import co.com.mutantes.lamda.stadistics.constant.ConstantEnum;
import co.com.mutantes.lamda.stadistics.constant.ResponseCodesEnum;
import co.com.mutantes.lamda.stadistics.dto.StadisticsResponse;
import co.com.mutantes.lamda.stadistics.exception.BusinessException;
import co.com.mutantes.lamda.stadistics.util.ResponseBuilder;
import co.com.mutantes.lamda.stadistics.util.UtilJson;

/**
 * Clase hanler que implementa función de consulta de estadisticas
 * @author Oscar Bermudez
 *
 */
public class LambdaFunctionHandler implements RequestHandler<Object, StadisticsResponse> {
	
	private ResponseBuilder<StadisticsResponse> builder = new ResponseBuilder<>();
	
	@Override  
	public StadisticsResponse handleRequest(Object input, Context context) {
		context.getLogger().log("Input: " + input);
		
		LambdaLogger logger = context.getLogger();
		

		StadisticsResponse response = new StadisticsResponse();
		
		try {
			long countHuman = countItemsByType(ConstantEnum.HUMANO.getCodigo());
			logger.log("Total de humanos : "+countHuman);
			long countMutans = countItemsByType(ConstantEnum.MUTANTE.getCodigo());
			logger.log("Total de Mutantes : "+countMutans);
			response.setCount_human_dna(countHuman); 
			response.setCount_mutant_dna(countMutans);
		
			response.setRatio(((double)countMutans/countHuman));
			logger.log("Ratio : "+response.getRatio());
			builder.build(ResponseCodesEnum.SUCESSFULL, response);
		} catch (Exception e) {
			logger.log("Error inesperado: "+e.getMessage());
			builder.build(ResponseCodesEnum.INTERNAL_SERVER_ERROR, response);
		
			throw new BusinessException(UtilJson.object2Json(response));
		}
		

		
		return response;
	}

	/**
	 * Metodo que permite ejecutar consulta por indice
	 * @param type
	 * @return
	 */
	public long countItemsByType(String type) {
		
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();

		Condition typeCondition = new Condition().withComparisonOperator(ComparisonOperator.EQ)
				.withAttributeValueList(new AttributeValue().withS(type));

		Map<String, Condition> keyConditions = new HashMap<>();
		keyConditions.put("type", typeCondition);

		QueryRequest request = new QueryRequest("ProcessedAdn");
		request.setIndexName("type-index");
		request.setSelect(Select.COUNT);
		request.setKeyConditions(keyConditions);

		QueryResult result = client.query(request);
		return result.getCount();

		
	}

}
