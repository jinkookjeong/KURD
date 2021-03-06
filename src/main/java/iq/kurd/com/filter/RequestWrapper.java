package iq.kurd.com.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class RequestWrapper extends HttpServletRequestWrapper
{
    public RequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    public String[] getParameterValues(String parameter) {

      String[] values = super.getParameterValues(parameter);
      if (values==null)  {
                  return null;
          }
      int count = values.length;
      String[] encodedValues = new String[count];
      for (int i = 0; i < count; i++) {
                 encodedValues[i] = filter(values[i]);
       }
      return encodedValues;
    }

    public String getParameter(String parameter) {
          String value = super.getParameter(parameter);
          if (value == null) {
                 return null;
                  }
          return filter(value);
    }

    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (value == null)
            return null;
        return filter(value);

    }

    private String filter(String input) {
//    	String chgValue="";
        if(input==null) {
            return null;
        }
        /**
         * 외부망에서만 적용
         */
//        String clean = new HTMLInputFilter().filter(input.replaceAll("%22", "").replaceAll("..\\\\", "").replaceAll("%22", "").replaceAll("\"", "%22").replaceAll("\'","%27"));
        String clean = new HTMLInputFilter().filter(input.replaceAll("%22", "").replaceAll("%22", "").replaceAll("\"", "%22").replaceAll("\'","%27"));
        clean=clean.replaceAll("<", "%3C").replaceAll(">", "%3E").replaceAll("[.]{2}[/]", "").replaceAll("[.]{2}[\\\\]", "");
//        clean=clean.replaceAll("system.", "").replaceAll("--", "");

        return clean;
//        HTMLInputFilter filter = new HTMLInputFilter(true);

//        chgValue = filter.filter(input);

//        return chgValue;
    }
}

