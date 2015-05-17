import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class Wordcountpairmapper extends Mapper<LongWritable,Text,TextpairWritable,IntWritable>{
	private static 	Text lastWord=null;
	TextpairWritable tppair=new TextpairWritable();
	Text mytxt=new Text();
	public void map(LongWritable key,Text value,Context context) throws IOException,InterruptedException
	{
		String s=value.toString();
	//	String lastWord1=lastWord.toString();
	//	System.out.println("lastword is " + lastWord1);
		for (String part1: s.split(" "))
		{
				if(lastWord==null)
					lastWord=new Text(part1);
				else
					
					tppair.set(lastWord,new Text(part1));
					context.write(tppair,new IntWritable(1));
					lastWord.set(part1);
		}
	}
}
