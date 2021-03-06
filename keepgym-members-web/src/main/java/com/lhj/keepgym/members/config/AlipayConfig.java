package com.lhj.keepgym.members.config;



/**
 * alipay配置类
 * @author Shinelon
 */
public class AlipayConfig {


    /**
     * 应用ID
     */
    public static String app_id = "2016101800715923";


    /**
     * 商户私钥
     */
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCX6ECwlc+3WFs/cXGS6kljSoHkcka6PUuOqSdPARS9nyngZGVn0k4gEex052wZolWKsigGHm495xNWhJcB6OREAr9YU8JfaS+wxvr1ZL7l5DEL394ssdoTbH8BhIvXKzTfhR9HR9kPtp7cl0LrA9rZaJ15KYbfwZbyfX5OJ6LLgejamZk945UhFkDtY634h73c+bRUWYpO08x0V1aP0q5O7zSCc4eoRbZBz4jCY5TZQXGTKcgXcCfK7dcCdIG0OaRKHAzS6NaK59Jmr67vy6wctya7kFmzKjWOKhBcWUDW+HhwsUs2x3wJyTN6z/UFOMzJxzXkPCmnUwv7EBxKtFoDAgMBAAECggEAIhWh1j13Iwt3Re/9bNqW5umD5a9igU1Zk8QOpuJ2NentqoS8YCRHFVkQNlbq4YGUMg+xfVX8pHUh2Krdezc3aK85IeeBWIkwgzjSfJdDYi/mdWSg5GRvqyYQBVYpTnlKXtDu6L6NTMJMOPDTugneZCPeGZ6gvbohVX2lBoJMO98rllz/+Pfv3zOFkV9TN+xRKA6lBLbkrg82+6/IOe9kK+/UzB/+W3Dz7Pn6+NcflD0p0P0IpOjG21ljzxmGV1ihetpErnV+LKOnFtD9gOtf0D9DL93IkTUA+NooE/Qt0Cf237EzSA8vfRTZFPH8+MN1rVl5a9gzh5bSfiDVDC5HmQKBgQDo/330KOG5O14YHyl5sjupV53ClCPpdN/Kgj6Uwe5Rb0GZS6WW/q1RJi0bK4+GZ+f+k36BuJEhFShHx9344GluHKcpqeRVwIEQEKL5i2Rf7DBNy3T0WjRBhbpRYK1cdz5cHdo/SrdbQaBpT0Nl9wopk9WNZSjPKeCLENXq5FlgJwKBgQCm518DEvRep34asE2WbCcMu4ohFGsnfmacUDtDZvENV/CVYf/7kgSe31TI3i+1LietAMS3ROyk52IZTxKZ34M/0FKVDPOANr2reEq2yVfguujwmDWSULLs4V5NqmtBoNusvNMzGvjlFOR9v31qFTmvQtdoyohwsLGBZzxrE2VExQKBgEwRPXm87xSeQTX21n+YpMnAhQzFeIP7WCSxKOU/+yIzBGObws/tDYB0yj0Yl571xVPjq2WGcDswwRnOMrXR7hm42QpNIPKuCnTIOazmyTpXzd1YEzLDUFQj6JQwz+hkNcO1K0+GgR4V6tz2t7ZOVM8GKK4k3JHlxh0gvfYV3uf5AoGAaMD8/CPwZlw/pKaU+91DoRUv+9Uh7/bAlkkw+PvqPVK9eDS7An1CEyjewPfa/59JXacG0HQdBt8GzhW3+gW0DeBFhLFNP2KcJrvoQNMcKbuGKkOOFBg8RLqqgpp8mQNJ/R/Ceufp4XX8dwQxxdwuiVatdmLvwbLUpgBShm30+HUCgYEArjmsgQrjYVIfG/pO5kUJZ+Im0hthuUdq53/298S101xIUJCgczl+WAOv4H63AIpM8QqTSTIz6Re/iRTr15h3g4XEWnlACaAE9tb4O0cP/+XyrDxFRfDbq4UrR3jn2EQ9I1tc9Gme04Q+iIT3L/PHu/EYTgkCgRh+3cdTrG1AR3Y=";

    /**
     *  支付宝公钥
     */
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAr1FRLd9mSa9ndGwgnxxvOejEr8BYNL6F5EHwUwqE/XSuJZToQ/FYXkMQvUuJzfSL2TWEy8iKGfJl0baFU1YJ5AAgf1T8wHoXw5/p6akVO8yFwSEQl8JLjp63aCsYaiey7PuNR4Wca4rfPHgc2/uJCfe0cumA/gKM4LRWCRUHmrjYjbeapQZ8/Wb/jBsx+kLbxbKwlv/dQrJBepgfkSm6AHJqNLZCCPJgj9F7gtvcrHRGPy3L+fEJx116a+ZII/697WvXY1UjlUWTZxB/CXNvCGATnRTUPoaDO3SmPJZ4qLs0/nxuOuoVAcgUxJVSchH5lHzZ2hJLV5kTlX0V4g1TYQIDAQAB";

    /**
     * 服务器异步通知页面路径
     */
    public static String notify_url = "http://47.98.241.105:8081/alipayNotify";

    /**
     * 服务器同步通知页面路径即付款成功后返回的url
     */
    public static String return_url = "http://47.98.241.105:8081/alipayCallback";


    /**
     * 签名方式
     */
    public static String sign_type = "RSA2";

    /**
     * 编码格式
     */
    public static String charset = "utf-8";

    /**
     * 支付宝网关
     */
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

}

