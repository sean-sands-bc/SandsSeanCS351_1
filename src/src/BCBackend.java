package src;

import java.awt.Color;
import java.nio.ByteBuffer;

public class BCBackend {
	
	int val;
	
	public BCBackend()
	{
		
	}
	
	public void setVal(int i)
	{
		val = i;
	}
	
	public void setVal(float f)
	{
		val = Float.floatToIntBits(f);
	}
	
	public void setVal(Color c)
	{
		val = 0;
		val+=c.getAlpha();
		val <<= 8;
		val+=c.getRed();
		val <<= 8;
		val+=c.getGreen();
		val <<= 8;
		val+=c.getBlue();
		
		//val = c.getRGB();
	}
	
	public void setVal(String s, BCGUI.ActiveField af)
	{
		switch(af)
		{
		case BIN:
			setInt(s, 2);
			break;
		case CHA:
			setCha(s);
			break;
		case COL:
			break;
		case DEC:
			setInt(s, 10);
			break;
		case FLT:
			setFlt(s);
			break;
		case HEX:
			setInt(s, 16);
			break;
		case NON:
			break;
		case OCT:
			setInt(s, 8);
			break;
		default:
			break;
		
		}
	}
	
	
	
	private void setInt(String s, int r)
	{
		boolean neg = false;
		int len = s.length();
		if(len==0) { val = 0; return; }
		int i = 0;
		if(s.charAt(0) == '-') { neg = true; i = 1;}
		int newVal = 0;
		int max = (int) (Math.ceil(32/(Math.log(r)/Math.log(2))) + i);
		for(; (i < len) && (i < max); ++i)
		{
			newVal*=r;
			char c = s.charAt(i);
			int dig = 0;
			if (c > 0x2f && c < 0x3a)
			{
				dig = c-0x30;
			}
			else if(c > 0x40 && c<0x5b)
			{
				dig = c-0x37;
			}
			else if(c > 0x60 && c<0x7b)
			{
				dig = c-0x57;
			}
			else { return; }
			if (dig < r)
			{
				newVal+=dig;
			}
			else { return; }
		}
		if(neg) 
		{
			newVal^=0b11111111111111111111111111111111;
			newVal += 1;
		}
		val = newVal;
	}
	
	private void setFlt(String s)
	{
		if (s.equals("NaN")) { val = 0b01111111110000000000000000000000; return; }
		else if (s.equals("posinf")) { val = 0b01111111100000000000000000000000; return; }
		else if (s.equals("neginf")) { val = 0b11111111100000000000000000000000; return; }
		int newVal = 0;
		float f = Float.parseFloat(s);
		if (f==0.0) { val = 0; return; }
		boolean neg = (f < 0.0);
		if (neg)
		{
			f*=-1;
			
		}
		
		int m = 0;
		while (f < 1.0)
		{
			f*=2;
			--m;
		}
		while (f > 2.0)
		{
			f/=2;
			++m;
		}
		//newVal <<= 8;
		m+=127;
		m<<=23;
		//newVal+=m;
		//f&=;
		//f|=;
		f-=1.0;
		
		for(int i = 0; i<23; ++i)
		{
			newVal<<=1;
			f*=2;
			if(f>=1.0)
			{
				newVal+=1;
				f-=1.0;
			}
			
			
		}
		//newVal <<= 23;
		
		if(neg)
		{
			newVal|=0b10000000000000000000000000000000;
		}
		newVal|=m;
		
		
		val = newVal;
	}
	
	private void setCha(String s)
	{
		
	}
	
	
	
	public void setVal(String s)
	{
		ByteBuffer bb = ByteBuffer.wrap(s.getBytes());
		val = bb.getInt();
	}
	
	public int getInt()
	{
		return val;
	}
	
	public float getFloat()
	{
		return Float.intBitsToFloat(val);
	}
	
	public Color getColor()
	{
		
		return new Color(val);
	}
	
	public String getString()
	{
		String s = new String(ByteBuffer.allocate(4).putInt(val).array());
		
		return s;
	}
	
	public String getInt(int r)
	{
		if (val == 0) { return "0"; }
		String is = "";
		boolean neg = false;
		int v = val;
		if (v < 0) { neg = true; v*=-1; }
		if (r > 36 || r < 2) { return null; }
		
		
		while(v!=0)
		{
			int d = v%r;
			v = v/r;
			if (d < 10)
			{
				is = String.valueOf((char)(0x30+d)).concat(is);
			}
			else
			{
				is = String.valueOf((char)(0x37+d)).concat(is);
			}
		}
		if (neg) { is = "-".concat(is); }
		return is;
	}
	
	public String getFlt()
	{
		return null;
	}
	

}
