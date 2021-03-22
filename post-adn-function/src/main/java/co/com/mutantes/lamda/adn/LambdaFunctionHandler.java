package co.com.mutantes.lamda.adn;

import java.util.Arrays;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;
import com.google.gson.Gson;

import co.com.mutantes.lamda.adn.constant.ConstantEnum;
import co.com.mutantes.lamda.adn.dto.MessageDNADto;
import co.com.mutantes.lamda.adn.enitity.ProcessedAdn;

public class LambdaFunctionHandler implements RequestHandler<SQSEvent, Void> {
	
	AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
	@Override
	public Void handleRequest(SQSEvent event, Context context) {

		
		Gson gson = new Gson();
		
		
		for (SQSMessage msg : event.getRecords()) {
			
			LambdaLogger logger = context.getLogger();
			MessageDNADto dto = gson.fromJson(msg.getBody(), MessageDNADto.class);
			ProcessedAdn entity = new ProcessedAdn();
			entity.setAdn(Arrays.asList(dto.getAdn()));
			entity.setId(getID(dto.getAdn()));
			entity.setType(dto.isMutant()?ConstantEnum.MUTANTE.getCodigo():ConstantEnum.HUMANO.getCodigo());

			DynamoDBMapper mapper = new DynamoDBMapper(client);
			mapper.save(entity);

			logger.log("Item guardado");
			
		}
		return null;
	}

	private String getID(String[] adn) {

		StringBuilder sb = new StringBuilder();
		for (String cadena : adn) {
			sb.append(cadena);
		}
		return sb.toString();
	}
	
	
}