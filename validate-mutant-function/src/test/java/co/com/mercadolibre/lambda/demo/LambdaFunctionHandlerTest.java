package co.com.mercadolibre.lambda.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import co.com.mutantes.lamda.analyze.LambdaFunctionHandler;
import co.com.mutantes.lamda.analyze.dto.RequestAnalyze;
import co.com.mutantes.lamda.analyze.dto.ResponseAnalize;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class LambdaFunctionHandlerTest {

    private static RequestAnalyze input;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
        input = new RequestAnalyze();
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

    @Test
    public void testLambdaFunctionHandler() {
        LambdaFunctionHandler handler = new LambdaFunctionHandler();
        Context ctx = createContext();

        ResponseAnalize output = handler.handleRequest(input, ctx);

        // TODO: validate output here if needed.
        Assert.assertNotNull(output);
    }
}
