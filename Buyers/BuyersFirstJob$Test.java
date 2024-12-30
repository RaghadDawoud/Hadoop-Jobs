import org.junit.Test;

import static org.junit.Assert.*;

public class BuyersFirstJob$Test {

    @Test
    public void debug() throws Exception {
        String[] input = new String[2];


        input[0] = "file:///C:/Users/pca/Desktop/HadoopProjects/BuyersFirstJob/Buyers.csv";
        input[1] = "file:///C:/Users/pca/Desktop/HadoopProjects/BuyersFirstJob/output.txt";

        BuyersFirstJob buyer = new BuyersFirstJob();
        buyer.debug(input);
    }
}