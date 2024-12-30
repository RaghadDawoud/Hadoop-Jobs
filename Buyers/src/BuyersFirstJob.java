import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class BuyersFirstJob {

    public static class MapperClass
            extends Mapper<Object, Text, Text, Text> {

        Text final_record = new Text();

        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException {

            String line = value.toString();

            if (line.contains("BuyerID")) {
                return;
            } // skip header

            String[] colomn_values = line.split(",");

            int age = Integer.parseInt(colomn_values[2].trim());
            if (age < 20 || age > 50) return;  // Only process ages 20-50

            String record = colomn_values[1].trim() + "," + colomn_values[2].trim() + "," + colomn_values[3].trim()+ "," + colomn_values[4].trim();

            context.write(new Text(colomn_values[0].trim()), new Text(record)); // (key: BuyerID , value: the rest of record)
        }
    }



    public void debug(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Buyers Age between 20 and 50");
        job.setJarByClass(BuyersFirstJob.class);
        job.setMapperClass(MapperClass.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Buyers Age between 20 and 50");
        job.setJarByClass(BuyersFirstJob.class);
        job.setMapperClass(MapperClass.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
