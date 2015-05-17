import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;


public class TextpairWritable implements WritableComparable<TextpairWritable>{

	private Text first;
	private Text second;
	public TextpairWritable()
	{
		set(new Text(),new Text());
	}

	public TextpairWritable(Text first,Text second)
	{
		set(first,second);
	}
	public TextpairWritable(String first,String second)
	{
		set(new Text(first),new Text(second));
		
	}
	@Override
	public void readFields(DataInput in) throws IOException {
		first.readFields(in);
		second.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		first.write(out);
		second.write(out);
	}

	public void set(Text first,Text second)
	{
		this.first=first;
		this.second=second;
	}
	  @Override
	    public String toString() {
	        return first + " " + second;
	    }
	
	@Override
	public int compareTo(TextpairWritable tp) {
		int cmp = first.compareTo(tp.first);
		if (cmp != 0) {
			return cmp;
		}
		return second.compareTo(tp.second);
	}
	
	public class TextpairComparator extends WritableComparator
	{
		public TextpairComparator()
		{
			super(TextpairWritable.class);
		}
		
		public int compare(byte[] s,int s1,int l1,byte[] s3,int s2,int l2)
		{
			int i1=readInt(s,s1);
			int i2=readInt(s3,s2);
			int comp = (i1 < i2) ? -1 : (i1 == i2) ? 0 : 1;
	        if(0 != comp)
	            return comp;
	         
	        int j1 = readInt(s, s1+4);
	        int j2 = readInt(s3, s2+4);
	        comp = (j1 < j2) ? -1 : (j1 == j2) ? 0 : 1;
	         
	        return comp;
		}
	}


	}
