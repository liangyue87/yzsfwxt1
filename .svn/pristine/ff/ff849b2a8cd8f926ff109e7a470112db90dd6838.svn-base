
package com.ljx.hfgsjt.util.utils;

import org.springframework.boot.system.ApplicationHome;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;



public class StringUtil {
  /**
   * <p>Description </p>
   */
  public static boolean bTextBox = true;
  
  /**
   * <p>鏁扮粍杞瓧绗︿覆</p>
   */
  public static String arrtostr(String[] oarr,String delimiter) { 
	  StringBuffer sb = new StringBuffer();
	  String newStr = "";
	  if ( oarr != null ) { 
	     for(int i = 0; i < oarr.length; i++){
	       sb.append(oarr[i]+delimiter);
	     }
	     newStr = sb.toString();
	  }
	  if (!"".equals(newStr)) { 
		  newStr = newStr.substring(0,newStr.length()-1);
	  }
	  return newStr;
  }
  

  /**
   * <p>瀵瑰弬鏁颁紶閫佽繃鏉ョ殑瀛楃涓茶繘琛屼粠iso-8859-1鍒癎BK鏍煎紡杞寲鍚庤繑鍥?/p>
   */
  public static String getGBKFromISO(String str) {
    //System.out.println("**********************"+str+"**************");
    try {
      byte[] buf = str.getBytes("iso-8859-1");
      str = new String(buf, "GBK");
      return str;
    } catch (java.io.UnsupportedEncodingException e) {
      return "";
    }
  }

//  获取根路径
    public static String getBasePath(){
        ApplicationHome home = new ApplicationHome(StringUtil.class);
        File jarFile = home.getSource();
        String basePath = jarFile.getParentFile().toString()+File.separator;
        return basePath;
    }

  /**
   * <p>瀵瑰弬鏁颁紶閫佽繃鏉ョ殑瀛楃涓茶繘琛屼粠GBK鍒癷so-8859-1鏍煎紡杞寲鍚庤繑鍥?/p>
   */
  public static String getISOFromGBK(String str) {
    //System.out.println("**********************"+str+"**************");
    try {
      byte[] buf = str.getBytes("GBK");
      str = new String(buf, "iso-8859-1");
      return str;
    } catch (java.io.UnsupportedEncodingException e) {
      return "";
    }
  }
  
  /**
   * <p>瀵瑰弬鏁颁紶閫佽繃鏉ョ殑瀛楃涓茶繘琛屼粠GBK鍒癠TF-8鏍煎紡杞寲鍚庤繑鍥?/p>
   */
  public static String getUTFFromGBK(String str) {
    //System.out.println("**********************"+str+"**************");
    try {
      byte[] buf = str.getBytes("GBK");
      str = new String(buf, "UTF-8");
      return str;
    } catch (java.io.UnsupportedEncodingException e) {
      return "";
    }
  }


  /**
   * Explore String The first parament is father string (String). The second parament is
   * separator (String). return a Vector.
   */
  @SuppressWarnings("unchecked")
public static Vector explore(String handleStr, String pointStr) {
    Vector v = new Vector();
    int pos1, pos2;
    try {
      if (handleStr.length() > 0) {
        pos1 = handleStr.indexOf(pointStr);
        pos2 = 0;
        while (pos1 != -1) {
          v.addElement(handleStr.substring(pos2, pos1));
          pos2 = pos1 + pointStr.length();
          pos1 = handleStr.indexOf(pointStr, pos2);
        }
        v.addElement(handleStr.substring(pos2));
      }
    } catch (Exception error) {
      error.printStackTrace();
    }
    return v;
  }

  /**
   * <p>Description 灏嗗弬鏁颁腑鐨勬簮瀛楃涓瞙andleStr涓殑鍏ㄩ儴pointStr鏇挎崲鎴恟epStr骞惰繑鍥?/p> 
   */
  public static String replace(String handleStr, String pointStr, String repStr) {
    String str = new String();
    int pos1, pos2;
    try {
      if (handleStr.length() > 0) {
        pos1 = handleStr.indexOf(pointStr);
        pos2 = 0;
        while (pos1 != -1) {
          str += handleStr.substring(pos2, pos1);
          str += repStr;
          pos2 = pos1 + pointStr.length();
          pos1 = handleStr.indexOf(pointStr, pos2);
        }
        str += handleStr.substring(pos2);
      }
    } catch (Exception error) {
      error.printStackTrace();
    }
    return str;
  }


  /**
   * <p>Description </p>
   */
  public static void SetReturn(boolean bAttrib) {
    bTextBox = bAttrib;
  }

  /**
   * <p>Description 灏嗗弬鏁颁腑鐨勬簮瀛楃涓瞙andleStr涓殑xml鏍囩杞寲鎴恏tml鏍囩</p> Change HTML special char in
   * String The first parament is father string (String). return a String.
   */
  public static String htmlspecialchars(String handleStr) {
    if (handleStr == null)
      return "";

    String str = handleStr;
    str = replace(str, "&", "&amp;");
    str = replace(str, "\"", "&quot;");
    str = replace(str, "<", "&lt;");
    str = replace(str, ">", "&gt;");

    if (!bTextBox)
      str = replace(str, "\n", "<br>");

    return str;
  }
  
  public static String specialcharsToHtml(String handleStr){
	  if (handleStr == null)
	      return "";

	    String str = handleStr;
	    str = replace(str, "&amp;", "&");
	    str = replace(str, "&quot;", " \"");
	    str = replace(str, "&lt;", "<");
	    str = replace(str, "&gt;", ">");
	    return str;
  }


  /**
   * <p>Description </p>
   */
  public static String ReturnChar2BR(String handleStr) {
    String str = handleStr;
    str = replace(str, "\n", "<br>");
    return str;
  }

  /**
   * Implode String The first parament is father Vector (String). The second parament is
   * separator (String). return a String.
   */
  @SuppressWarnings("unchecked")
public static String implode(Vector handler, String separator) {
    StringBuffer strbuf = new StringBuffer();
    try {
      if (!handler.isEmpty()) {
        int len = handler.size();
        for (int loopi = 0; loopi < len; loopi++) {
          strbuf.append((String) handler.get(loopi));
          if (loopi != len - 1)
            strbuf.append(separator);
        }
      }
    } catch (Exception error) {
      error.printStackTrace();
    }
    return strbuf.toString();
  }

  /**
   * Return appointed String from a String Vector param1: String Vector param2: appointed
   * Index param3: include Excel CSV process.
   */
  @SuppressWarnings("unchecked")
public static String getField(Vector vt, int i, boolean isExcel) {
    String str = "";
    try {
      str = (String) vt.get(i);
      if (str != null && str.length() > 2 && isExcel) {
        if (str.substring(0, 1).compareTo("\"") == 0) {
          str = str.substring(1, str.length() - 1);
          str = StringUtil.replace(str, "\"\"", "\"");
        }
      }
    } catch (ArrayIndexOutOfBoundsException aibe) {
      System.out.println("Exceed the length of array, Please check the field number");
      return "";
    }
    return str;
  }

  /**
   * fill in inschar in string's left or right, in order to let string have appoint
   * length. param1: father string param2: need fill in char param3: 0 is left fill in 1
   * is right fill in param4: total string length after fill in char
   */
  public static String InsStr(String str, String InsChar, int intDirect, int Len) {
    int intLen = str.length();
    StringBuffer strBuffer = new StringBuffer(str);

    if (intLen < Len) {
      int inttmpLen = Len - intLen;
      for (int i = 0; i < inttmpLen; i++) {
        if (intDirect == 1) {
          str = str.concat(InsChar);
        } else if (intDirect == 0) {
          strBuffer.insert(0, InsChar);
          str = strBuffer.toString();
        }
      }
    }
    return str;
  }


  /**
   * <p>Description </p>
   */
  public static int searchdiv(String str, String divided) {
    String tmpsearchstr = new String();
    tmpsearchstr = str;
    divided = divided.trim();
    int divpos = -1;

    if (tmpsearchstr != "") {
      divpos = tmpsearchstr.indexOf(divided);

      return divpos;
    } else
      return 0;
  }

  public static String ExtractStr(String str, String startdiv, String enddiv) {
    int startdivlen = startdiv.length();
    str = str.trim();

    int startpos = -1;
    int endpos = -1;

    startdiv = startdiv.trim();
    enddiv = enddiv.trim();
    startpos = searchdiv(str, startdiv);
    if (str != "") {
      if (startpos >= 0) {
        str = str.substring(startpos + startdivlen);
        str = str.trim();
      }
      endpos = searchdiv(str, enddiv);
      if (endpos == -1) return "";
      str = str.substring(0, endpos);
      str = str.trim();
    }
    return str;
  }
  
  public static String isNullAndZero(String str) {
	    if (str == null || "null".equals(str) || "0".equals(str))
	      return "";
	    else if (str.length() == 0)
	      return "";
	    else
	      return str;
	  }


  /**
   * <p>Description </p>
   */
  public static String isNull(String str) {
    if (str == null || "null".equals(str))
      return "";
    else if (str.length() == 0)
      return "";
    else
      return str;
  }
  
  /**
   * <p>Description </p>
   */
  public static Integer isZero(String str) {
	  if (str == null)
		  return 0;
	  else if (str.length() == 0)
		  return 0;
	  else
		  return Integer.parseInt(str);
  }
  

  /**
   *  椤甸潰琛ㄥ崟鐨勬煇浜涘瓧绗︿覆褰撲负""鏃惰浆鍖栦负NULL
   */
  public static String isString(String str){
	    if (str != null && !str.equals(""))
	      return str;
	    else
	      return null;
  }

  /**
   * 涓轰簡澶勭悊椤甸潰Form 鎻愪氦鐨勮〃鍗曞璞?(浠ヤ笅鏄敤鏉ュ鐞嗙殑鏂规硶)
   *
   * @param request
   * @param s 鍙傛暟鍚嶇О
   *
   * @return 鍙傛暟鍊?
   */
  public static String getString(HttpServletRequest request, String s) {
    String s1 = request.getParameter(s);
    if (s1 != null && !s1.equals(""))
      return s1;
    else
      return null;
  }

  /**
   * @param request
   * @param s       鍙傛暟鍚嶇О
   * @param s1      if s=null 鏃?鍙傛暟鍊約1
   *
   * @return 鍙傛暟鍊?
   */
  public static String getString(HttpServletRequest request, String s, String s1) {
    String s2 = getString(request, s);
    if (s2 == null)
      s2 = s1;
    return s2;
  }

  /**
   * @param request
   * @param s       鍙傛暟鍊?
   *
   * @return 杩斿洖鍊肩被鍨嬫槸鏁村瀷
   */
  public static int getInt(HttpServletRequest request, String s) {
    try {

      return Integer.parseInt(getString(request, s));

    } catch (NumberFormatException numberformatexception) {

      System.out.println(
          "com.ving.sbzl.StringUtil ERROR getInt(HttpServletRequest request, String s)");
      numberformatexception.printStackTrace();
      return 0;
    }
  }

  /**
   * @param request
   * @param s       鍙傛暟鍊?
   * @param i       if s=null 鏃?鍙傛暟鍊?
   *
   * @return 杩斿洖鍊肩被鍨嬫槸鏁村瀷
   */
  public static int getInt(HttpServletRequest request, String s, int i) {
    try {
      String s1 = getString(request, s);
      if (s1 == null)
        return i;
      else
        return Integer.parseInt(s1);
    } catch (NumberFormatException numberformatexception) {
      System.out.println(
          "com.ving.sbzl.StringUtil ERROR getInt(HttpServletRequest request, String s, int i)");
      numberformatexception.printStackTrace();
    }
    return 0;
  }
  
  public static int getInt(String s, int i) {
	    try {
	      String s1 = s;
	      if (isEmpty(s1))
	        return i;
	      else
	        return Integer.parseInt(s1);
	    } catch (NumberFormatException numberformatexception) {
	      System.out.println(
	          "com.ving.sbzl.StringUtil ERROR getInt(HttpServletRequest request, String s, int i)");
	      numberformatexception.printStackTrace();
	    }
	    return 0;
	  }

  public static long getLong(HttpServletRequest request, String s, long i) {
    try {
      String s1 = getString(request, s);
      if (s1 == null)
        return i;
      else
        return Long.parseLong(s1);
    } catch (NumberFormatException numberformatexception) {
      System.out.println(
          "com.ving.sbzl.StringUtil ERROR getLong(HttpServletRequest request, String s, long  i)");
      numberformatexception.printStackTrace();
    }
    return 0;
  }

  public static String[] getStrArray(HttpServletRequest request, String s) {

    return request.getParameterValues(s);

  }

  public static int[] getIntArray(HttpServletRequest request, String s) {

    String[] s1 = getStrArray(request, s);
    int[] j = null;

    if (s1 != null) {
      for (int i = 0; i < s1.length; i++) {
        if (i == 0) j = new int[s1.length];
        j[i] = Integer.parseInt(s1[i]);
      }
    }
    return j;
  }


  
  public static String formatDate(String date){
    if(date==null||date.indexOf("1900-01-01")!=-1||date.equals(""))
      date = "";
    else if(date.length()>=10)
      date=date.substring(0,10);
      
    return date;    
  }
  
  
  /** Check whether strings is empty. */
	public static boolean isEmpty(String s) {
		return ((s == null) || (s.length() == 0));
	}
	
	public static boolean isInit(String s) {
		return ((s == null) || (s.length() == 0) || s.equals("0"));
	}
	
	public static boolean isEmpty(Object obj) {
		return ((obj == null) || (obj.equals(""))|| new Integer("0") == obj);
	}
	
	/** Check whether List list is Empty */
	public static boolean isEmpty(List list) {
		return (list == null || list.size() == 0 );
	}
	
	/** Check whether String s is number */
	public static boolean isNumeric(String s) {
		if (isEmpty(s))
			return false;
	
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	/** Check whether string s is NOT empty. */
	public static boolean isNotEmpty(String s) {
		return (!isEmpty(s));
	}
	
	public static  boolean isNotInit(String s){
		return (!isInit(s));
	}
	
	/** Check whether List list is NOT empty */
	public static boolean isNotEmpty(List list) {
		return (!isEmpty(list));
	}
	
	/** Check whether List list is NOT empty */
	public static boolean isNotEmpty(Object obj) {
		return (!isEmpty(obj));
	}
  
	public static String getPercentage(int a, int b ){
		if(b == 0)
			return "0.00%";
		float aF = Float.valueOf(String.valueOf(a));
		float bF = Float.valueOf(String.valueOf(b));
		
		return new BigDecimal(aF / bF).setScale(4,BigDecimal.ROUND_HALF_UP).
			movePointRight(2) + "%" ;
	}
	
	//.......
	public static String getOrderNo(){
        long No = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSS");
        String nowdate = sdf.format(new Date());
        No = Long.parseLong(nowdate)*1000;//杩欓噷濡傛灉涓?ぉ璁㈠崟澶氱殑璇濆彲浠ョ敤涓?竾鎴栨洿澶? 
        int rand=(int) (Math.random()*9000+1000);
        return No+rand+"";
    }

	
	// java 瑙ｇ爜escape
	public static String unescape(String src) {
		if (src == null)
			return null;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src
							.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src
							.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}
	
	/**
	 * 鎴彇瀛楃涓层?瓒呭嚭鐨勫瓧绗︾敤symbol浠ｆ浛 銆??
	 * 
	 * @param len
	 *            銆?瓧绗︿覆闀垮害銆?暱搴﹁閲忓崟浣嶄负涓?釜GBK姹夊瓧銆??涓や釜鑻辨枃瀛楁瘝璁＄畻涓轰竴涓崟浣嶉暱搴?
	 * @param str
	 * @param symbol
	 * @return
	 */
	public static String getLimitLengthString(String str, int len, String symbol) {
		int iLen = len * 2;
		int counterOfDoubleByte = 0;
		String strRet = "";
		try {
			if (str != null) {
				byte[] b = str.getBytes("UTF-8");
				if (b.length <= iLen) {
					return str;
				}
				for (int i = 0; i < iLen; i++) {
					if (b[i] < 0) {
						counterOfDoubleByte++;
					}
				}
				if (counterOfDoubleByte % 2 == 0) {
					strRet = new String(b, 0, iLen, "UTF-8") + symbol;
					return strRet;
				} else {
					strRet = new String(b, 0, iLen - 1, "UTF-8") + symbol;
					return strRet;
				}
			} else {
				return "";
			}
		} catch (Exception ex) {
			return str.substring(0, len);
		} finally {
			strRet = null;
		}
	}

	/**
	 * 鎴彇瀛楃涓层?瓒呭嚭鐨勫瓧绗︾敤symbol浠ｆ浛 銆??
	 * 
	 * @param len
	 *            銆?瓧绗︿覆闀垮害銆?暱搴﹁閲忓崟浣嶄负涓?釜GBK姹夊瓧銆??涓や釜鑻辨枃瀛楁瘝璁＄畻涓轰竴涓崟浣嶉暱搴?
	 * @param str
	 * @return12
	 */
	public static String getLimitLengthString(String str, int len) {
		return getLimitLengthString(str, len, "...");
	}
	
	/**
	 * 灏嗘棩鏈熻浆鎹㈡垚瀛楃涓?
	 * @param date
	 * @return
	 */
	public static String timeTostr(Date date){
		String strDate="";
		if(date!=null){
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal=Calendar.getInstance();
			cal.setTime(date);			
			if(cal.get(Calendar.HOUR_OF_DAY)==0 && cal.get(Calendar.MINUTE)==0 && cal.get(Calendar.SECOND)==0){
				format=new SimpleDateFormat("yyyy-MM-dd");
			}
			strDate=format.format(date);		
		}
		return strDate;
	}
	
	public static java.sql.Date strTotime(String s){
		java.sql.Date strDate=null;
		if (isEmpty(s))
	        return null;
	    else{
	    	SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
	    	try {
	    		Date tem=sdf.parse(s);
	    		java.sql.Date date =new java.sql.Date(tem.getTime());	
	    		return date;
			} catch (ParseException e) {
				System.out.println( "com.ving.sbzl.StringUtil ERROR strTotime("+s+")");
				e.printStackTrace();
			}
	    }
		return strDate;
	}
	
	/**
	 * 杩斿洖瀛愪覆鐨勪釜鏁?
	 * @param source    婧愬瓧绗︿覆
	 * @param substring  瀛愪覆
	 * @return
	 */
	public int getSubStringCount(String source,String substring){
		String sourceTemp = source;
		int count = 0;//鍒ゆ柇鏄惁涓哄ご鏉′俊鎭?
		int index = -1;
		while((index = sourceTemp.indexOf(substring))!=-1){
			sourceTemp = sourceTemp.substring(index+1,sourceTemp.length());
			count++ ;
		}
		return count;
	}
	
	public static String PadLeft(String fmt,Integer intHao){          
        DecimalFormat df = new DecimalFormat(fmt);  
        return df.format(intHao);  
    }
	
	public static String PadRight(int fmt,int intHao){          
        String sfmt= "d%0"+String.valueOf(fmt);
        return  String.format("%06d", intHao);
    }
}
