package co.com.mutantes.lamda.analyze.util;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;

import co.com.mutantes.lamda.analize.exception.BusinessException;

public class UtilSQS {

	private UtilSQS() {
	}

	public static void sendMessage(Object obj) {
		try {
			AmazonSQS sqs = AmazonSQSClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
			SendMessageRequest sendRequest = new SendMessageRequest()
					.withQueueUrl("https://sqs.us-east-1.amazonaws.com/727030243491/my-queue")
					.withMessageBody(UtilJson.object2Json(obj)).withDelaySeconds(5);
			sqs.sendMessage(sendRequest);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("Error al enviar notificación");
		}

	}
}
