package com.weekendceo.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StringUtil {
	
	protected static final Log log = LogFactory.getLog(StringUtil.class);

    private StringUtil() {
    	
    }

    public static String nvl(String str) {
		return str == null ? "" : str;
	}

	public static String nvl(String str, String defaultVal) {
		return str == null ? defaultVal : str;
	}
	
	public static Object nvl(Object obj, Object obj2){
		return ( obj != null ) ? obj : obj2;
	}
	
	public static Object nvl(Object obj){
		return nvl(obj, "" );
	}	
	
	public static boolean isEmpty(String str){
		boolean flag;
		flag = (str == null || str.equals("")) ? true: false;		
		return flag;
	}

	/**
	 * 복수건에 대한 equals 함수 (OR 조건)
	 * @param compareStandard	비교 기준 문자열
	 * @param targets			비교 대상 문자열(복수건)
	 * @return
	 * @author 임준혁
	 */
	public static boolean equalsAtLeast(String compareStandard,
			String... targets) {
		if(StringUtil.isEmpty(compareStandard)){
			return false;
		}
		for (String target : targets) {
			if (compareStandard.equals(target)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 복수건에 대한 equalsIgnoreCase 함수 (OR 조건)
	 * @param compareStandard	비교 기준 문자열
	 * @param targets			비교 대상 문자열(복수건)
	 * @return
	 * @author 임준혁
	 */
	public static boolean equalsIgnoreCaseAtLeast(String compareStandard,
			String... targets) {
		if(StringUtil.isEmpty(compareStandard)){
			return false;
		}
		for (String target : targets) {
			if (compareStandard.equalsIgnoreCase(target)){
				return true;
			}
		}
		return false;
	}

	/**
	 * 비어있는 문자열(<code>""</code>)은 null로 전환하고 다른 문자는 그대로 리턴합니다.
	 * @author 임준혁
	 */
	public static String emptyToNull(String str) {
		if ("".equals(str)) {
			return null;
		}else{
			return str;
		}
	}
	

	/**
	 * inputString 문자열의 왼쪽에 format 문자를 채워서 length 길이로 변경
	 * 
	 * @param inputString
	 *            입력 문자열
	 * @param length
	 *            변경 결과 문자열의 총길이
	 * @param format
	 *            총길이로 변경하기 위해 채울 문자
	 * @return String 변경된 문자열
	 */
	public static String lpad(String inputString, int length, char format) {
		int padding_length = length - inputString.length();

		if (padding_length <= 0) {
			return inputString;
		}

		StringBuffer returnString = new StringBuffer();
		for (int i = 0; i < padding_length; i++) {
			returnString.append(format);
		}
			
		returnString .append(inputString);
		return returnString.toString();
	}

	/**
	 * inputString 문자열의 오른쪽에 format 문자를 채워서 length 길이로 변경
	 * 
	 * @param inputString
	 *            입력 문자열
	 * @param length
	 *            변경 결과 문자열의 총길이
	 * @param ch
	 *            총길이로 변경하기 위해 채울 문자
	 * @return String 변경된 문자열
	 */
	public static String rpad(String str, int length, char ch) {

		String strParam = str;
		if (strParam == null){
			strParam = "";
		}
		StringBuilder sb = new StringBuilder(strParam);

		for (int i = 0; i < length - strParam.length(); i++) {
			sb.append(ch);
		}
		return sb.toString();
	}

	/**
	 * 입력된 변수중 String.compareTo로 비교하여 최대 값을 얻는다. 오라클 GREATEST 대체함수
	 * 
	 * @param args
	 * @author 이기춘
	 * @return
	 */
	public static String greatest(String... args) {
		String maxVal = "";
		for (String string : args) {
			if(string == null)
				continue;
			if (maxVal.compareTo(string) < 0){
				maxVal = string;
			}
		}
		return maxVal;
	}

	/**
	 * 카멜케이스된 문자열(변수명)을 컬럼명 스타일의 문자열(대문자와 단어 사이의 구분은 Underbar)로 바꾼다.
	 * @param camelCaseString (as a variable name)
	 * @return	COLUMN_NAME_STYLED_STRING
	 * @author 이기춘
	 */
	public static String toColumnName(String camelCaseString) {
		return camelCaseString.replaceAll("([A-Z])", "_$1").toUpperCase();
	}
	
	/**
	 * 복수건에 대한 startsWith 함수 (OR 조건)
	 * @param compareStandard	비교 기준 문자열
	 * @param targets			비교 대상 문자열(복수건)
	 * @return
	 * @author 임준혁
	 */
	public static boolean startsWithAtLeast(String compareStandard,
			String... targets) {
		if(StringUtil.isEmpty(compareStandard)){
			return false;
		}
		for (String target : targets) {
			if (compareStandard.startsWith(target)){
				return true;
			}
		}
		return false;
	}

	/**
	 * 문자열을 연결시켜준다.
	 * @param delim
	 * @param strings
	 * @return
	 */
	public static String concatenate(String delim, String... strings) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(strings[0]);
		for (int i = 1; i < strings.length; i++) {
			stringBuilder.append(delim).append(strings[i]);
		}
		return stringBuilder.toString();
	}
	
	public static String concatenateNotEmpty(String delim, String... strings) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(nvl(strings[0]));
		for (int i = 1; i < strings.length; i++) {
			if(!isEmpty(strings[i])){
				stringBuilder.append(delim).append(strings[i]);
			}
		}
		return stringBuilder.toString();
	}
	
	/**
	 * 날짜형식 문자열의 구분자로 들어있는 ., - 등을 제거한다. 
	 * @param dateString
	 * @return
	 */
	public static String removeDateDelim(String dateString){
		if(StringUtil.isEmpty(dateString)){
			return "";
		}
		return dateString.replaceAll("(\\.|-)", "");
	}
	
	public static String ing(Object... objs) {
		StringBuffer sb = new StringBuffer();
		for (Object obj : objs) {
			sb.append(obj);
		}
		return sb.toString();
	}
	
	/**
	 * Concatenate the strings	(protected from 'varargs' warnings)
	 * @param objs Pieces of Strings for concat'n
	 * @return
	 */
	public static String ing(String... objs) {
		StringBuffer sb = new StringBuffer();
		for (Object obj : objs) {
			sb.append(obj);
		}
		return sb.toString();
	}
	
	/**
	 * 정해진 length만큼 문자열을 잘라서 배열로 리턴한다.
	 * @param src		원본문자열 
	 * @param length	자르는 길이
	 * @return String[]
	 * @author 정명교
	 */
	public static String[] splitEqualSize(String src, int length){
		return src.split(ing("(?<=\\G.{", length, "})"));
	}
	
	/**
	 * 오라클 SQL 함수 <code>DECODE()</code>처럼 구현하였습니다.
	 * <blockquote><pre>
	 * <code>StringUtil.decode("1", "1", "A")</code> returns "A"
	 * <code>StringUtil.decode("0", "1", "A", "ZZZ")</code> returns "ZZZ"
	 * <code>StringUtil.decode("2", "1", "A", "2", "B")</code> returns "B"
	 * <code>StringUtil.decode("0", "1", "A", "2", "B", "ZZZ")</code> returns "ZZZ"
	 * <code>StringUtil.decode("3", "1", "A", "2", "B", "3", "C")</code> returns "C"
	 * <strong><code>StringUtil.decode("4", "1", "A", "2", "B", "3", "C")</code> returns null</strong>
	 * <code>StringUtil.decode("0", "1", "A", "2", "B", "3", "C", "ZZZ")</code> returns "ZZZ"
	 * </pre></blockquote>
	 * @param standard			기준되는 문자열
	 * @param firstCompareValue	첫번째 비교대상 문자열
	 * @param firstReplaceStr	첫번째 교체 문자열
	 * @param others			그 이후
	 * @return	바뀌어진 문자열
	 * @author 임준혁
	 */
	public static String decode(String standard, String firstCompareValue, String firstReplaceStr, String... others) {
		if(firstCompareValue.equals(standard)) {
			return firstReplaceStr;
		}
		for(int i = 0; i < others.length; i += 2) {
			if(i == others.length - 1) {
				return others[i];
			} else if(others[i].equals(standard)) {
				return others[i + 1];
			}
		}
		return null;
	}
	
	/**
	 * contains 함수 복수건
	 * @param standard		비교 대상
	 * @param targets		비교할 문자열
	 * @return
	 * @dmlType [dml유형정보] *DAO만 적용*
	 */
	public static boolean containsAtLeast(String target, String... strings) {
		for (String string : strings) {
			if(target.contains(string)) {
				return true;
			}
		}
		return false;
	}
    
    /**
     * @param str - String
     * @param max - int
     * @return
     */
    public static String formatTitle( String str, int max ) 
    {
        if( str.length()*2 <= max) return str;

        String tmp = null;        
        byte bTmp[] = null;
        String rets = "" ;

        for(int i=0, k=0; i < str.length(); i++) {
            tmp = str.substring( i, i+1 );
            bTmp = tmp.getBytes();
            if( bTmp.length > 1 ) {
                rets += tmp;
                k +=2;
            }  else {
                rets += tmp;
                k ++;
            }

            if( max <= k ) break;
            else if ("\n".compareTo(tmp) == 0) return rets;
        }
        
        return rets+"...";
    }

    
    public static String toNNull(String s,String y)
    {
        if(s == null)
            return y;
        if("null".equals(s))
            return y;
        if ("".equals(s))
            return y;
        else
            return s.trim();
    }

    public static String toNNull(String s)
    {
        if(s == null)
            return "";
        if("null".equals(s))
            return "";
        else
            return s.trim();
    }
    
    /**
     * @param str
     * @return
     */
    public static String getFileExtension( String str ) {
        return ( str.lastIndexOf( "." ) > 0 ) ? str.substring( str.lastIndexOf( "." )+1 ) : str;
    }    
      
    
    /**
     * @return String
     */
    public static String unFormat(String str, char ch)
    {
        StringBuffer strbuf = new StringBuffer();
        int i;

        if(str == null)         return "";
        if(str.length() == 0)   return "";

        try {
            for(i = 0; i < str.length(); i++) {
                if(str.charAt(i) != ch)
                    strbuf.append(str.charAt(i));
            }

            return strbuf.toString();
        }
        catch(Exception e) {
            
            return "";
        }
    }
    
    public static boolean inArray( String value, String[] str ) {
        boolean result = false;
        for( int i=0;i<str.length;i++ ) {
            if( str[i].equals(value) ) {
                result = true;
                break;
            }
        }
        return result;
    }
    
    /**
     * @param value
     * @param len
     * @return
     */
    public static String fillZero(String value, int len){
        if (len > value.length())
        {
            int num = len - value.length();
            for(int i=0; i < num ; i++)
                    value= "0" + value;
            return value;
        }
        return value;
    } 
    
	public static String getBetweenStr(String str, String val) {
	    String startStr =null, endStr = null, replymsg = null;	    
	    try {
			startStr = "<" + val + ">";
			endStr = "</" + val + ">"; 
	        replymsg = str.substring(str.indexOf(startStr)+startStr.length(), str.indexOf(endStr));
	        
	    } catch (StringIndexOutOfBoundsException siofbe) {
	        log.debug("catch StringIndexOutOfBoundsException");
	    } catch (Exception e) {
	        log.debug("catch Exception");
	    }
	    return replymsg;
	}    

	
	/** 
	 * 설명 :  파일명 특수문자 검사
	 * @param path
	 * @return
	 * @return String
	*/
	public static String fileNameCheck(String fileNm){
        
	    try {
    	    if( !"".equals(fileNm) && fileNm != null ){
    	        fileNm = fileNm.replaceAll("/", "");
    	        fileNm = fileNm.replaceAll("\\\\", "");
    	        fileNm = fileNm.replaceAll("&", "");
    	    }
        } catch (Exception e) {
               log.debug("fileNameCheckFileName=>"+fileNm);
        }
	    return fileNm;
	}
	
	
	/** 
	 * 설명 :  파일 경로 특수무자 검새
	 * @param filePath
	 * @param er
	 * @return
	 * @return String
	*/
	public static String filePathCheck(String filePath){
	    
	    try {
            if( !"".equals(filePath) && filePath != null ) {
                filePath = filePath.replaceAll("/", "");
                filePath = filePath.replaceAll("\\", "");
                filePath = filePath.replaceAll(".", "");
                filePath = filePath.replaceAll("&", "");
            }
	        
        } catch (Exception e) {
            log.debug("filePathCheck filePath=>"+filePath);
        }
        
	    return filePath;
	}
	
    /** 
     * 설명 :  2009.04.30 신경훈 : 두개의 String을 받아 문자단위로 서로 비교해서 틀린 문자수 반환
     * @param a
     * @param b
     * @return
     * @return int
    */
	public static int strCompare(String a, String b) {

        String str1 = "";
        String str2 = "";
        int cnt = 0;
        
        for (int i=0; i<a.length(); i++)    {
            str1 = a.substring(i, i+1);
            str2 = b.substring(i, i+1);
            if (!str1.equals(str2)) {
                cnt++;
            }
        }
        return cnt;
    }
	
	/** 
	 * 설명 :   egovframework.rte.ptl.mvc.filter.HTMLTagFilter 에 바꾸는 5가지 특수문자 다시 원복 
	 * @param parameter
	 * @return
	 * @return String
	*/
	public static String getXSSParameter(String parameter) {

        String value = parameter;

        if (value == null) {
            return null;
        }

        //현재 egovframework.rte.ptl.mvc.filter.HTMLTagFilter 에서 변환하는 특수 문제는 아래의 다섯개 역으로 다시 바꺼준다.
        value = value.replaceAll("&lt;", "<");
        value = value.replaceAll("&gt;", ">");
        value = value.replaceAll("&amp;", "&");
        value = value.replaceAll("&quot;", "\"");
        value = value.replaceAll("&apos;", "'");

        return value;
    }
	
	/**
	 * <pre>
	 * 1. 개요 : 전화번호 00011112222 에 하이픈 추가
	 * 2. 처리내용 : 전화번호 00011112222 에 하이픈 추가
	 * </pre>
	 * @Method Name : selectUserPhon
	 * @date : 2019. 10. 17.
	 * @author : 뷰아떼1
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 10. 17.		뷰아떼1				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param String
	 * @return String
	 * @throws 
	 */
	public static String phone(String src) {
		if (src == null) {
			return "";
		}
		if (src.length() == 8) {
			return src.replaceFirst("^([0-9]{4})([0-9]{4})$", "$1-$2");
		} else if (src.length() == 12) {
			return src.replaceFirst("(^[0-9]{4})([0-9]{4})([0-9]{4})$", "$1-$2-$3");
		}
		return src.replaceFirst("(^02|[0-9]{3})([0-9]{3,4})([0-9]{4})$", "$1-$2-$3");
	}
}