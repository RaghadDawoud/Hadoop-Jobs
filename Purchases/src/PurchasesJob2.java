import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class PurchasesJob2 {

    public static class MapperClass extends Mapper<Object, Text, IntWritable, Text> {

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

            String line =value.toString();

            if(line.contains("purchID")){
                return;
            } //skip the header

            String[] columns = line.split(",");

            int id = Integer.parseInt(columns[1]); //BuyerID
            double price = Double.parseDouble(columns[2]); //purchPrice

            context.write(new IntWritable(id), new Text("1," + price)); // (BuyerID, "1,price")
        }
    }

    public static class ReducerClass extends Reducer<IntWritable, Text, IntWritable, Text> {

        public void reduce(IntWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            int numOfBuy = 0;
            double prices = 0.0;

            // Sum up purchases and prices for each BuyerID
            for (Text val : values) {
                String[] line = val.toString().split(",");
                numOfBuy += Integer.parseInt(line[0]);
                prices += Double.parseDouble(line[1]);
            }

            context.write(key, new Text(numOfBuy + "," + String.format("%.2f", prices)));  // final result "numPurchases,sumPrices"
        }
    }

    public void debug(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Buyer Purchase Count");
        job.setJarByClass(PurchasesJob2.class);
        job.setMapperClass(MapperClass.class);
        job.setCombinerClass(ReducerClass.class);
        job.setReducerClass(ReducerClass.class);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }




    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Buyer Purchase Count");
        job.setJarByClass(PurchasesJob2.class);
        job.setMapperClass(MapperClass.class);
        job.setCombinerClass(ReducerClass.class);
        job.setReducerClass(ReducerClass.class);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
