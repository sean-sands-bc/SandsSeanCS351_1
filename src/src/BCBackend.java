package src;

import java.awt.Color;
import java.nio.ByteBuffer;

public class BCBackend
{
	int val = 0;
	
	public BCBackend() {}
	
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
	
	public void clrVal()
	{
		val = 0;
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
			newVal*=-1;
		}
		val = newVal;
		//System.out.println(val);
	}
	
	private void setFlt(String s)
	{
		boolean neg = false;
		int len = s.length();
		if(len==0) { val = 0; return; }
		
		float f = 0;
		
		for(int i = 0; i<len; ++i)
		{
			
		}
		f = Float.parseFloat(s);	//	needs to be replaced
		val = fltToInt(f);
	}
	
	private int fltToInt(float f)
	{
		int fti = Float.floatToRawIntBits(f);	//	needs to be replaced
		return fti;
	}
	
	private void setCha(String s)
	{
		int newVal = 0;
		for(int i = 0; i<4; ++i)
		{
			newVal<<=8;
			if (i < s.length())
			{
				newVal|=s.charAt(i);
			}
			
		}
		val = newVal;
		//System.out.println("setCha "+s);	//	debugging console
		//System.out.println("setCha "+Integer.toHexString(val));	//	debugging console
	}
	
	public String getVal(BCGUI.ActiveField af)
	{
		switch(af)
		{
		case BIN:
			//return getInt(2);
			return getBin();
			//break;
		case CHA:
			return getCha();
			//break;
		case COL:
			break;
		case DEC:
			return getInt(10);
			//break;
		case FLT:
			return getFlt();
			//break;
		case HEX:
			//return getInt(16);
			return getHex();
			//break;
		case NON:
			break;
		case OCT:
			//return getInt(8);
			return getOct();
			//break;
		default:
			break;
		
		}
		
		return null;
		
	}
	
	
	private String getInt(int r)
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
	
	private String getBin()
	{
		String bs = "";
		int v = val;
		
		
		for(int i = 0; i < 32; ++i)
		{
			int d = v&0b1;
			bs = String.valueOf((char)(0x30+d)).concat(bs);
			v>>=1;
		}
		
		return bs;
	}
	
	private String getOct()
	{
		String os = "";
		int v = val;
		
		for(int i = 0; i < 11; ++i)
		{
			int d = v&0b111;
			os = String.valueOf((char)(0x30+d)).concat(os);
			v>>=3;
		}
		
		return os;
	}
	
	private String getHex()
	{
		String hs = "";
		int v = val;
		
		for(int i = 0; i < 8; ++i)
		{
			int d = v&0b1111;
			if (d < 10)
			{
				hs = String.valueOf((char)(0x30+d)).concat(hs);
			}
			else
			{
				hs = String.valueOf((char)(0x37+d)).concat(hs);
			}
			
			v>>=4;
		}
		
		return hs;
	}
	
	private String getFlt()
	{
		return Float.toString(Float.intBitsToFloat(val));	//	needs to be replaced
	}
	
	private String getCha()
	{
		int tempVal = val;
		String t = "";
		for(int i = 0; i < 4; ++i)
		{
			//System.out.println(String.valueOf((char)(tempVal%256)));	//	debugging console
			t=(String.valueOf((char)(tempVal%256))).concat(t);
			tempVal>>=8;
		}
		//System.out.println("getCha "+t);	//	debugging console
		return t;
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
	
	public Color getCol()
	{
		
		return new Color(val);
	}

}
/*
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
*/