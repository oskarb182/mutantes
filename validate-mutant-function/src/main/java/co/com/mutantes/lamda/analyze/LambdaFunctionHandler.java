package co.com.mutantes.lamda.analyze;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import co.com.mutantes.lamda.analize.exception.BusinessException;
import co.com.mutantes.lamda.analyze.dto.MessageDNADto;
import co.com.mutantes.lamda.analyze.dto.RequestAnalyze;
import co.com.mutantes.lamda.analyze.dto.ResponseAnalize;
import co.com.mutantes.lamda.analyze.util.ResponseBuilder;
import co.com.mutantes.lamda.analyze.util.UtilAnalizeMutant;
import co.com.mutantes.lamda.analyze.util.UtilJson;
import co.com.mutantes.lamda.analyze.util.UtilSQS;
import co.com.mutantes.lamda.stadistics.constant.ResponseCodesEnum;




public class LambdaFunctionHandler implements RequestHandler<RequestAnalyze, ResponseAnalize> {
	
	private ResponseBuilder<ResponseAnalize> builder = new ResponseBuilder<>();
	
	
	
    @Override
    public ResponseAnalize handleRequest(RequestAnalyze input, Context context) {
        context.getLogger().log("Input: " + input);
        ResponseAnalize response = new ResponseAnalize();
        LambdaLogger logger = context.getLogger();
        
        
        if (input==null || input.getDna()==null) {
        	logger.log("Request no valido: "+ input);
        	builder.build(ResponseCodesEnum.INVALID_REQUEST, response);
			throw new BusinessException(UtilJson.object2Json(response));
		}else {
			
			boolean resultAnalyze = analizar(context,input.getDna(), response);
			logger.log("Resultado del analiziz: "+ resultAnalyze);
			logger.log("Enviando mensaje");
			sendMessage(resultAnalyze,input.getDna() );
			if (resultAnalyze) {
				logger.log("Mutante :"+input.getDna() );
				builder.build(ResponseCodesEnum.SUCESSFULL, response);
				
			}else {
				logger.log("No mutante :"+input.getDna() );
				builder.build(ResponseCodesEnum.NO_MUTANT, response);
				throw new BusinessException(UtilJson.object2Json(response));
			}
			
	    	
		}
        
        
        return response;
    }
    
    public boolean analizar(Context context, String [] dna, ResponseAnalize response ) {
    	boolean result ;
    	UtilAnalizeMutant utilAnalize = new UtilAnalizeMutant(context);
    	if(utilAnalize.validateInput(dna)) {
    		
    		 result = utilAnalize.analizeADN(dna);
    		
    		
    	}else{
    		builder.build(ResponseCodesEnum.INVALID_REQUEST, response);
    		throw new BusinessException(UtilJson.object2Json(response));
    		
    	}
    	
    	return result;
    	
    }
    
    public void sendMessage(boolean isMutant, String [] dna) {
    	
    	MessageDNADto msgRQ = new MessageDNADto();
		msgRQ.setAdn(dna);
		msgRQ.setMutant(isMutant);
		UtilSQS.sendMessage(msgRQ);
    }
    
    

}
