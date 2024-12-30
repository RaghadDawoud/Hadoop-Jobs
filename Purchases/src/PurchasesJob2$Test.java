import org.junit.Test;

import static org.junit.Assert.*;

public class PurchasesJob2$Test {

    @Test
    public void debug() throws Exception {
        String[] input = new String[2];



        input[0] = "file:///C:/Users/pca/Desktop/HadoopProjects/PurchasesJob2/Purchases.csv";
        input[1] = "file:///C:/Users/pca/Desktop/HadoopProjects/PurchasesJob2/output.txt";

        PurchasesJob2 buyer_Purchases = new PurchasesJob2();
        buyer_Purchases.debug(input);
    }
}