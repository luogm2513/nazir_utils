package com.nazir.object;

/**
 * @Type UrlDTO
 * @Desc
 * @author luo
 * @date 2016年6月14日
 * @Version V1.0
 */
public class UrlDTO implements Comparable<UrlDTO> {
    private String url;
    private String param;
    private String requestMethod;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public int compareTo(UrlDTO o) {
        if (o != null) {
            return this.url.compareTo(o.getUrl());
        }
        return 0;
    }

}
