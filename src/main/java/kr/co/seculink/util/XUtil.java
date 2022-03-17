package kr.co.seculink.util;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.servlet.ModelAndView;

import kr.co.seculink.web.excel.ExcelConstant;
import kr.co.seculink.web.model.cmon.ExcelVO;



/**
 * Framework 구동시 사용되는 Utility 클래스.
 */
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
public class XUtil {
    private static int      lastPos = 0;
    private static String   caller  = null;
    
    public static void setXUtilCaller( String caller ) {
        XUtil.caller = caller;
    }
    
    private static String       DEFAULT_DATE_FORMAT = "YYYYMMDD";
    private static String       DEFAULT_TIME_FORMAT = "HHMMSS";
    
    //--------------------------------------------------------------------//
    //             데이터 형변환 및 문자열/숫자열 유형별 처리모음                  //
    //--------------------------------------------------------------------//
    
    /**
     * 공백문자
     */
    public final static char    WHITE_SPACE         = ' ';
    
    static String               m_whiteSpace        = " \t\n\r";
    static char                 m_citChar           = '"';
    
    private static StringWriter sw                  = new StringWriter();
    private static PrintWriter  pw                  = new PrintWriter( sw );
    
    //private static Logger log = Logger.getLogger(Util.class.getName());
    
    public static String changeStringFormat( String str ) {
        char[] charArr = new char[str.length()];
        
        str.getChars( 0, str.length(), charArr, 0 );
        
        String retVal = "";
        
        for ( int i = 0; i < charArr.length; i++ ) {
            String byteChar = String.valueOf( charArr[i] );
            
            if ( XUtil.capitalize( byteChar ).equals( byteChar ) ) {
                retVal = retVal + "_";
            }
            retVal = retVal + byteChar.toUpperCase();
        }
        
        return retVal;
    }
    
    /**
     * 메소드명 : spaceToNull
     * 설명 : 객체내의 모든 공백값을 null값으로 변경
     * 메소드인수1 : Map<String,String> sddo - 변환할 Map<String,String>
     * 메소드리턴값 : Map<String,String> retDo - 변환된 Map<String,String>
     */
    public static Map<String,String> spaceToNull( Map<String,String> sddo ) {
        try {
            Map<String,String> retDo = new HashMap<String,String>();
            
            if ( sddo == null ) return retDo;
            
            Iterator iterator = sddo.entrySet().iterator();
            
            while ( iterator.hasNext() ) {
                Map.Entry e = (Map.Entry) iterator.next();
                String key = (String) e.getKey();
                Object obj = (Object) e.getValue();
                String sData = null;
                if ( obj != null ) {
                    if ( obj instanceof String ) {
                        sData = (String) obj;
                        if ( getString( sData ).length() == 0 ) {
                            sData = null;
                        }
                        retDo.put( key, sData );
                    } else {
                        retDo.put( key, obj.toString() );
                    }
                }
            }
            return retDo;
        } catch ( Exception e ) {
            e.printStackTrace();
            return sddo;
        }
    }
    
    
    // --------------------------------------------------------------------//
    // 데이터 형변환 및 문자열/숫자열 유형별 처리모음                      //
    // --------------------------------------------------------------------//
    
    /**
     * 공백문자
     */
    
    // private static Logger log = Logger.getLogger(Util.class.getName());
    /**
     * 메소드명 : isDigit
     * 설명 : 문자열이 숫자만으로 구성되어있는지를 확인
     * 메소드인수1 : String number - 확인할 문자열
     * 메소드리턴값 : boolean - 숫자여부
     */
    public static boolean isDigit( String str ) {
        return isDigit( str, false );
    }
    
    // private static Logger log = Logger.getLogger(Util.class.getName());
    /**
     * 메소드명 : isDigit
     * 설명 : 문자열이 숫자만으로 구성되어있는지를 확인
     * 메소드인수1 : String number - 확인할 문자열
     * 메소드인수2 : boolean isDouble - double 형인지 여부
     * 메소드리턴값 : boolean - 숫자여부
     */
    public static boolean isDigit( String str,boolean isDouble ) {
        boolean isDigit = false;
        try {
            if ( str != null ) {
                double num = Double.parseDouble( str.trim() );
                isDigit = true;
            }
        } catch ( NumberFormatException e ) {
            e.printStackTrace();
            isDigit = false;
        }
        
        return isDigit;
    }
    
    /**
     * 메소드명 : trim
     * 설명 : 공백제거
     * 메소드인수1 : String data - 공백제거 처리할 값
     * 메소드리턴값 : String result - 공백제거 처리된 값
     */
    public static String trim( String data ) {
        String result = null;
        if ( data != null ) {
            result = data.trim();
        }
        return result;
    }
    
    /**
     * 메소드명 : trimToStr
     * 설명 : 공백제거, 인수가 null 이면 ""(빈스트링) 으로 바꿈
     * 메소드인수1 : String data - 공백제거 처리할 값
     * 메소드리턴값 : String result - 공백제거 처리된 값
     */
    public static String trimToStr( String data ) {
        String result = "";
        if ( data != null ) {
            result = data.trim();
        }
        return result;
    }
    
    /**
     * 메소드명 : replaceStr
     * 설명 : 주어진 value(String)의 특정문자열을 대상문자열로 변환
     * 메소드인수1 : String value - 주어진 문자열
     * 메소드인수2 : String oldStr - 변환하고자 하는 특정 문자열
     * 메소드인수3 : String newStr - 변환하고자 하는 대상 문자열
     * 메소드리턴값 : String - 특정문자열이 변환된 값 리턴
     */
    public static String replaceStr( String value,String oldStr,String newStr ) {
        int idx = 0;
        int curIdx = 0;
        StringBuffer result = new StringBuffer();
        
        try {
            curIdx = value.indexOf( oldStr, idx );
            while ( curIdx >= 0 ) {
                // Replace string and append string.
                result.append( value.substring( idx, curIdx ) );
                result.append( newStr );
                // Increment search the string index.
                idx = curIdx + oldStr.length();
                curIdx = value.indexOf( oldStr, idx ); // Add this line, this is the fixed point.
            }
            
            // After replace all of the oldStr, then if the string remains...
            // append it to result string.
            if ( idx <= value.length() ) {
                result.append( value.substring( idx, value.length() ) ); // this..
            }
        } catch ( Exception e ) {
            e.printStackTrace();
            return value;
        }
        
        return result.toString();
    }
    
    /**
     * 메소드명 : convertDateStr
     * 설명 : 주어진 String의 날짜형식 변환
     * 메소드인수1 : String dateStr - format에 맞출 문자열
     * 메소드리턴값 : String - format에 맞춰 변경된 문자열
     */
    public static String convertDateStr( String dateStr ) {
        String result = null;
        String year = null;
        String month = null;
        String day = null;
        
        try {
            if ( dateStr == null || (dateStr.length() != 6 && dateStr.length() != 8) ) {
                return dateStr;
            }
            year = dateStr.substring( 0, 4 );
            month = dateStr.substring( 4, 6 );
            if ( dateStr.length() == 6 ) {
                return year + "-" + month;
            }
            day = dateStr.substring( 6 );
        } catch ( Exception e ) {
            e.printStackTrace();
            return dateStr;
        }
        return year + "-" + month + "-" + day;
    }
    
    /**
     * 메소드명 : dgtFormat
     * 설명 : String => String, 숫자형 문자값을 기준 소숫점 자리에서 절사후 문자값으로 리턴
     * 메소드인수1 : String sDecimal - 입력받은 숫자형 문자값
     * 메소드인수2 : int iDgtCnt - 기준 소숫점 자릿수
     * 메소드인수3 : int format - 올림, 반올림, 버림 여부
     * 메소드인수4 : String isNullToZero - 값이 널인 경우 "0"으로 리턴할지 여부
     * 메소드리턴값 : String sRslt - 처리된 문자값
     */
    public static String dgtFormat( String sDecimal,int iDgtCnt,int format,String isNullToZero ) {
        String sRslt = null;
        try {
            if ( sDecimal != null ) {
                sDecimal = trim( sDecimal );
                if ( sDecimal.length() > 0 ) {
                    BigDecimal bd = new BigDecimal( sDecimal );
                    bd = bd.setScale( iDgtCnt, format );
                    sRslt = bd.toString();
                }
            }
            
            if ( isNullToZero.equals( "Y" ) && sRslt == null ) // 널이면 0으로 바꾸어야 할 경우
            {
                sRslt = "0";
            }
        } catch ( Exception e ) {
            e.printStackTrace();
            return sDecimal;
        }
        return sRslt;
    }
    
    /**
     * 메소드명 : dgtFormat
     * 설명 : String => String, 숫자형 문자값을 기준 소숫점 자리에서 절사후 문자값으로 리턴
     * 메소드인수1 : String sDecimal - 입력받은 숫자형 문자값
     * 메소드인수2 : int iDgtCnt - 기준 소숫점 자릿수
     * 메소드인수3 : int format - 올림, 반올림, 버림 여부
     * 메소드리턴값 : String - 처리된 문자값
     */
    public static String dgtFormat( String sDecimal,int iDgtCnt,int format ) {
        return dgtFormat( sDecimal, iDgtCnt, format, "Y" );
    }
    
    /**
     * 메소드명 : toHalfChar
     * 설명 : 반각문자로 변환하기
     * 메소드인수1 : String src - 변경할 스트링
     * 메소드리턴값 : String - 변경된 스트링
     */
    public static String toHalfChar( String src ) {
        StringBuffer strBuf = new StringBuffer();
        try {
            char c = 0;
            int nSrcLength = src.length();
            for ( int i = 0; i < nSrcLength; i++ ) {
                c = src.charAt( i );
                // 영문이거나 특수 문자 일경우.
                if ( c >= '！' && c <= '～' ) {
                    c -= 0xfee0;
                } else if ( c == '　' ) {
                    c = 0x20;
                }
                // 문자열 버퍼에 변환된 문자를 쌓는다
                strBuf.append( c );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
        return strBuf.toString();
    }
    
    /**
     * 메소드명 : toFullChar
     * 설명 : 전각문자로 변환하기
     * 메소드인수1 : String src - 변경할 스트링
     * 메소드리턴값 : String - 변경된 스트링
     */
    public static String toFullChar( String src ) {
        StringBuffer strBuf = new StringBuffer();
        try {
            char c = 0;
            int nSrcLength = src.length();
            for ( int i = 0; i < nSrcLength; i++ ) {
                c = src.charAt( i );
                // 영문이거나 특수 문자 일경우.
                if ( c >= '！' && c <= '～' ) {
                    c -= 0xfee0;
                } else if ( c == '　' ) {
                    c = 0x20;
                }
                // 문자열 버퍼에 변환된 문자를 쌓는다
                strBuf.append( c );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
        return strBuf.toString();
    }
    
    /**
     * 메소드명 : alignLeft
     * 설명 : 문자열을 좌측 정렬 하기
     * 메소드인수1 : String src - 원본 문자열
     * 메소드인수2 : int length - 정렬이 이루어질 길이
     * 메소드인수3 : boolean isEllipsis - 마지막에 줄임표("...")의 여부
     * 메소드리턴값 : String - 변경된 스트링
     */
    public static String alignLeft( String src,int length,boolean isEllipsis ) {
        try {
            if ( src.length() <= length ) {
                
                StringBuffer temp = new StringBuffer( src );
                for ( int i = 0; i < (length - src.length()); i++ ) {
                    temp.append( ' ' );
                }
                return temp.toString();
            } else {
                if ( isEllipsis ) {
                    
                    StringBuffer temp = new StringBuffer( length );
                    temp.append( src.substring( 0, length - 3 ) );
                    temp.append( "..." );
                    return temp.toString();
                } else {
                    return src.substring( 0, length );
                }
            }
        } catch ( Exception e ) {
            e.printStackTrace();
            return src;
        }
    }
    
    /**
     * 메소드명 : alignRight
     * 설명 : 문자열을 우측 정렬 하기
     * 메소드인수1 : String src - 원본 문자열
     * 메소드인수2 : int length - 정렬이 이루어질 길이
     * 메소드인수3 : boolean isEllipsis - 마지막에 줄임표("...")의 여부
     * 메소드리턴값 : String - 변경된 스트링
     */
    public static String alignRight( String src,int length,boolean isEllipsis ) {
        try {
            if ( src.length() <= length ) {
                
                StringBuffer temp = new StringBuffer( length );
                for ( int i = 0; i < (length - src.length()); i++ ) {
                    temp.append( ' ' );
                }
                temp.append( src );
                return temp.toString();
            } else {
                if ( isEllipsis ) {
                    
                    StringBuffer temp = new StringBuffer( length );
                    temp.append( src.substring( 0, length - 3 ) );
                    temp.append( "..." );
                    return temp.toString();
                } else {
                    return src.substring( 0, length );
                }
            }
        } catch ( Exception e ) {
            e.printStackTrace();
            return src;
        }
    }
    
    /**
     * 메소드명 : capitalize
     * 설명 : 제일 처음글자를 대문자화 하기
     * 메소드인수1 : String src - 원본 문자열
     * 메소드리턴값 : String - 변경된 스트링
     */
    public static String capitalize( String src ) {
        String tmpStr = null;
        try {
            tmpStr = !src.equals( "" ) && src != null ? src.substring( 0, 1 ).toUpperCase() + src.substring( 1 ).toLowerCase() : src;
        } catch ( Exception e ) {
            e.printStackTrace();
            return src;
        }
        return tmpStr;
    }
    
    /**
     * 메소드명 : joinStr
     * 설명 : 배열을 받아 연결될 문자열로 연결하기 각 엘레멘트 사이에 구분문자열을 추가
     * 메소드인수1 : Object[] aobj - 배열
     * 메소드인수2 : String src - 구분문자열
     * 메소드리턴값 : String - 변경된 스트링
     */
    public static String joinStr( Object aobj[],String src ) {
        StringBuffer stringbuffer = new StringBuffer();
        try {
            int i = aobj.length;
            if ( i > 0 ) {
                stringbuffer.append( aobj[0].toString() );
            }
            for ( int j = 1; j < i; j++ ) {
                stringbuffer.append( src );
                stringbuffer.append( aobj[j].toString() );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
        return stringbuffer.toString();
    }
    
    /**
     * 메소드명 : splitStr
     * 설명 : 문자열을 지정된 Token Seperator로 Tokenize
     * 메소드인수1 : String src - 원문문자열
     * 메소드인수2 : String src1 - 토큰 스트링
     * 메소드리턴값 : String[] - 스트링 배열
     */
    public static String[] splitStr( String src,String src1 ) {
        try {
            StringTokenizer stringtokenizer = new StringTokenizer( src, src1 );
            int i = stringtokenizer.countTokens();
            String as[] = new String[i];
            for ( int j = 0; j < i; j++ ) {
                as[j] = stringtokenizer.nextToken();
            }
            return as;
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 메소드명 : subStr
     * 설명 : 좌우구분하여 문자열 자르기
     * 메소드인수1 : String src - 원문문자열
     * 메소드인수2 : String src1 - "left"또는 "right"
     * 메소드인수3 : int num - 길이
     * 메소드리턴값 : String - 결과 문자열
     */
    public static String subStr( String src,String src1,int num ) {
        String tmpStr = null;
        try {
            if ( src != null ) {
                int strLength = src.length();
                if ( strLength >= num ) {
                    if ( src1.equals( "left" ) ) {
                        tmpStr = src.substring( 0, num );
                    } else if ( src1.equals( "right" ) ) {
                        tmpStr = src.substring( (strLength - num) );
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            }
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
        return tmpStr;
    }
    
    /**
     * 메소드명 : convertCaseStr
     * 설명 : 대소문자 구분하기
     * 메소드인수1 : String src - 원문문자열
     * 메소드인수2 : String src1 - "lower"또는 "upper"
     * 메소드리턴값 : String - 결과 문자열
     */
    public static String convertCaseStr( String src,String src1 ) {
        String tmpStr = null;
        try {
            if ( src != null ) {
                if ( src1.equals( "lower" ) ) {
                    tmpStr = src.toLowerCase();
                } else if ( src1.equals( "upper" ) ) {
                    tmpStr = src.toUpperCase();
                } else {
                    return null;
                }
            }
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
        return tmpStr;
    }
    
    /**
     * 메소드명 : nullToSpace
     * 설명 : 공백제거, 인수가 null 이면 ""(빈스트링) 으로 바꿈 인수가 "NULL" 이면 ""(빈스트링으로)
     * 메소드인수1 : String data - 공백제거 처리할 값
     * 메소드리턴값 : String result - 공백제거 처리된 값
     */
    public static String nullToSpace( String data ) {
        String result = data;
        try {
            if ( data != null ) {
                result = data.trim();
                if ( result.equals( "NULL" ) || result.equals( "null" ) ) {
                    result = "";
                }
            } else if ( data == null || data.equals( "" ) ) {
                result = "";
            }
        } catch ( Exception e ) {
            e.printStackTrace();
            return data;
        }
        return result;
    }
    
    /**
     * 메소드명 : nullToSpace
     * 설명 :
     * 메소드인수1 : String data - 공백제거 처리할 값
     * 메소드리턴값 : String result - 공백제거 처리된 값
     */
    public static Map nullToSpace( Map sddo ) {
        Map retDo = new HashMap();
        
        try {
            Iterator iterator = sddo.entrySet().iterator();
            
            while ( iterator.hasNext() ) {
                Map.Entry e = (Map.Entry) iterator.next();
                String key = (String) e.getKey();
                Object obj = (Object) e.getValue();
                
                if ( obj instanceof String ) {
                    String sData = (String) obj;
                    retDo.put( key, nullToSpace( sData ) );
                } else if ( obj == null ) {
                    retDo.put( key, "" );
                } else {
                    retDo.put( key, obj );
                }
            }
        } catch ( Exception e ) {
            e.printStackTrace();
            return sddo;
        }
        
        return retDo;
    }
    
    /**
     * 메소드명 : removeSeparator
     * 설명 : 문자열에서 지정된 Token을 제거
     * 메소드인수1 : String src - 원문문자열
     * 메소드인수2 : String src1 - 토큰 스트링
     * 메소드리턴값 : String - 구분자가 빠진 문자열
     */
    public static String removeSeparator( String src,String src1 ) {
        try {
            StringTokenizer stringtokenizer = new StringTokenizer( src, src1 );
            int i = stringtokenizer.countTokens();
            String str = new String();
            for ( int j = 0; j < i; j++ ) {
                str += stringtokenizer.nextToken();
            }
            return str;
        } catch ( Exception e ) {
            e.printStackTrace();
            return src;
        }
    }
    
    /**
     * 메소드명 : getComma
     * 설명 : 주어진 String의 금액단위 콤마
     * 메소드인수1 : String str - format에 맞출 문자열
     * 메소드리턴값 : String - format에 맞춰 변경된 문자열
     */
    public static String getComma( String str,String format ) {
        try {
            String sj = str;
            if ( str == null ) return null;
            if ( str.trim().equals( "" ) ) return "";
            if ( str.indexOf( "," ) != -1 ) return str;
            if ( sj.startsWith( "0" ) ) {
                for ( int i = 0; i < str.length(); i++ ) {
                    sj = str.substring( i );
                    if ( !sj.startsWith( "0" ) ) break;
                }
            }
            if ( str.equals( "0" ) ) return "0";
            else {
                double nu = Double.parseDouble( sj );
                NumberFormat nf = NumberFormat.getInstance();
                ((java.text.DecimalFormat) nf).applyPattern( format );
                String no = nf.format( nu );
                return no;
            }
        } catch ( Exception e ) {
            e.printStackTrace();
            return str;
        }
    }
    
    /**
     * 메소드명 : nullToZero
     * 설명 : 공백제거, 인수가 null 이거나 "" 일경우 "0"
     * 메소드인수1 : String data - 공백제거 처리할 값
     * 메소드리턴값 : String result - null문자나 공백일경우 "0" 그외엔 공백제거 후 리턴
     */
    public static String nullToZero( String data ) {
        try {
            String result = data;
            if ( data == null ) {
                result = "0";
            } else {
                result = data.trim();
                if ( result.equals( "" ) ) {
                    result = "0";
                }
            }
            return result;
        } catch ( Exception e ) {
            e.printStackTrace();
            return data;
        }
    }
    
    /**
     * 메소드명 : byteFormat
     * 설명 : 문자열을 바이트 수에 맞게 원하는 문자를 채워 리턴하는 함수
     * 메소드인수1 : String str - 처리할 문자열
     * 메소드인수2 : int length - 총 byte 수
     * 메소드인수3 : char ch - 채울 문자
     * 메소드인수4 : boolean fillRightYn - 오를쪽으로 채울때 true, 왼쪽에 채울때 false
     * 메소드리턴값 : String rsltStr - 리턴 스트링
     */
    
    public static String byteFormat( String str,int length,char ch,boolean fillRightYn ) {
        try {
            String rsltStr = null;
            
            StringBuffer buff = new StringBuffer( str );
            
            if ( str == null ) {
                str = "";
            }
            
            if ( (str.getBytes()).length > length ) { // 처리하려는 문자열이 기준길이보다 길 경우
                                                      // 문자열이 기준보다 길면 그대로 리턴
            }
            
            if ( !fillRightYn ) {
                for ( int i = 0; i < (length - (str.getBytes()).length); i++ ) {
                    buff.insert( 0, ch );
                }
                rsltStr = buff.toString();
            } else {
                for ( int i = 0; i < (length - (str.getBytes()).length); i++ ) {
                    buff.append( ch );
                }
                rsltStr = buff.toString();
            }
            
            return rsltStr;
        } catch ( Exception e ) {
            e.printStackTrace();
            return str;
        }
    }
    
    public static String setDecimal( String sDecimal ) {
        return getDecimal( sDecimal, 0, "Y" );
    }
    
    // 바이트 크기만큼 문자열 파싱
    public static String getByteString( String str,int startPos,int byteSize ) {
        return getShortString( str, byteSize ).substring( getShortString( str, startPos ).length() );
    }
    
    /*
     * // 바이트 시작 위치 및 자릿수를 기준으로 문자열을 리턴함. public static String getByteData(String desc, int strtPos, int iLen) { String sOutput = ""; int descLen = getByteLen(desc); int iSeq = 0; for (int i = 0; i < descLen; i++) { char cOut = desc.charAt(iSeq); if (cOut > 0X007F) { i = i + 1; } iSeq++; if (i >=
     * strtPos && i < strtPos + iLen) { sOutput = sOutput + cOut; } else if (i == strtPos + iLen && cOut > 0X007f) { sOutput = sOutput + " "; } } return getString(sOutput); }
     */
    // 바이트 시작 위치 및 자릿수를 기준으로 문자열을 리턴함.
    public static String[] getByteDataWithArray( String desc,int strtPos,int iLen,int iMax ) {
        String[] result = new String[iMax];
        for ( int i = 0; i < iMax; i++ ) {
            int start = strtPos + (i * iLen);
            int size = iLen;
            result[i] = getByteData( desc, start, size );
        }
        
        lastPos = strtPos + (iLen * iMax);
        return result;
    }
    
    // getByteDataWithArray의 수행에 의한 전문의 마지막 위치 추출
    public static int getStartPosWithLastArray() {
        
        return lastPos;
        
    }
    
    // 바이트 처리 및 Alignment
    public static String setByteDataWithArray( String[] desc,int bytes,String align,String blank,int iMax ) {
        String result = "";
        for ( int i = 0; i < iMax; i++ ) {
            setByteData( desc[i], bytes, align, blank );
            result += desc[i];
        }
        
        return result;
    }
    
    /**
     * null => ""
     * 
     */
    public static String nvl( String stValue ) {
        String stNew = nvl( stValue, "" );
        return stNew;
    }
    
    /**
     * null => stNew
     * 
     */
    public static String nvl( String stValue,String stNew ) {
        
        String stResult = null;
        
        if ( isEmpty( stValue ) == true ) {
            stResult = stNew;
        } else {
            stResult = stValue.trim();
        }
        
        return stResult;
    }
    
    /**
     * 메소드명 : outPath
     * 설명 : 클래스 및 메소드 구동경로 출력
     * 메소드리턴값 :
     */
    public static void outPath( Throwable t ) {
        String out = "";
        if ( t == null ) {
            out = "N/A";
        }
        
        String s;
        synchronized (sw) {
            t.printStackTrace( pw );
            s = sw.toString();
            sw.getBuffer().setLength( 0 );
        }
        
        // substring 할 시작과 끝 Index
        int ibegin, iend;
        
        // 시작Index 찾기. 처음나오는 "at "
        ibegin = s.indexOf( "at " );
        if ( ibegin == -1 ) {
            out = "N/A";
        }
        
        // "at " 만큼 뒤로 시작Index 조정
        ibegin += 3;
        
        // 끝Index 찾기. 시작Index 이후에 처음 나오는 "("
        iend = s.indexOf( "(", ibegin );
        if ( iend == -1 ) {
            out = "N/A";
        }
        
        // "at " 과 "(" 사이의 문자열을 반환한다.
        if ( !out.equals( "N/A" ) ) {
            out = s.substring( ibegin, iend );
        }
        System.out.println("클래스 인스턴스 " + out + " (이)가 호출됨.\r\n" );
    }
    
    // Map Value를 String으로 일괄변환 처리
    public static Map stickToString( Map map ) {
        Map rsltMap = new HashMap();
        
        if ( map == null ) return rsltMap;
        
        Iterator iterator = map.entrySet().iterator();
        int iCnt = 0;
        
        while ( iterator.hasNext() ) {
            Map.Entry e = (Map.Entry) iterator.next();
            String key = (String) e.getKey();
            Object obj = (Object) e.getValue();
            
            String value = null;
            if ( obj instanceof String ) {
                value = (String) obj;
            } else if ( obj instanceof BigDecimal ) {
                value = ((BigDecimal) obj).toString();
            } else if ( obj instanceof Double ) {
                value = ((Double) obj).toString();
            } else if ( obj instanceof Integer ) {
                value = ((Integer) obj).toString();
            }
            rsltMap.put( key, value );
        }
        
        return rsltMap;
    }
    
    /**
     * 메소드명 : lastDayOfMonth
     * 설명 : 주어진 날짜 달의 마지막 일자 구하기
     * 메소드인수1 : String dateString - 일자
     * 메소드리턴값: String - 월말일
     */
    public static String lastDayOfMonth( String dateString ) {
        return lastDayOfMonth( dateString, "yyyyMMdd" );
    }
    
    /**
     * 메소드명 : lastDayOfMonth
     * 설명 : 주어진 날짜 달의 마지막 일자 구하기
     * 메소드인수1 : String dateString - 일자
     * 메소드인수2 : String format - 날짜포멧
     * 메소드리턴값: String - 월말일
     */
    public static String lastDayOfMonth( String orgDate,String format ) {
        try {
            if ( orgDate == null || (orgDate.length() != 8 && orgDate.length() != 10) ) {
                return null;
            }
            String dateString = orgDate;
            if ( orgDate.length() == 10 ) {
                dateString = changeDateFormat( dateString );
            }
            
            SimpleDateFormat formatter = new SimpleDateFormat( format, Locale.KOREA );
            
            Date date = null;
            try {
                date = formatter.parse( dateString );
                
                SimpleDateFormat yearFormat = new SimpleDateFormat( "yyyy", Locale.KOREA );
                SimpleDateFormat monthFormat = new SimpleDateFormat( "MM", Locale.KOREA );
                
                int year = Integer.parseInt( yearFormat.format( date ) );
                int month = Integer.parseInt( monthFormat.format( date ) );
                int day = lastDay( year, month );
                
                DecimalFormat fourDf = new DecimalFormat( "0000" );
                DecimalFormat twoDf = new DecimalFormat( "00" );
                String tempDate = String.valueOf( fourDf.format( year ) ) + String.valueOf( twoDf.format( month ) ) + String.valueOf( twoDf.format( day ) );
                date = formatter.parse( tempDate );
            } catch ( ParseException e ) {
                return null;
            }
            String retDate = formatter.format( date );
            if ( retDate.length() == 8 ) {
                retDate = changeDateFormat( retDate );
            }
            return retDate;
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String lastDay( String stndDt ) throws ParseException {
        if ( stndDt == null ) return null;
        
        stndDt = stndDt.replaceAll( "-", "" );
        
        if ( stndDt == null || stndDt.length() < 8 ) return null;
        else if ( isDigit( stndDt ) == false ) return null;
        
        int year = Integer.parseInt( stndDt.substring( 0, 4 ) );
        int mnth = Integer.parseInt( stndDt.substring( 4, 6 ) );
        
        return getString( lastDay( year, mnth ) );
    }
    
    /**
     * 메소드명 : isValid
     * 설명 : 날짜체크
     * 메소드인수1 : String dateString
     * 메소드리턴값: true - 날짜 형식이 맞고, 존재하는 날짜일 때 false - 날짜 형식이 맞지 않거나, 존재하지 않는 날짜일 때
     */
    public static boolean isValid( String dateString ) {
        return isValid( dateString, "yyyyMMdd" );
    }
    
    /**
     * 메소드명 : isValid
     * 설명 : 날짜체크
     * 메소드인수1 : String dateString
     * 메소드인수2 : String format - 날짜포멧
     * 메소드리턴값: true - 날짜 형식이 맞고, 존재하는 날짜일 때 false - 날짜 형식이 맞지 않거나, 존재하지 않는 날짜일 때
     */
    public static boolean isValid( String orgDate,String format ) {
        try {
            if ( orgDate == null || (orgDate.length() != 8 && orgDate.length() != 10) ) {
                return false;
            }
            String dateString = orgDate;
            if ( orgDate.length() == 10 ) {
                dateString = changeDateFormat( dateString );
            }
            
            SimpleDateFormat formatter = new SimpleDateFormat( format, Locale.KOREA );
            Date date = null;
            try {
                date = formatter.parse( dateString );
            } catch ( ParseException e ) {
                return false;
            }
            
            if ( !formatter.format( date ).equals( dateString ) ) return false;
            
            return true;
        } catch ( Exception e ) {
            return false;
        }
    }
    
    // 한자리 문자열값이 알파벳인지 확인함
    public static boolean isAlphabet( String data ) {
        boolean isOk = false;
        
        if ( (data.compareTo( "a" ) >= 0 && data.compareTo( "z" ) <= 0) || (data.compareTo( "A" ) >= 0 && data.compareTo( "Z" ) <= 0) ) {
            isOk = true;
        }
        
        return isOk;
    }
    
    // 한자리 문자열값이 숫자형인지 확인함
    public static boolean isNumeric( String data ) {
        boolean isOk = false;
        
        if ( data.compareTo( "0" ) >= 0 && data.compareTo( "9" ) <= 0 ) {
            isOk = true;
        }
        
        return isOk;
    }
    
    // 절대값 리턴함
    public static double getAbs( double iNum ) {
        if ( iNum > 0 ) return iNum;
        else return iNum * (-1);
    }
    
    // 절대값 리턴함
    public static int getAbs( int iNum ) {
        if ( iNum > 0 ) return iNum;
        else return iNum * (-1);
    }
    
    // 절대값 리턴함
    public static long getAbs( long iNum ) {
        if ( iNum > 0 ) return iNum;
        else return iNum * (-1);
    }
    
    
    /**
     * 설명문자열이 null 일경우 지정문자열로 변환
     * 
     * @param String input - 변환할 문자열
     * @param String output - 지정 문자열 메소드리턴값String - 변환된 String
     */
    public static String nvl( Object input,String output ) {
        if ( input == null || input.toString().equals( "" ) ) {
            return output;
        } else {
//          String str = input.toString().trim();
            String str = input.toString();
            if ( "NULL".equals( str.toUpperCase() ) ) {
                return output;
            }
            return str;
        }
    }
    
    /**
     * 설명문자열이 null 일경우 Empty String("") 으로 변환
     * 
     * @param String input - 변환할 문자열
     * @param String output - 지정 문자열 메소드리턴값String - 변환된 String
     */
    public static String nvl( Object input ) {
        return nvl( input, "" );
    }
    
    
    /**
     * 메소드명 : spaceToNull
     * 설명 : 공백제거, 인수가 "" 이면 null로 리턴 null 이면 null 로 리턴
     * 메소드인수1 : String data - 공백제거 처리할 값
     * 메소드리턴값 : String result - 공백제거 처리된 값
     */
    public static String spaceToNull( String data ) {
        try {
            String result = null;
            if ( data != null ) {
                result = data.trim();
                if ( result.equals( "" ) ) {
                    result = null;
                }
            }
            return result;
        } catch ( Exception e ) {
            e.printStackTrace();
            return data;
        }
    }
    
    /**
     * 숫자문자열을 term만큼 증가시킴(예increaseString("0005", 1) --> "0006")
     * 
     * @param strNum 숫자문자열
     * @param term 증가범위
     * @return String term만큼 증가한 숫자문자열
     */
    public static String increaseString( String str_num,int term ) {
        String num;
        int set_length;
        
        int temp_num = Integer.parseInt( str_num );
        num = "" + (temp_num + term);
        set_length = str_num.length() - num.length();
        for ( int i = 0; i < set_length; i++ ) {
            num = "0" + num;
        }
        return num;
    }
    
    /**
     * 좌측에 특정 문자를 채워서 자리수를 맞춤
     * 
     * @param val 변환할 값
     * @param 채울 문자
     * @param 자리수
     * @return 자리수가 맞춰진 문자열
     */
    public static String lpad( String val,String p,int count ) {
        int len = val.length();
        if ( len < count ) {
            for ( int i = 0; i < count - len; i++ ) {
                val = p + val;
            }
        }
        return val;
    }
    
    /**
     * 숫자문자열을 term만큼 감소시킴(예decreaseString("0005", 1) --> "0004")
     * 
     * @param strNum 숫자문자열
     * @param term 감소범위
     * @return String term만큼 감소한 숫자문자열
     */
    public static String decreaseString( String str_num,int term ) {
        String num;
        int set_length;
        
        int temp_num = Integer.parseInt( str_num );
        if ( (temp_num - term) < 0 ) {
            num = "";
            for ( int i = 0; i < str_num.length(); i++ ) {
                num += "0";
            }
        } else {
            num = "" + (temp_num - term);
            set_length = str_num.length() - num.length();
            for ( int i = 0; i < set_length; i++ ) {
                num = "0" + num;
            }
        }
        return num;
    }
    
    /**
     * 주어진 문자열을 CamelCase 로 변환
     * 
     * @param String strInput 문자열
     * @return CamelCase 로 변환된 문자열
     */
    public static String convertCamelCase( Object msg ) {
        return convertCamelCase( (String) msg );
    }
    
    public static String convertCamelCase( String strInput ) {
        StringTokenizer st = new StringTokenizer( strInput, "_" );
        String strOutput = "";
        String token;
        int cnt = 0;
        
        while ( st.hasMoreElements() ) {
            token = st.nextToken().toLowerCase();
            if ( cnt == 0 ) {
                strOutput += token;
            } else {
                strOutput += token.substring( 0, 1 ).toUpperCase() + token.substring( 1 );
            }
            cnt++;
        }
        
        return strOutput;
    }
    
    /**
     * 주어진 문자열을 PastcalCase 로 변환
     * 
     * @param String strInput 문자열
     * @return PastcalCase 로 변환된 문자열
     */
    public static String convertPastcalCase( String strInput ) {
        StringTokenizer st = new StringTokenizer( strInput, "_" );
        String strOutput = "";
        String token;
        
        while ( st.hasMoreElements() ) {
            token = st.nextToken().toLowerCase();
            strOutput += token.substring( 0, 1 ).toUpperCase() + token.substring( 1 );
        }
        
        return strOutput;
    }
    
    /**
     * String => 숫자형을 기준 자릿수 이하에서 올림후 문자값으로 리턴
     * 
     * @paramdouble dDecimal - 입력받은 숫자
     * @paramint iDgtCnt - 값이 널인 경우 "0"으로 리턴할지 여부
     * @return String - 절사된 문자값
     */
    public static String ceil( double dDecimal,int iDgtCnt ) {
        String out = "0";
        
        if ( iDgtCnt >= 0 ) {
            out = calcDecimal( dDecimal, iDgtCnt, BigDecimal.ROUND_CEILING );
        } else {
            double mt = Math.pow( 10, Math.abs( iDgtCnt ) );
            dDecimal = Math.ceil( dDecimal / mt );
            dDecimal = dDecimal * mt;
            out = getDecimal( dDecimal );
        }
        
        return out;
    }
    
    /**
     * String => String, 숫자형 문자값을 기준 자릿수 이하에서 올림후 문자값으로 리턴
     * 
     * @paramString sDecimal - 입력받은 숫자형 문자열
     * @paramint iDgtCnt - 값이 널인 경우 "0"으로 리턴할지 여부
     * @return String - 절사된 문자값
     */
    public static String ceil( String sDecimal,int iDgtCnt ) {
        if ( sDecimal == null || sDecimal == "" ) sDecimal = "0";
        double tmp = Double.parseDouble( sDecimal );
        return ceil( tmp, iDgtCnt );
    }
    
    /**
     * String => 숫자형을 기준 자릿수 이하에서 올림, 반올림, 버림후 문자값으로 리턴
     * 
     * @paramdouble dDecimal - 입력받은 숫자
     * @paramint iDgtCnt - 올림, 반올림, 버림 기준 자릿수
     * @paramint format - 올림, 반올림, 버림 여부
     * @paramString isNullToZero - 값이 널인 경우 "0"으로 리턴할지 여부
     * @return String sRslt - 올림, 반올림, 버림된 결과값
     */
    public static String calcDecimal( double dDecimal,int iDgtCnt,int format ) {
        BigDecimal bd = new BigDecimal( dDecimal );
        bd = bd.setScale( iDgtCnt, format );
        return bd.toString();
    }
    
    /**
     * String => 숫자형을 기준 자릿수 이하에서 올림, 반올림, 버림후 문자값으로 리턴
     * 
     * @param String sDecimal - 입력받은 숫자형 문자열
     * @param int iDgtCnt - 올림, 반올림, 버림 기준 자릿수
     * @param int format - 올림, 반올림, 버림 여부
     * @param String isNullToZero - 값이 널인 경우 "0"으로 리턴할지 여부
     * @return String sRslt - 올림, 반올림, 버림된 결과값
     */
    public static String calcDecimal( String sDecimal,int iDgtCnt,int format ) {
        double dDecimal = 0;
        sDecimal = nvl( sDecimal );
        if ( !"".equals( sDecimal ) ) {
            dDecimal = Double.parseDouble( sDecimal );
        }
        
        return calcDecimal( dDecimal, iDgtCnt, format );
    }
    
    /**
     * 문장중 시작 패턴에대해 분할하여 목록으로 리턴함.
     * 
     * @param String stmt대상문자열
     * @param String startSep시작 식별자
     * @return List
     */
    public static List parse( String stmt,String patternStr ) {
        List itemList = new ArrayList();
        Pattern pattern = Pattern.compile( patternStr ); // 검색할 문자열
        Matcher matcher = pattern.matcher( stmt ); // 대상문자열
        
        while ( matcher.find() ) {
            itemList.add( matcher.group() );
        }
        
        return itemList;
    }
    
    /**
     * 문장중 시작/종료 패턴 내의 문자를 분할하여 목록으로 리턴함.
     * 
     * @param String stmt대상문자열
     * @param String startSep시작 식별자
     * @return List
     */
    public static List parseItem( String stmt,String startSep,String endSep ) {
        List itemList = new ArrayList();
        Pattern pattern = Pattern.compile( startSep + "(((?!" + endSep + ").)*)" + endSep ); // 검색할 문자열
        Matcher matcher = pattern.matcher( stmt ); // 대상문자열
        String matchStr = null;
        
        while ( matcher.find() ) {
            matchStr = matcher.group();
            // 시작/종료 식별자를 제외한항목만 목록에 추가
            matchStr = matchStr.replaceAll( startSep, "" );
            matchStr = matchStr.replaceAll( endSep, "" );
            itemList.add( matchStr );
        }
        
        return itemList;
    }
    
    /**
     * 메소드명 : getComma
     * 설명 : 3자리마다 콤마를 찍음
     * 메소드인수1 : int item - 콤마를 찍을 대상
     * 메소드리턴값 : String - format에 맞춰 변경된 문자열
     */
    public static String getComma( int item ) {
        return getComma( item + "" );
    }
    
    /**
     * 메소드명 : getComma
     * 설명 : 주어진 String의 금액단위 콤마
     * 메소드인수1 : String str - format에 맞출 문자열
     * 메소드리턴값 : String - format에 맞춰 변경된 문자열
     */
    public static String getComma( String str ) {
        String sj = str;
        
        try {
            if ( str == null ) return null;
            if ( str.trim().equals( "" ) ) return "";
            if ( str.indexOf( "," ) != -1 ) return str;
            if ( sj.startsWith( "0" ) ) {
                for ( int i = 0; i < str.length(); i++ ) {
                    sj = str.substring( i );
                    if ( !sj.startsWith( "0" ) ) break;
                }
            }
            if ( str.equals( "0" ) ) return "0";
            else {
                double nu = Double.parseDouble( sj );
                NumberFormat nf = NumberFormat.getInstance();
                String no = nf.format( nu );
                return no;
            }
        } catch ( Exception e ) {
            e.printStackTrace();
            return str;
        }
    }
    
    /**
     * 메소드명 : fomat
     * 설명 : 주어진 format의 길이에 맞춰 특정문자를 문자열의 왼쪽에 attach
     * 메소드인수1 : String str - format에 맞출 문자열
     * 메소드인수2 : int length - format의 길이
     * 메소드인수3 : char ch - 자릿수에 맞춰서 채울 문자
     * 메소드리턴값 : String - format에 맞춰 변경된 문자열
     */
    public static String format( String str,int length,char ch ) {
        StringBuffer buff = new StringBuffer( str );
        try {
            if ( str == null ) {
                str = "";
            }
            
            if ( str.length() > length ) { // 처리하려는 문자열이 기준길이보다 길 경우
                //
            }
            
            for ( int i = buff.length(); i < length; i++ ) {
                buff.insert( 0, ch );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
            return str;
        }
        
        return buff.toString();
    }
    
    /**
     * 메소드명 : fomat
     * 설명 : 주어진 format의 길이에 맞춰 특정문자를 숫자의 왼쪽에 attach
     * 메소드인수1 : int num - format에 맞출 숫자
     * 메소드인수2 : int length - format의 길이
     * 메소드인수3 : char ch - 자릿수에 맞춰서 채울 문자
     * 메소드리턴값 : String - format에 맞춰 변경된 문자열
     */
    public static String format( int num,int length,char ch ) {
        return format( String.valueOf( num ), length, ch );
    }
    
    /**
     * 메소드명 : fomat
     * 설명 : 주어진 format의 길이에 맞춰 특정문자를 문자열의 지정한 쪽에 attach
     * 메소드인수1 : String str - format에 맞출 문자열
     * 메소드인수2 : int length - format의 길이
     * 메소드인수3 : char ch - 자릿수에 맞춰서 채울 문자
     * 메소드인수4 : boolean fillRightYn - 오른쪽에 채울것인지의 여부
     * 메소드리턴값 : String - format에 맞춰 변경된 문자열
     */
    public static String format( String str,int length,char ch,boolean fillRightYn ) {
        String rsltStr = null;
        
        StringBuffer buff = new StringBuffer( str );
        
        if ( str == null ) {
            str = "";
        }
        
        try {
            if ( XUtil.getByteLen(str) > length ) { // 처리하려는 문자열이 기준길이보다 길 경우
                // 문자열이 기준보다 길면 그대로 리턴
            }
            
            if ( !fillRightYn ) {
                rsltStr = format( str, length, ch );
            } else {
                for ( int i = buff.length(); i < length; i++ ) {
                    buff.append( ch );
                }
                rsltStr = buff.toString();
            }
        } catch ( Exception e ) {
            e.printStackTrace();
            rsltStr = str;
        }
        
        return rsltStr;
    }
    
    /**
     * 메소드명 : fomat
     * 설명 : 주어진 format의 길이에 맞춰 특정문자를 숫자의 왼쪽에 attach
     * 메소드인수1 : int num - format에 맞출 숫자
     * 메소드인수2 : int length - format의 길이
     * 메소드인수3 : char ch - 자릿수에 맞춰서 채울 문자
     * 메소드인수4 : boolean fillRightYn - 오른쪽에 채울것인지의 여부
     * 메소드리턴값 : String - format에 맞춰 변경된 문자열
     */
    public static String format( int num,int length,char ch,boolean fillRightYn ) {
        return format( String.valueOf( num ), length, ch, fillRightYn );
    }
    
    public static String getString( long num ) {
        return getString( Long.toString( num ) );
    }
    
    public static String getString( double num ) {
        return getString( Double.toString( num ) );
    }
    
    public static String getString( int num ) {
        return getString( Long.toString( num ) );
    }
    
    public static String getString( BigDecimal bDecimal ) {
        return getString( bDecimal.toString() );
    }
    
    public static String getString( Object obj ) {
        String out = "";
        if ( obj != null ) {
            out = obj.toString();
        }
        
        return getString( out );
    }
    
    /**
     * 메소드명 : getPostNoFormat
     * 설명 : 우편번호 ###-### 형식으로 변환
     * 메소드인수1 : String strVal - 입력받은 숫자형 문자값
     * 메소드리턴값 : String - 우편번호 ###-### 형식 문자값
     */
    public static String getPostNoFormat( String strVal ) {
        String trimStr = strVal.replaceAll( " ", "" ).replaceAll( "-", "" );
        String rtnStr = strVal;
        if ( "".equals( nvl( trimStr ) ) ) {
            return rtnStr;
        }
        
        if ( trimStr.length() == 6 ) {
            rtnStr = trimStr.substring( 0, 3 ) + "-" + trimStr.substring( 3 );
        }
        
        return rtnStr;
    }
    
    /**
     * 메소드명 : getTelNoFormat
     * 설명 : 전화번호 ##(#)(#)-###(#)-#### 형식으로 변환
     * 메소드인수1 : String strVal - 입력받은 숫자형 문자값
     * 메소드리턴값 : String - 전화번호 ##(#)(#)-###(#)-#### 문자값
     */
    public static String getTelNoFormat( String strVal ) {
        String trimStr = strVal.replaceAll( " ", "" ).replaceAll( "-", "" );
        String rtnStr = strVal;
        if ( "".equals( nvl( trimStr ) ) ) {
            return rtnStr;
        }
        
        if ( trimStr.length() == 7 ) {
            //###-####
            rtnStr = (trimStr.substring( 0, 3 ) + "-" + trimStr.substring( 3 ));
        } else if ( trimStr.length() == 8 ) {
            //####-####
            rtnStr = (trimStr.substring( 0, 4 ) + "-" + trimStr.substring( 4 ));
        } else if ( trimStr.length() == 9 ) {
            //02-XXX-XXXX
            rtnStr = (trimStr.substring( 0, 2 ) + "-" + trimStr.substring( 2, 4 ) + "-" + trimStr.substring( 4 ));
        } else if ( trimStr.length() == 10 ) {
            if ( "02".equals( trimStr.substring( 0, 2 ) ) ) {
                //02-XXXX-XXXX
                rtnStr = (trimStr.substring( 0, 2 ) + "-" + trimStr.substring( 2, 6 ) + "-" + trimStr.substring( 6 ));
            } else {
                //XXX-XXX-XXXX
                rtnStr = (trimStr.substring( 0, 3 ) + "-" + trimStr.substring( 3, 6 ) + "-" + trimStr.substring( 6 ));
            }
        } else if ( trimStr.length() == 11 ) {
            //XXX-XXXX-XXXX
            rtnStr = (trimStr.substring( 0, 3 ) + "-" + trimStr.substring( 3, 7 ) + "-" + trimStr.substring( 7 ));
        } else if ( trimStr.length() == 12 ) {
            //0505-XXXX-XXXX
            rtnStr = (trimStr.substring( 0, 4 ) + "-" + trimStr.substring( 4, 8 ) + "-" + trimStr.substring( 8 ));
        } else {
            rtnStr = strVal;
        }
        
        return rtnStr;
    }
    
    /**
     * 메소드명 : getString
     * 설명 : nul 일경우 ""으로 치환하여 반환
     * 메소드인수1 : String sString - 입력받은 숫자형 문자값
     * 메소드리턴값 : String - 절사된 문자값
     */
    public static String getString( String sString ) {
        return getString( sString, "Y" );
    }
    
    /**
     * 메소드명 : getString
     * 설명 : nul 일경우 조건에 따라 ""으로 치환하여 반환하거나 null을 반환
     * 메소드인수1 : String sString - 입력받은 숫자형 문자값
     * 메소드인수2 : String isNullToSpace - 값이 널인 경우 ""으로 리턴할지 여부
     * 메소드리턴값 : String - 절사된 문자값
     */
    public static String getString( String sString,String isNullToSpace ) {
        try {
            String sRslt = null;
            
            if ( ( sString == null || "null".equals(sString) ) && isNullToSpace.equals( "Y" ) ) {
                sRslt = "";
            }
            
            else if ( sString != null ) {
                
                sRslt = sString.trim();
                if ( sRslt.length() == 0 ) {
                    sRslt = "";
                }
            }
            
            return sRslt;
        } catch ( Exception e ) {
            e.printStackTrace();
            return sString;
        }
    }
    
    /**
     * 메소드명 : getRunPath
     * 설명 :현재 실행되고 있는 클래스.메소드를 문자열로 반환함(기록용)
     * 메소드인수1 : Throwable t - Throwable 객체
     * 메소드리턴값 : String - 클래스실행경로 메시지
     */
    public static String getRunPath( Throwable t ) {
        String out = "";
        if ( t == null ) {
            out = "N/A";
        }
        
        String s;
        synchronized (sw) {
            t.printStackTrace( pw );
            s = sw.toString();
            sw.getBuffer().setLength( 0 );
        }
        
        // substring 할 시작과 끝 Index
        int ibegin, iend;
        
        // 시작Index 찾기. 처음나오는 "at "
        ibegin = s.indexOf( "at " );
        if ( ibegin == -1 ) {
            out = "N/A";
        }
        
        // "at " 만큼 뒤로 시작Index 조정
        ibegin += 3;
        
        // 끝Index 찾기. 시작Index 이후에 처음 나오는 "("
        iend = s.indexOf( "(", ibegin );
        if ( iend == -1 ) {
            out = "N/A";
        }
        
        // "at " 과 "(" 사이의 문자열을 반환한다.
        if ( !out.equals( "N/A" ) ) {
            out = s.substring( ibegin, iend );
        }
        return "\r\n[STEP] " + out + " START.\r\n------------------------------------------------------------------------------------------\r\n";
    }
    
    // BIG DECIMAL로 리턴함
    public static BigDecimal getBigDecimal( double iNum ) {
        BigDecimal bd = new BigDecimal( iNum );
        
        return bd;
    }
    
    public static BigDecimal getBigDecimal( int iNum ) {
        BigDecimal bd = new BigDecimal( iNum );
        
        return bd;
    }
    
    public static BigDecimal getBigDecimal( long iNum ) {
        BigDecimal bd = new BigDecimal( iNum );
        
        return bd;
    }
    
    public static BigDecimal getBigDecimal( String sNum ) {
        double iNum = Double.parseDouble( getDecimal( sNum ) );
        return getBigDecimal( iNum );
    }
    
    // 바이트 시작 위치 및 자릿수를 기준으로 문자열을 리턴함.
    public static String getByteData( String desc,int strtPos,int iLen ) {
        String sOutput = "";
        
        int descLen = getByteLen( desc );
        int iSeq = 0;
        
        for ( int i = 0; i < descLen; i++ ) {
            char cOut = desc.charAt( iSeq );
            if ( cOut > 0X007F ) {
                i = i + 1;
            }
            iSeq++;
            if ( i >= strtPos && i < strtPos + iLen ) {
                sOutput = sOutput + cOut;
            } else if ( i == strtPos + iLen && cOut > 0X007f ) {
                sOutput = sOutput + " ";
            }
        }
        return getString( sOutput );
    }
    
    // 바이트 자릿수만큼 왼쪽에서부터 잘라서 리턴함.
    public static String getByteData( String desc,int bytes ) {
        String ret = "";
        
        if ( desc == null ) {
            desc = "";
        } else {
            desc = desc.trim();
        }
        
        if ( bytes > getByteLen( desc ) ) return desc;
        
        int iMaxLen = bytes;
        int iSize = 0;
        
        for ( int i = 0; i < getByteLen( desc ); i++ ) {
            String tmp = desc.substring( i, i + 1 );
            char cOut = tmp.charAt( 0 );
            if ( cOut > 0X007F ) {
                iSize = iSize + 2;
            } else {
                iSize = iSize + 1;
            }
            
            if ( iSize > bytes ) {
                i = getByteLen( desc );
            } else {
                ret = ret + tmp;
            }
        }
        
        return getString( ret );
    }
    
    // 바이트 처리 및 Alignment
    public static String setByteData( String desc,int bytes,String align,String blank ) {
        if ( desc == null ) {
            desc = "";
        } else {
            desc = desc.trim();
        }
        int cnt = bytes - getByteLen( desc );
        String temp = "";
        
        if ( align.equals( "R" ) ) {
            for ( int i = 0; i < cnt; i++ ) {
                if ( blank.equals( "0" ) ) {
                    temp += "0";
                } else {
                    temp += " ";
                }
            }
            desc = temp + desc;
        } else {
            for ( int i = 0; i < cnt; i++ ) {
                if ( blank.equals( "0" ) ) {
                    temp += "0";
                } else {
                    temp += " ";
                }
            }
            desc = desc + temp;
        }
        return desc;
    }
    
    // 바이트 길이를 리턴함.
    public static int getByteLen( String str ) {
        int iOrgLen = str.length();
        int iLen = 0;
        
        for ( int i = 0; i < iOrgLen; i++ ) {
            if ( str.charAt( i ) > 0X007F ) {
                iLen = iLen + 2;
            } else {
                iLen = iLen + 1;
            }
        }
        
        return iLen;
    }
    
    // 한글자릿수 계산을 위한 처리
    public static String getShortString( String str,int byteSize ) {
        int rSize = 0;
        int len = 0;
        
        if ( str.getBytes().length > byteSize ) {
            for ( ; rSize < str.length(); rSize++ ) {
                if ( str.charAt( rSize ) > 0x007F ) len += 2;
                else len++;
                
                if ( len > byteSize ) break;
            }
            
            str = str.substring( 0, rSize );
        }
        
        return str;
    }
    
    /**
     * 빈값(Null/공백문자열)인지를 체크함
     * 
     */
    public static boolean isEmpty( String stValue ) {
        boolean bResult = false;
        
        if ( (stValue == null) || ("".equals( stValue.trim() ) == true) || ("null".equals( stValue.trim().toLowerCase() ) == true) ) {
            bResult = true;
        }
        
        return bResult;
    }
    
    public static boolean isEmpty( char cValue ) {
        return isEmpty( cValue + "" );
    }
    
    /**
     * 메소드명 : getDecimal
     * 설명 : double => String, 숫자형 문자값을 소숫점2자리에서 절사후 문자값으로 리턴
     * 메소드인수1 : String sDecimal - 입력받은 숫자형 문자값
     * 메소드인수2 : int iDgtCnt - 절사 기준 자릿수
     * 메소드인수3 : String isNullToZero - 값이 널인 경우 "0"으로 리턴할지 여부
     * 메소드리턴값 : String sRslt - 절사된 문자값
     */
    public static String getDecimal( double iDecimal,int iDgtCnt,String isNullToZero,int roundType ) {
        try {
            String sRslt = null;
            String sDecimal = Double.toString( iDecimal );
            if ( sDecimal.length() > 0 ) {
                BigDecimal bd = new BigDecimal( sDecimal );
                bd = bd.setScale( iDgtCnt, roundType );
                sRslt = bd.toString();
            }
            
            if ( isNullToZero.equals( "Y" ) && sRslt == null ) // 널이면 0으로 바꾸어야 할 경우
            {
                sRslt = "0";
            }
            return sRslt;
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 메소드명 : getDecimal
     * 설명 : double => String, 숫자형 문자값을 절사후 문자값으로 리턴
     * 메소드인수1 : String sDecimal - 입력받은 숫자형 문자값
     * 메소드인수2 : String isNullToZero - 값이 널인 경우 "0"으로 리턴할지 여부
     * 메소드리턴값 : String - 절사된 문자값
     */
    public static String getDecimal( double iDecimal,String isNullToZero ) {
        String sDecimal = getDecimal( iDecimal, 0, "Y" );
        return getDecimal( sDecimal, 0, isNullToZero );
    }
    
    public static String getDecimal( String sDecimal ) {
        return getDecimal( sDecimal, 0, "Y" );
    }
    
    public static String getDecimal( Object obj ) {
        if ( obj == null ) return "0";
        
        String tmp = obj.toString();
        return getDecimal( tmp );
    }
    
    /**
     * 메소드명 : getDecimal
     * 설명 : double => String, 숫자형 문자값을 절사후 문자값으로 리턴
     * 메소드인수1 : String sDecimal - 입력받은 숫자형 문자값
     * 메소드인수2 : String isNullToZero - 값이 널인 경우 "0"으로 리턴할지 여부
     * 메소드리턴값 : String - 절사된 문자값
     */
    public static String getDecimal( double iDecimal ) {
        String sDecimal = getDecimal( iDecimal, 0, "Y" );
        return getDecimal( sDecimal, 0, "Y" );
    }
    
    public static String getDecimal( BigDecimal bDecimal ) {
        String sDecimal = "0";
        if ( bDecimal == null ) {
            sDecimal = "0";
        } else {
            sDecimal = bDecimal.toString();
        }
        return sDecimal;
    }
    
    public static String getDecimal( double iDecimal,int iDgtCnt,String isNullToZero ) {
        return getDecimal( iDecimal, iDgtCnt, isNullToZero, BigDecimal.ROUND_FLOOR );
    }
    
    /**
     * 메소드명 : setDecimal
     * 설명 : String => String, 숫자형 문자값을 소숫점2자리에서 절사후 문자값으로 리턴
     * 메소드인수1 : String sDecimal - 입력받은 숫자형 문자값
     * 메소드인수2 : int iDgtCnt - 절사 기준 자릿수
     * 메소드인수3 : String isNullToZero - 값이 널인 경우 "0"으로 리턴할지 여부
     * 메소드리턴값 : String sRslt - 절사된 문자값
     */
    public static String getDecimal( String sDecimal,int iDgtCnt,String isNullToZero ) {
        try {
            String sRslt = null;
            if ( sDecimal != null ) {
                if ( sDecimal != null ) sDecimal.trim();
                
                if ( sDecimal.length() > 0 ) {
                    BigDecimal bd = new BigDecimal( sDecimal );
                    bd = bd.setScale( iDgtCnt, BigDecimal.ROUND_FLOOR );
                    sRslt = bd.toString();
                }
            }
            
            if ( isNullToZero.equals( "Y" ) && sRslt == null ) // 널이면 0으로 바꾸어야 할 경우
            {
                sRslt = "0";
            }
            return sRslt;
        } catch ( Exception e ) {
            e.printStackTrace();
            return sDecimal;
        }
    }
    
    /**
     * 메소드명 : getDecimal
     * 설명 : String => String, 숫자형 문자값을 소숫점2자리에서 절사후 문자값으로 리턴
     * 메소드인수1 : String sDecimal - 입력받은 숫자형 문자값
     * 메소드인수2 : String isNullToZero - 값이 널인 경우 "0"으로 리턴할지 여부
     * 메소드리턴값 : String - 절사된 문자값
     */
    public static String getDecimal( String sDecimal,String isNullToZero ) {
        return getDecimal( sDecimal, 0, isNullToZero );
    }
    
    // double로 변환
    public static double getDouble( String sItem,int iDgtCnt ) {
        if ( sItem == null ) return 0;
        sItem = sItem.toString();
        String sDecimal = getDecimal( sItem, 4, "Y" );
        return Double.parseDouble( sDecimal );
    }
    
    public static double getDouble( String sItem ) {
        double dItem = 0;
        
        sItem = getDecimal( sItem );
        dItem = Double.parseDouble( sItem );
        
        return dItem;
    }
    
    public static double getDouble( long num ) {
        return getDouble( getString( num ) );
    }
    
    public static double getDouble( BigDecimal bDecimal ) {
        String sDecimal = getDecimal( bDecimal );
        double dDecimal = Double.parseDouble( sDecimal );
        return dDecimal;
    }
    
    public static double getDouble( Object obj ) {
        String tmp = "";
        if ( obj != null ) tmp = obj.toString();
        return getDouble( tmp );
    }
    
    // Long형으로 변환
    public static long getLong( String sItem,int iDgtCnt ) {
        if ( sItem == null ) return 0;
        sItem = sItem.toString();
        String sDecimal = getDecimal( sItem, 4, "Y" );
        return Long.parseLong( sDecimal );
    }
    
    public static long getLong( String sItem ) {
        long dItem = 0;
        
        sItem = getDecimal( sItem );
        dItem = Long.parseLong( sItem );
        
        return dItem;
    }
    
    public static long getLong( double num ) {
        return getLong( getString( num ) );
    }
    
    public static long getLong( BigDecimal bDecimal ) {
        String sDecimal = getDecimal( bDecimal );
        long dDecimal = Long.parseLong( sDecimal );
        return dDecimal;
    }
    
    public static long getLong( Object obj ) {
        String tmp = "";
        if ( obj != null ) tmp = obj.toString();
        return getLong( tmp );
    }
    
    /**
     * 메소드명 : getInteger
     * 설명 : String => String, 숫자형 문자값을 소숫점2자리에서 절사후 문자값으로 리턴
     * 메소드인수1 : String sDecimal - 입력받은 숫자형 문자값
     * 메소드인수2 : int iDgtCnt - 절사 기준 자릿수
     * 메소드인수3 : String isNullToZero - 값이 널인 경우 "0"으로 리턴할지 여부
     * 메소드리턴값 : String sRslt - 절사된 문자값
     */
    public static int getInteger( String sInteger,int iDgtCnt,String isNullToZero ) {
        try {
            String sRslt = null;
            if ( sInteger != null ) {
                if ( sInteger != null ) sInteger.trim();
                if ( sInteger.length() > 0 ) {
                    BigDecimal bd = new BigDecimal( sInteger );
                    bd = bd.setScale( iDgtCnt, BigDecimal.ROUND_FLOOR );
                    sRslt = bd.toString();
                }
            }
            
            if ( isNullToZero.equals( "Y" ) && sRslt == null ) // 널이면 0으로 바꾸어야 할 경우
            {
                sRslt = "0";
            }
            return Integer.parseInt( sRslt );
        } catch ( Exception e ) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public static int getInteger( String tmp ) {
        return getInteger( tmp, 0, "Y" );
    }
    
    public static int getInteger( Object tmp ) {
        if ( tmp == null ) return 0;
        String item = tmp.toString();
        return getInteger( item, 0, "Y" );
    }
    
    /**
     * 메소드명 : getInteger
     * 설명 : double => String, 숫자형 문자값을 소숫점2자리에서 절사후 문자값으로 리턴
     * 메소드인수1 : String sDecimal - 입력받은 숫자형 문자값
     * 메소드인수2 : int iDgtCnt - 절사 기준 자릿수
     * 메소드인수3 : String isNullToZero - 값이 널인 경우 "0"으로 리턴할지 여부
     * 메소드리턴값 : String sRslt - 절사된 문자값
     */
    static int getInteger( int iInteger,int iDgtCnt,String isNullToZero ) {
        try {
            String sRslt = null;
            String sInteger = Double.toString( iInteger );
            if ( sInteger.length() > 0 ) {
                BigDecimal bd = new BigDecimal( sInteger );
                bd = bd.setScale( iDgtCnt, BigDecimal.ROUND_FLOOR );
                sRslt = bd.toString();
            }
            
            if ( isNullToZero.equals( "Y" ) && sRslt == null ) // 널이면 0으로 바꾸어야 할 경우
            {
                sRslt = "0";
            }
            return Integer.parseInt( sRslt );
        } catch ( Exception e ) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public static int getInteger( int iInteger ) {
        return getInteger( iInteger, 0, "Y" );
    }
    
    /**
     * 메소드명 : getDate
     * 설 명 : 포맷에 맞는 날짜형태로 변환함
     * 리 턴 값 : String outDt - 포맷에 맞추어 변환된 날짜문자열
     */
    public static String getDate( String date,String format ) throws Exception {
        String outDt = "";
        
        date = getString( date );
        format = getString( format );
        
        if ( date.length() == 0 ) return date;
        else if ( format.length() == 0 ) return date;
        
        format = format.toUpperCase();
        
        if ( date.length() == 6 ) {
            outDt = "20" + date;
        } else if ( date.length() == 10 ) {
            outDt = date.replaceAll( "-", "" );
        } else if ( date.length() == 8 ) {
            outDt = date;
        }
        
        if ( outDt.length() != 8 ) {
            throw new Exception( "값:" + date + "(은)는 날짜형태의 데이터가 아닙니다." );
        }
        
        if ( format.equals( "YYYY-MM-DD" ) ) {
            outDt = changeDate( outDt, 10 );
        } else if ( format.equals( "YYYY-MM" ) ) {
            outDt = changeDate( outDt, 10 ).substring( 0, 7 );
        } else if ( format.equals( "YY-MM" ) ) {
            outDt = outDt.substring( 2, 4 ) + "-" + outDt.substring( 4, 6 );
        } else if ( format.equals( "YY-MM-DD" ) ) {
            outDt = changeDate( outDt, 10 ).substring( 2 );
        } else if ( format.equals( "MM-DD" ) ) {
            outDt = changeDate( outDt, 10 ).substring( 5 );
        } else if ( format.equals( "YYYYMMDD" ) ) {
            // ItSelf
        } else if ( format.equals( "YYYYMM" ) ) {
            outDt = outDt.substring( 0, 6 );
        } else if ( format.equals( "YYMM" ) ) {
            outDt = outDt.substring( 2, 6 );
        } else if ( format.equals( "YYMMDD" ) ) {
            outDt = outDt.substring( 2 );
        } else if ( format.equals( "MMDD" ) ) {
            outDt = outDt.substring( 4 );
        } else if ( format.equals( "YYYY" ) ) {
            outDt = outDt.substring( 0, 4 );
        } else if ( format.equals( "MM" ) ) {
            outDt = outDt.substring( 4, 6 );
        } else if ( format.equals( "DD" ) ) {
            outDt = outDt.substring( 6 );
        } else {
            throw new Exception( "포맷:" + format + "(은)는 지원하지 않습니다." );
        }
        
        return outDt;
    }
    
    /**
     * 메소드명 : getSqlDate
     * 설 명 : Timestamp 형식의 오늘날짜를 리턴
     * 리 턴 값 : Timestamp
     */
    public static Timestamp getSqlDate() throws Exception {
        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp( today.getTime() );
    }
    
    /**
     * 메소드명 : getCurrDate
     * 설명 : 처리작업일자(현재일자) 구하기
     * 메소드리턴값 : String sCurrDate - 리턴할 현재날짜 문자열
     */
    public static String getCurrDate() {
        return getCurrDate( false );
    }
    
    public static String getCurrDate( boolean bDash ) {
        try {
            Calendar calendar = Calendar.getInstance();
            String sCurrDate = "";
            
            String sYear = Integer.toString( calendar.get( Calendar.YEAR ) );
            String sMnth = Integer.toString( calendar.get( Calendar.MONTH ) + 1 );
            String sDays = Integer.toString( calendar.get( Calendar.DATE ) );
            
            if ( sMnth.length() == 1 ) {
                sMnth = "0" + sMnth;
            }
            
            if ( sDays.length() == 1 ) {
                sDays = "0" + sDays;
            }
            
            if ( bDash == true ) {
                sCurrDate = sYear + "-" + sMnth + "-" + sDays;
            } else {
                sCurrDate = sYear + sMnth + sDays;
            }
            return sCurrDate;
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
    }
    
    // 현재날짜를 포맷에 맞추어 리턴함 (YYYYMMDD, YYYYMM, DD, YYYY-MM-DD,...)
    public static String getCurrDate( String format ) throws Exception {
        String currDt = getCurrDate();
        
        currDt = getDate( currDt, format );
        return currDt;
    }
    
    // 시간문자열을 포맷에 맞추어 리턴함
    public static String getTime( String stndTm,String format ) {
        String outTm = "";
        
        stndTm = getString( stndTm );
        format = getString( format );
        
        if ( stndTm.length() == 0 ) return stndTm;
        else if ( format.length() == 0 ) return stndTm;
        
        format = format.toUpperCase();
        
        if ( stndTm.length() == 4 ) {
            stndTm = stndTm + "00";
        } else if ( stndTm.length() == 8 ) {
            stndTm = stndTm.replaceAll( ":", "" );
        }
        
        String hour = stndTm.substring( 0, 2 );
        String min = stndTm.substring( 2, 4 );
        String sec = stndTm.substring( 4 );
        
        if ( format.equals( "HH:MM:SS" ) ) {
            outTm = hour + ":" + min + ":" + sec;
        } else if ( format.equals( "HH:MM" ) ) {
            outTm = hour + ":" + min;
        } else if ( format.equals( "MM:SS" ) ) {
            outTm = min + ":" + sec;
        } else if ( format.equals( "HH" ) ) {
            outTm = hour;
        } else if ( format.equals( "MM" ) ) {
            outTm = min;
        } else if ( format.equals( "SS" ) ) {
            outTm = sec;
        } else if ( format.equals( "HHMMSS" ) ) {
            outTm = hour + min + sec;
        } else if ( format.equals( "HHMM" ) ) {
            outTm = hour + min;
        } else if ( format.equals( "MMSS" ) ) {
            outTm = min + sec;
        }
        
        return outTm;
    }
    
    /**
     * 메소드명 : getCurrTime
     * 설명 : 처리작업시각(현재시간) 구하기
     * 메소드리턴값 : String sCurrTime - 리턴할 현재시각 문자열
     */
    public static String getCurrTime() {
        return getCurrTime( false );
    }
    
    public static String getCurrTime( boolean bColon ) {
        try {
            String sCurrTime = "";
            Calendar cal = Calendar.getInstance();
            
            if ( bColon == true ) {
                SimpleDateFormat sdf = new SimpleDateFormat( "HH:mm:ss" );
                sCurrTime = sdf.format( cal.getTime() );
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat( "HHmmss" );
                sCurrTime = sdf.format( cal.getTime() );
            }
            
            return sCurrTime;
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
    }
    
    // 포맷에 맞는 현재 시각을 리턴함 (HH:MM:SS, MM:SS, HHMMSS,...)
    public static String getCurrTime( String format ) {
        String outTm = getCurrTime();
        
        return getTime( outTm, format );
    }
    
    // 현재일시정보를 리턴함
    public static String getCurrDttm() {
        return getCurrDttm( false );
    }
    
    public static String getCurrDttm( boolean bSep ) {
        if ( bSep ) {
            return getCurrDate( bSep ) + " " + getCurrTime( bSep );
        } else {
            return getCurrDate( bSep ) + getCurrTime( bSep );
        }
    }
    
    /**
     * 메소드명 : getLastDate
     * 설명 : 기준일자 또는 기준년월에 대한 해당 월의 마지막 일자(yyyymmdd)를 리턴함
     * 메소드리턴값 : String 월의 마지막일(yyyymmdd)을 리턴함
     */
    public static String getLastDate( String stndDt ) throws Exception {
        return getLastDate( stndDt, DEFAULT_DATE_FORMAT );
    }
    
    public static String getLastDate( String stndDt,String format ) throws Exception {
        String lastDate = "";
        String year = "";
        String month = "";
        
        stndDt = getDate( stndDt, "YYYYMMDD" );
        
        try {
            year = stndDt.substring( 0, 4 );
            month = stndDt.substring( 4, 6 );
            
            int iDay = lastDay( Integer.parseInt( year ), Integer.parseInt( month ) );
            lastDate = year + month + Integer.toString( iDay );
            lastDate = getDate( lastDate, format );
        } catch ( Exception e ) {
            lastDate = "";
        }
        
        return lastDate;
    }
    
    // 지정한 월의 첫번째 날짜를 원하는 포맷으로 리턴함
    public static String getFirstDate( String stndDt,String format ) throws Exception {
        stndDt = getDate( stndDt, "YYYYMMDD" );
        if ( stndDt.length() != 8 ) return stndDt;
        stndDt = stndDt.substring( 0, 6 ) + "01";
        return getDate( stndDt, format );
    }
    
    public static String getFirstDate( String stndDt ) throws Exception {
        return getFirstDate( stndDt, DEFAULT_DATE_FORMAT );
    }
    
    /**
     * 메소드명 : getLastDay
     * 설명 : 년월(yyyyyymm)을 입력받아 해당 월의 마지막 날짜(dd)를 리턴함
     * 메소드리턴값 : String 월의 마지막일(dd)을 리턴함
     */
    public static String getLastDay( String stndDt ) {
        String lastDay = "";
        String year = "";
        String month = "";
        
        try {
            year = stndDt.substring( 0, 4 );
            month = stndDt.substring( 4, 6 );
            int iDay = lastDay( Integer.parseInt( year ), Integer.parseInt( month ) );
            lastDay = format( iDay, 2, '0', false );
        } catch ( Exception e ) {
            lastDay = "";
        }
        
        return lastDay;
    }
    
    /**
     * 메소드명 : getDayOfWeek
     * 설명 : 주어진 날짜 DATE 의 요일을 구함
     * 메소드인수1 : String orgDate - 8자리 날짜 데이터
     * 메소드리턴값: int 요일 - 1 - 일요일, 7 - 토요일
     */
    public static int getDayOfWeek( String orgDate ) {
        try {
            if ( orgDate == null || (orgDate.length() != 8 && orgDate.length() != 10) ) {
                return -1;
            }
            
            String dateString = orgDate;
            if ( orgDate.length() == 10 ) {
                dateString = XUtil.changeDateFormat( dateString );
            }
            
            int year = Integer.parseInt( dateString.substring( 0, 4 ) );
            int month = Integer.parseInt( dateString.substring( 4, 6 ) );
            int day = Integer.parseInt( dateString.substring( 6, 8 ) );
            
            GregorianCalendar cal = new GregorianCalendar();
            
            //MONTH 는 0 ~ 11 이므로 실제 데이터의 마이너스 1
            cal.set( year, month - 1, day );
            
            return cal.get( Calendar.DAY_OF_WEEK );
        } catch ( Exception e ) {
            return -1;
        }
    }
    
    /**
     * 메소드명 : lastDay
     * 설명 : 주어진 년 월의 마지막 일자(월말) 구하기
     * 메소드인수1 : int year
     * 메소드인수1 : int month
     * 메소드리턴값: int - 월말일
     */
    public static int lastDay( int year,int month ) throws ParseException {
        try {
            int day = 0;
            switch ( month ) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    day = 31;
                    break;
                case 2:
                    if ( (year % 4) == 0 ) {
                        if ( (year % 100) == 0 && (year % 400) != 0 ) {
                            day = 28;
                        } else {
                            day = 29;
                        }
                    } else {
                        day = 28;
                    }
                    break;
                default:
                    day = 30;
            }
            return day;
        } catch ( Exception e ) {
            e.printStackTrace();
            return 0;
        }
    }
    
    /**
     * 메소드명 : addYear
     * 설명 : 주어진 날짜 DATE 의 년수를 addYear 만큼 증가 또는 감소 시킴
     * 메소드인수1 : String dateString - 8자리 또는 6자리 또는 4자리의 날짜 데이터
     * 메소드인수2 : int addYear - 더할 년의 수 (증가시 +, 감소시 -)
     * 메소드리턴값 : String dateString - 8자리 또는 6자리 또는 4자리의 날짜 데이터
     */
    public static String addYear( String orgDate,int addYear ) {
        try {
            if ( orgDate == null ) {
                return null;
            }
            
            String dateString = orgDate;
            if ( getString( orgDate ).length() == 10 ) {
                dateString = changeDateFormat( orgDate );
            }
            
            int year = 0;
            int month = 2;
            int day = 1;
            String format = "yyyyMMdd";
            
            GregorianCalendar cal = new GregorianCalendar();
            if ( dateString.length() == 8 ) {
                year = Integer.parseInt( dateString.substring( 0, 4 ) );
                month = Integer.parseInt( dateString.substring( 4, 6 ) );
                day = Integer.parseInt( dateString.substring( 6 ) );
            } else if ( dateString.length() == 6 ) {
                year = Integer.parseInt( dateString.substring( 0, 4 ) );
                month = Integer.parseInt( dateString.substring( 4 ) );
                
                format = "yyyyMM";
            } else if ( dateString.length() == 4 ) {
                year = Integer.parseInt( dateString.substring( 0, 4 ) );
                
                format = "yyyy";
            } else {
                return null;
            }
            //MONTH 는 0 ~ 11 이므로 실제 데이터의 마이너스 1
            cal.set( year, month - 1, day );
            cal.add( GregorianCalendar.YEAR, addYear );
            
            SimpleDateFormat formatter = new SimpleDateFormat( format );
            String retDate = formatter.format( cal.getTime() );
            if ( retDate.length() == 8 ) {
                retDate = changeDateFormat( retDate );
            } else if ( retDate.length() == 6 ) {
                retDate = retDate.substring( 0, 4 ) + "-" + retDate.substring( 4 );
            }
            return retDate;
            
        } catch ( Exception e ) {
            return null;
        }
    }
    
    /**
     * 메소드명 : addMonth
     * 설명 : 주어진 날짜 DATE 의 달수를 addCount 만큼 증가 또는 감소 시킴
     * 메소드인수1 : String dateString - 8자리 또는 6자리의 날짜 데이터
     * 메소드인수2 : int addCount - 더할 달의 수 (증가시 +, 감소시 -)
     * 메소드리턴값: String dateString - 8자리 또는 6자리의 날짜 데이터
     */
    public static String addMonth( String orgDate,int addMonth ) {
        try {
            if ( orgDate == null ) {
                return null;
            }
            
            String dateString = orgDate;
            if ( getString( orgDate ).length() == 10 ) {
                dateString = changeDateFormat( dateString );
            }
            if ( getString( orgDate ).length() == 6 ) dateString = dateString + "01";
            
            int year = 0;
            int month = 2;
            int day = 1;
            String format = "yyyyMMdd";
            
            GregorianCalendar cal = new GregorianCalendar();
            if ( orgDate.length() == 8 ) {
                year = Integer.parseInt( dateString.substring( 0, 4 ) );
                month = Integer.parseInt( dateString.substring( 4, 6 ) );
                day = Integer.parseInt( dateString.substring( 6 ) );
            } else if ( orgDate.length() == 6 ) {
                year = Integer.parseInt( dateString.substring( 0, 4 ) );
                month = Integer.parseInt( dateString.substring( 4, 6 ) );
                
                format = "yyyyMM";
            } else {
                return null;
            }
            //MONTH 는 0 ~ 11 이므로 실제 데이터의 마이너스 1
            cal.set( year, month - 1, day );
            cal.add( GregorianCalendar.MONTH, addMonth );
            
            SimpleDateFormat formatter = new SimpleDateFormat( format );
            String retDate = formatter.format( cal.getTime() );
            if ( retDate.length() == 8 ) {
                retDate = changeDateFormat( retDate );
            } else if ( retDate.length() == 6 ) {
                retDate = retDate.substring( 0, 4 ) + "-" + retDate.substring( 4 );
            }
            retDate = retDate.replaceAll( "-", "" );
            return retDate;
        } catch ( Exception e ) {
            return null;
        }
    }
    
    /**
     * 메소드명 : addDay
     * 설명 : 주어진 날짜 DATE 의 일수를 addCount 만큼 증가 또는 감소 시킴
     * 메소드인수1 : String dateString - 8자리 날짜 데이터
     * 메소드인수2 : int addCount - 더할 일의 수 (증가시 +, 감소시 -)
     * 메소드리턴값: String dateString - 8자리 날짜 데이터
     */
    public static String addDay( String orgDate,int addDay ) {
        try {
            if ( orgDate == null || (orgDate.length() != 8 && orgDate.length() != 10) ) {
                return null;
            }
            
            String dateString = orgDate;
            if ( orgDate.length() == 10 ) {
                dateString = changeDateFormat( dateString );
            }
            
            int year = Integer.parseInt( dateString.substring( 0, 4 ) );
            int month = Integer.parseInt( dateString.substring( 4, 6 ) );
            int day = Integer.parseInt( dateString.substring( 6, 8 ) );
            
            GregorianCalendar cal = new GregorianCalendar();
            
            //MONTH 는 0 ~ 11 이므로 실제 데이터의 마이너스 1
            cal.set( year, month - 1, day );
            cal.add( GregorianCalendar.DAY_OF_YEAR, addDay );
            
            SimpleDateFormat formatter = new SimpleDateFormat( "yyyyMMdd" );
            String retDate = formatter.format( cal.getTime() );
            if ( retDate.length() == 10 ) {
                retDate = changeDateFormat( retDate );
            }
            return retDate;
        } catch ( Exception e ) {
            return null;
        }
    }
    
    /**
     * 메소드명 : daysBetween
     * 설명 : 주어진 날짜 DATE(from, to) 의 기간 구하기 to - from
     * 메소드인수1 : String from - 시작일자
     * 메소드인수2 : String to - 끝일자
     * 메소드리턴값: int - 기간(일수)
     */
    public static int daysBetween( String from,String to ) {
        return daysBetween( from, to, "yyyyMMdd" );
    }
    
    /**
     * 메소드명 : daysBetween
     * 설명 : 주어진 날짜 DATE(from, to) 의 기간(일) 구하기 to - from
     * 메소드인수1 : String from - 시작일자
     * 메소드인수2 : String to - 끝일자
     * 메소드인수2 : String format - 날짜포멧
     * 메소드리턴값: int - 기간(일수)
     */
    public static int daysBetween( String orgFrom,String orgTo,String format ) {
        try {
            if ( orgFrom == null || (orgFrom.length() != 10 && orgFrom.length() != 8) ) {
                return -1;
            }
            if ( orgTo == null || (orgTo.length() != 10 && orgTo.length() != 8) ) {
                return -1;
            }
            
            String from = orgFrom;
            String to = orgTo;
            
            if ( from.length() == 10 ) from = changeDateFormat( from );
            if ( to.length() == 10 ) to = changeDateFormat( to );
            
            SimpleDateFormat formatter = new SimpleDateFormat( format, java.util.Locale.KOREA );
            Date d1 = null;
            Date d2 = null;
            try {
                d1 = formatter.parse( from );
                d2 = formatter.parse( to );
            } catch ( ParseException e ) {
                return -1;
            }
            if ( !formatter.format( d1 ).equals( from ) ) return -1;
            if ( !formatter.format( d2 ).equals( to ) ) return -1;
            
            long duration = d2.getTime() - d1.getTime();
            
            //          if (duration < 0)
            //              return -1;
            return (int) (duration / (1000 * 60 * 60 * 24));
        } catch ( Exception e ) {
            e.printStackTrace();
            return -1;
        }
    }
    
    /**
     * 메소드명 : monthsBetween
     * 설명 : 주어진 날짜 DATE(from, to) 의 기간(달) 구하기 to - from
     * 메소드인수1 : String from - 시작일자
     * 메소드인수2 : String to - 끝일자
     * 메소드리턴값: int - 기간(달수)
     */
    public static int monthsBetween( String from,String to ) {
        return monthsBetween( from, to, "yyyyMMdd" );
    }
    
    /**
     * 메소드명 : monthsBetween
     * 설명 : 주어진 날짜 DATE(from, to) 의 기간(달) 구하기 to - from
     * 메소드인수1 : String from - 시작일자
     * 메소드인수2 : String to - 끝일자
     * 메소드인수2 : String format - 날짜포멧
     * 메소드리턴값: int - 기간(달수)
     */
    public static int monthsBetween( String orgFrom,String orgTo,String format ) {
        try {
            if ( orgFrom == null || (orgFrom.length() != 10 && orgFrom.length() != 8) ) {
                return -1;
            }
            if ( orgTo == null || (orgTo.length() != 10 && orgTo.length() != 8) ) {
                return -1;
            }
            
            String from = orgFrom;
            String to = orgTo;
            
            if ( from.length() == 10 ) from = changeDateFormat( from );
            if ( to.length() == 10 ) to = changeDateFormat( to );
            
            SimpleDateFormat formatter = new SimpleDateFormat( format, Locale.KOREA );
            Date fromDate = null;
            Date toDate = null;
            try {
                fromDate = formatter.parse( from );
                toDate = formatter.parse( to );
            } catch ( ParseException e ) {
                return -1;
            }
            
            // if two date are same, return 0.
            if ( fromDate.compareTo( toDate ) == 0 ) return 0;
            
            SimpleDateFormat yearFormat = new SimpleDateFormat( "yyyy", Locale.KOREA );
            SimpleDateFormat monthFormat = new SimpleDateFormat( "MM", Locale.KOREA );
            SimpleDateFormat dayFormat = new SimpleDateFormat( "dd", Locale.KOREA );
            
            int fromYear = Integer.parseInt( yearFormat.format( fromDate ) );
            int toYear = Integer.parseInt( yearFormat.format( toDate ) );
            int fromMonth = Integer.parseInt( monthFormat.format( fromDate ) );
            int toMonth = Integer.parseInt( monthFormat.format( toDate ) );
            int fromDay = Integer.parseInt( dayFormat.format( fromDate ) );
            int toDay = Integer.parseInt( dayFormat.format( toDate ) );
            
            int result = 0;
            result += ((toYear - fromYear) * 12);
            result += (toMonth - fromMonth);
            
            //          if (((toDay - fromDay) < 0) ) result += fromDate.compareTo(toDate);
            // ceil과 floor의 효과
            if ( ((toDay - fromDay) > 0) ) result += toDate.compareTo( fromDate );
            
            return result;
        } catch ( Exception e ) {
            return -1;
        }
    }
    
    /**
     * 메소드명 : changeYymm
     * 설명 : 년월(YYYYMM, YYYY-MM)을 지정한 자릿수에 맞는 포맷으로 변환처리함
     * 메소드인수1 : String sDate - 변환대상 문자열
     * 메소드리턴값 : String sOutput - 변환한 문자열
     */
    public static String changeYymm( String yymm,int len ) {
        yymm = getString( yymm );
        if ( yymm.length() < 6 ) return yymm;
        else if ( yymm.length() == len ) return yymm;
        
        if ( yymm.length() == 6 && len == 7 ) {
            yymm = yymm.substring( 0, 4 ) + "-" + yymm.substring( 4, 6 );
        } else if ( yymm.length() == 7 && len == 6 ) {
            yymm = yymm.replaceAll( "-", "" );
        }
        return yymm;
    }
    
    /**
     * 메소드명 : changeDate
     * 설명 : 날짜형식을 10자리, 8자리, 6자리(년도2자리) 포맷으로 변환처리함
     * 메소드인수1 : String sDate - 변환대상 문자열
     * 메소드리턴값 : String sOutput - 변환한 문자열
     */
    public static String changeDate( String sDate,int len ) {
        sDate = getString( sDate );
        
        if ( sDate.length() < 6 ) return sDate;
        else if ( sDate.length() == len ) return sDate;
        
        if ( sDate.length() == 8 && len == 6 ) {
            sDate = sDate.substring( 2 );
        } else if ( sDate.length() == 6 && len == 8 ) {
            sDate = "20" + sDate;
        } else if ( sDate.length() == 8 && len == 10 ) {
            sDate = sDate.substring( 0, 4 ) + "-" + sDate.substring( 4, 6 ) + "-" + sDate.substring( 6, 8 );
        } else if ( sDate.length() == 10 && len == 8 ) {
            sDate = sDate.replaceAll( "-", "" );
        }
        
        return sDate;
    }
    
    /**
     * 메소드명 : changeDateFormat
     * 설명 : 날짜 문자열 형식 변환변환
     * 메소드인수1 : String sDate - 변환대상 문자열
     * 메소드리턴값 : String sOutput - 변환한 문자열
     */
    public static String changeDateFormat( String sDate ) {
        try {
            String sOutput = "";
            if ( getString( sDate, "Y" ).length() == 10 ) {
                sOutput = sDate.substring( 0, 4 ) + sDate.substring( 5, 7 ) + sDate.substring( 8, 10 );
            } else if ( getString( sDate, "Y" ).length() == 8 ) {
                sOutput = sDate.substring( 0, 4 ) + "-" + sDate.substring( 4, 6 ) + "-" + sDate.substring( 6, 8 );
            }
            return sOutput;
        } catch ( Exception e ) {
            e.printStackTrace();
            return sDate;
        }
    }
    
    /**
     * 메소드명 : changeTime
     * 설명 : 지정한 자릿수(6자리, 8자리)의 시각표현으로 전환함
     * 메소드인수1 : String sTime - 변환대상 문자열
     * 메소드인수2 : int len - 자릿수
     * 메소드리턴값 : String sOutput - 변환한 문자열
     */
    public static String changeTime( String sTime,int len ) {
        sTime = getString( sTime );
        
        if ( sTime.length() < len ) return sTime;
        else if ( sTime.length() == len ) return sTime;
        else if ( sTime.length() == 6 && len == 8 ) {
            sTime = sTime.substring( 0, 2 ) + ":" + sTime.substring( 2, 4 ) + ":" + sTime.substring( 4, 6 );
        } else if ( sTime.length() == 8 && len == 6 ) {
            sTime = sTime.replaceAll( ":", "" );
        }
        return sTime;
    }
    
    /**
     * 메소드명 : changeTimeFormat
     * 설명 : 시간 문자열 형식 변환변환
     * 메소드인수1 : String sTime - 변환대상 문자열
     * 메소드리턴값 : String sOutput - 변환한 문자열
     */
    public static String changeTimeFormat( String sTime ) {
        try {
            String sOutput = "";
            if ( getString( sTime, "Y" ).length() == 8 ) {
                sOutput = sTime.substring( 0, 2 ) + sTime.substring( 3, 5 ) + sTime.substring( 6, 8 );
            } else if ( getString( sTime, "Y" ).length() == 6 ) {
                sOutput = sTime.substring( 0, 2 ) + ":" + sTime.substring( 2, 4 ) + ":" + sTime.substring( 4, 6 );
            }
            return sOutput;
        } catch ( Exception e ) {
            e.printStackTrace();
            return sTime;
        }
    }
    
    public static String changeDateTimeFormat(String dttm) {
    	try {
    		String sOutput = "";
    		if ( getString(dttm, "Y").length() == 19 ) {
    			sOutput = dttm.substring( 0, 4 ) + dttm.substring( 5, 7 ) + dttm.substring( 8, 10 )
    			        + dttm.substring( 11, 13 ) + dttm.substring( 14, 16 ) + dttm.substring( 17, 19 );
    		} else if ( getString(dttm, "Y").length() == 14 ) {
    			sOutput = dttm.substring( 0, 4 ) + "-" + dttm.substring( 4, 6 ) + "-" + dttm.substring( 6, 8 ) + " "
    			        + dttm.substring( 8, 10 ) + ":" + dttm.substring( 10, 12 ) + ":" + dttm.substring( 12, 14 );
    		}
    		return sOutput;
    	} catch ( Exception e ) {
    		e.printStackTrace();
    		return dttm;
    	}
    }
    
    /**
     * 메소드명 : isBetween
     * 설 명 : 두 비교 문자열의 사이에 존재하는 문자열인지 여부를 리턴함
     * 메소드인수1 : String valFrom - FROM 문자열
     * 메소드인수1 : String valTo - TO 문자열
     * 메소드리턴값 : boolean - 존재여부
     */
    public static boolean isBetween( String val,String valFr,String valTo ) {
        boolean isIn = false;
        
        double rangeFr = val.compareTo( valFr );
        double rangeTo = val.compareTo( valTo );
        
        if ( rangeFr >= 0 && rangeTo <= 0 ) {
            isIn = true;
        }
        
        return isIn;
    }
    
    /**
     * 메소드명 : isBetween
     * 설 명 : 두 비교 숫자값의 사이에 존재하는 숫자인지 여부를 리턴함
     * 메소드인수1 : double valFrom - FROM 값
     * 메소드인수1 : double valTo - TO 값
     * 메소드리턴값 : boolean - 존재여부
     */
    public static boolean isBetween( double val,double valFr,double valTo ) {
        boolean isIn = false;
        
        if ( val >= valFr && val <= valTo ) {
            isIn = true;
        }
        
        return isIn;
    }
    
    /**
     * 메소드명 : round
     * 설 명 : 반올림 처리함
     * 메소드인수1 : double val - 처리대상 숫자값
     * 메소드인수1 : int dgtCnt - 자릿수(0보다 큰값이면 소숫점 이하 자릿수, 0보다 작으면, 0이상 자릿수)
     * 메소드리턴값 : String out - 끝전처리결과값
     */
    public static String round( double val,int dgtCnt ) {
        String out = "0";
        
        double tmp = val;
        
        if ( dgtCnt >= 0 ) {
            out = calcDecimal( tmp, dgtCnt, BigDecimal.ROUND_HALF_UP );
        } else {
            double mt = Math.pow( 10, Math.abs( dgtCnt ) );
            tmp = Math.round( tmp / mt );
            tmp = tmp * mt;
            out = getDecimal( tmp );
        }
        
        return out;
    }
    
    public static String round( String val,int dgtCnt ) {
        if ( val == null || val == "0" ) val = "0";
        return round( Double.parseDouble( val ), dgtCnt );
    }
    
    /**
     * 메소드명 : floor
     * 설 명 : 지정한 자릿수에서 절사 처리함
     * 메소드인수1 : double val - 처리대상 숫자값
     * 메소드인수1 : int dgtCnt - 자릿수(0보다 큰값이면 소숫점 이하 자릿수, 0보다 작으면, 0이상 자릿수)
     * 메소드리턴값 : String out - 끝전처리결과값
     */
    public static String floor( double val,int dgtCnt ) {
        String out = "0";
        
        double tmp = val;
        
        if ( dgtCnt >= 0 ) {
            out = calcDecimal( tmp, dgtCnt, BigDecimal.ROUND_FLOOR );
        } else {
            double mt = Math.pow( 10, Math.abs( dgtCnt ) );
            tmp = getDouble( getDecimal( tmp / mt ) );
            tmp = tmp * mt;
            out = getDecimal( tmp );
        }
        
        return out;
    }
    
    public static String floor( String val,int dgtCnt ) {
        if ( val == null || val == "0" ) val = "0";
        return floor( Double.parseDouble( val ), dgtCnt );
    }
    
    // 힙메모리를 계산해서 파라메터에서 지정한 메모리 이하로 떨어지면 TRUE를 리턴함
    public static boolean isLowMemory( int minMemSize) {
        Runtime runtime = Runtime.getRuntime();
        int totHeapSize = Math.round( (runtime.totalMemory()) / (1024 * 1024) ); // 총   Heap Memory
        int balHeapSize = Math.round( (runtime.freeMemory()) / (1024 * 1024) ); // 잔여 Heap Memory
        int useHeapSize = totHeapSize - balHeapSize; // 사용 Heap Memory
        StringBuffer sb = new StringBuffer();
        sb.append( "================================================\r\n"                  );
        sb.append( "               Low Heap Memory                  \r\n"                  );
        sb.append( "------------------------------------------------\r\n"                  );
        sb.append( " [Total Heap] " + format( getComma( totHeapSize ), 5, ' ' ) + "MB\r\n" );
        sb.append( " [Used  Heap] " + format( getComma( useHeapSize ), 5, ' ' ) + "MB\r\n" );
        sb.append( " [Free  Heap] " + format( getComma( balHeapSize ), 5, ' ' ) + "MB\r\n" );
        sb.append( "================================================\r\n"                  );
        
        if ( balHeapSize < minMemSize ) { System.out.println(sb.toString());
            return true;
        } else {
            return false;
        }
    }

    // 한글 잘라내기
    public static String cutKor(String cont, int len)
    {
        return cutKor(cont, "", len, 0, false , false);
    }
    public static String cutKor(String cont,String key,int len,int nPrev,boolean isNotag,boolean isAdddot)
    { 
        String out = cont;
        int oF = 0, oL = 0, rF = 0, rL = 0;
        int lenPrev = 0;
        Pattern p = Pattern.compile("<(/?)([^<>]*)?>", Pattern.CASE_INSENSITIVE); // 태그제거 패턴
        
        // 패턴문자(태그)를 제거하려는 경우
        if (isNotag)
        {
            out = p.matcher(out).replaceAll("");

            // 태그 제거
            out = out.replaceAll("&", "&");
            out = out.replaceAll("(!/|\r|\n| )", ""); // 공백제거
        }
        
        try
        {
            byte[] bytes = out.getBytes(); // 바이트로 보관
            
            // 검색키의 위치를 지정함
            if (key != null && !key.equals(""))
            {
                lenPrev = (out.indexOf(key) == -1) ? 0 : out.indexOf(key); // 일단 위치찾고
                lenPrev = out.substring(0, lenPrev).getBytes().length; // 위치까지길이를 byte로 다시 구한다
                lenPrev = (lenPrev - nPrev >= 0) ? lenPrev - nPrev : 0; // 좀 앞부분부터 가져오도록한다.
            }
            
            // x부터 y길이만큼 잘라낸다. 한글안깨지게.
            int j = 0;
            
            if (lenPrev > 0) while (j < bytes.length)
            {
                if ((bytes[j] & 0x80) != 0)
                {
                    oF += 2;  rF += 3;
                    if (oF + 2 > lenPrev) break;
                    j += 3;
                }
                else
                {
                    if (oF + 1 > lenPrev) break;
                    ++oF;  ++rF;  ++j;
                }
            }
            j = rF;
            
            while (j < bytes.length)
            {
                if ((bytes[j] & 0x80) != 0)
                {
                    if (oL + 2 > len) break;
                    oL += 2; rL += 3; j += 3;
                }
                else
                {
                    if (oL + 1 > len) break;
                    ++oL;  ++rL;  ++j;
                }
            }
            
            out = new String(bytes, rF, rL); // charset 옵션
            
            if (isAdddot && rF + rL + 3 <= bytes.length)
            {
                out += "...";
            } 
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return out;
    }

    // 특정 문자열의 갯수를 셈
    public static int countOf(String cont, String key)
    {
        int iCnt = 0;
        Pattern pattern = Pattern.compile(key);
        Matcher matcher = pattern.matcher(cont);
        while (matcher.find())
        {
            iCnt = iCnt + 1;
        }
        return iCnt;
    }
    
    // 날짜를 넣으면 요일명을 리턴함
    public static String getWeekNm(String date) throws Exception
    {
        return getWeekNm(date, "yyyyMMdd");
    }
    
    public static String getWeekNm(String date, String dateType) throws Exception 
    {
        String day = "" ;
         
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateType) ;
        Date nDate = dateFormat.parse(date) ;
         
        Calendar cal = Calendar.getInstance() ;
        cal.setTime(nDate);
         
        int dayNum = cal.get(Calendar.DAY_OF_WEEK) ;
         
        switch(dayNum){
            case 1:
                day = "일요일";
                break ;
            case 2:
                day = "월요일";
                break ;
            case 3:
                day = "화요일";
                break ;
            case 4:
                day = "수요일";
                break ;
            case 5:
                day = "목요일";
                break ;
            case 6:
                day = "금요일";
                break ;
            case 7:
                day = "토요일";
                break ;
        }
        return day ;
    }
    
    public static int minuteBetween(String orgFrom, String orgTo)
    {
    	return minuteBetween(orgFrom, orgTo, "HHmmss");
    }
    
    public static int minuteBetween(String orgFrom, String orgTo, String format)
    {
    	try {
            if ( orgFrom == null || (orgFrom.length() != 8 && orgFrom.length() != 6 ) ) {
                return -1;
            }
            if ( orgTo == null || (orgTo.length() != 8 && orgTo.length() != 6 ) ) {
                return -1;
            }
            
            String from = orgFrom;
            String to = orgTo;
            
            if ( from.length() == 8 ) from = changeTimeFormat( from );
            if ( to.length() == 8 ) to = changeTimeFormat( to );
            
            SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.KOREA);
            Date fromTime = null;
            Date toTime = null;
            try {
            	fromTime = formatter.parse( from );
            	toTime = formatter.parse( to );
            } catch ( ParseException e ) {
                return -1;
            }
            
            long diff = fromTime.getTime() - toTime.getTime();
            
            // if two date are same, return 0.
            if ( fromTime.compareTo(toTime) == 0 ) return 0;
            
            BigDecimal sec = new BigDecimal(diff).divide(new BigDecimal(1000));
            BigDecimal minute = new BigDecimal(diff).divide(new BigDecimal(1000)).divide(new BigDecimal(60));
            BigDecimal hour = new BigDecimal(diff).divide(new BigDecimal(1000)).divide(new BigDecimal(60)).divide(new BigDecimal(60));
                        
            return minute.intValue();
        } catch ( Exception e ) {
            return -1;
        }
    }
    
    public static ModelAndView getExcelView(ExcelVO vo)
    {
    	List<List<String>> dataList = new ArrayList<List<String>>();
        List<String> data;
        
        for ( int i = 0 ; i < vo.getData().size() ; i++ )
        {
        	Map<String, Object> info = (Map<String, Object>) vo.getData().get(i);
        	
            data = new ArrayList<String>();
            for ( String key : vo.getKeys() )
            {
            	String value = getString(String.valueOf(info.get(key)));
//            	System.out.println("key/val: " + key + "/" + value + "**");
            	if ( key.endsWith("Dttm") ) {
            		value = changeDateTimeFormat(value);
            	} else if ( key.endsWith("Dt") ) {
            		value = changeDateFormat(value);
            	} else if ( key.endsWith("Tm") ) {
            		value = changeTimeFormat(value);
            	} else if ( key.endsWith("Amt") || key.endsWith("fee") ) {
            		value = getComma(value);
            	}
            	data.add(value);
            }
            dataList.add(data);
        }

        Map<String, Object> map = new HashMap<>();
        map.put(ExcelConstant.FILE_NAME, "excel");
        map.put(ExcelConstant.HEAD, Arrays.asList(vo.getHeaders()));
        map.put(ExcelConstant.BODY, dataList);
		
		return new ModelAndView("excelXlsView", map);
    }
}
