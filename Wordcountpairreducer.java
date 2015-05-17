import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;


public class Wordcountpairreducer extends Reducer<TextpairWritable,IntWritable,TextpairWritable,IntWritable> {
	public void reduce(TextpairWritable key,Iterable<IntWritable> values,Context context) throws IOException,InterruptedException
	{
		int count=0;
		for(IntWritable value: values)
		{
			count+=value.get();
		}
		context.write(key, new IntWritable(count));
	}

}
