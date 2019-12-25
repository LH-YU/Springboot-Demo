//package com.example.demo.util;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONException;
//import com.alibaba.fastjson.JSONObject;
//import com.google.common.base.Charsets;
//import com.google.common.io.CharStreams;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.methods.HttpUriRequest;
//import org.apache.http.config.Registry;
//import org.apache.http.config.RegistryBuilder;
//import org.apache.http.conn.socket.ConnectionSocketFactory;
//import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
//import org.apache.http.conn.socket.PlainConnectionSocketFactory;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.net.ssl.SSLContext;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.UnknownHostException;
//import java.security.NoSuchAlgorithmException;
//
///**
// *
// * Http远程连接的工具类
// * @author LH-Yu
// * @date 2018-11-12
// */
//public class HttpClientUtil {
//
//    public static final String APPID = "wx86b9c08d1269ab6e";
//    public static final String APPSECRET = "ef86641e32c01304acc9a75176b5bbf7";
//
//    private final static Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);
//
//    private HttpClientUtil() {}
//
//    private static HttpConnectionManager httpConnectionManager = new HttpConnectionManager();
//
//
//    /**
//     *
//     * 通过指定地址和参数发送post请求以获取数据
//     *
//     * @param uri 请求地址
//     * @param param 请求参数，此参数将转换为json格式的字符串
//     * @return 返回的JsonNode对象，若发生异常或无结果，则此对象 {@code null}
//     */
//    public static JSONObject sendPost(String uri, JSON param){
//        LOGGER.info("[HTTP-POST请求地址]:{}",uri);
//        LOGGER.debug("[HTTP-POST请求参数]:{}",param);
//        HttpPost httpPost = new HttpPost(uri);
//        if (param != null) {
//            httpPost.setEntity(new StringEntity(param.toJSONString(), ContentType.APPLICATION_JSON));
//        }
//        return httpActionJSONObject(httpPost);
//    }
//
//    public static String proxyPost(String uri, String param,String header){
//        LOGGER.info("[HTTP-POST请求地址]:{}",uri);
//        LOGGER.debug("[HTTP-POST请求参数]:{}",param);
//        CloseableHttpResponse httpResponse = null;
//        String result="";
//        InputStream inputStream=null;
//        HttpPost httpPost = new HttpPost(uri);
//        if (param != null) {
//            httpPost.setEntity(new StringEntity(param, ContentType.APPLICATION_JSON));
//            httpPost.setHeader("platform",header);
//        }
//        try {
//             httpResponse = getHttpClient().execute(httpPost);
//             inputStream = httpResponse.getEntity().getContent();
//            result = CharStreams.toString(new InputStreamReader(httpResponse.getEntity().getContent(), Charsets.UTF_8));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            closeReturnInputStream(inputStream);
//            closeHttpResponse(httpResponse);
//        }
//        return result;
//    }
//
//    /**
//     *
//     * 通过指定地址和参数发送get请求以获取数据
//     *
//     * @param uri 请求地址
//     * @return 返回的JsonNode对象，若发生异常或无结果，则此对象 {@code null}
//     */
//    public static JSONObject sendGet(String uri){
//        LOGGER.info("[HTTP-GET:URL]： {}",uri);
//        HttpGet httpGet = new HttpGet(uri);
//        return httpActionJSONObject(httpGet);
//    }
//
//    public static String sendGetRefund(String uri){
//        HttpGet httpGet = new HttpGet(uri);
//        CloseableHttpResponse response = null;
//        long start = System.currentTimeMillis();
//        try {
//            response = getHttpClient().execute(httpGet);
//        } catch (UnknownHostException e) {
//            LOGGER.error("未发现主机",e);
//        } catch (IOException e) {
//            LOGGER.error("IO异常",e);
//        }
//        InputStream inputStream = null;
//        try {
//            if (response != null) {
//                inputStream = response.getEntity().getContent();
//            }
//        } catch (IOException e) {
//            LOGGER.error("IO异常",e);
//        }
//        long getStream = System.currentTimeMillis()-start;
//        String resultStr = null;
//        try {
//            resultStr = CharStreams.toString(new InputStreamReader(inputStream, Charsets.UTF_8));
//        } catch (IOException e) {
//            LOGGER.error("[HTTP返回值]解析数据流异常，服务器返回：\n", resultStr);
//        }
//        return resultStr;
//    }
//
//
//    /**
//     *
//     * 通过指定地址和参数发送post请求以获取数据
//     *
//     * @param uri 请求地址
//     * @return 返回的JsonNode对象，若发生异常或无结果，则此对象 {@code null}
//     */
//    public static JSONObject sendPost(String uri){
//        return sendPost(uri,  new JSONObject());
//    }
//
//    private static JSONObject httpActionJSONObject(HttpUriRequest request){
//        CloseableHttpResponse response = null;
//        long start = System.currentTimeMillis();
//        try {
//            response = getHttpClient().execute(request);
//        } catch (UnknownHostException e) {
//            LOGGER.error("未发现主机",e);
//        } catch (IOException e) {
//            LOGGER.error("IO异常",e);
//        }
//
//        InputStream inputStream = null;
//        try {
//            if (response != null) {
//                inputStream = response.getEntity().getContent();
//            }
//        } catch (IOException e) {
//            LOGGER.error("IO异常",e);
//        }
//        long getStream = System.currentTimeMillis()-start;
//        LOGGER.debug("[HTTP返回值][阻塞耗时]发出请求->流:{} ms",getStream);
//        JSONObject result = null;
//        String resultStr = null;
//        try {
//            resultStr = CharStreams.toString(new InputStreamReader(inputStream, Charsets.UTF_8));
//        } catch (IOException e) {
//            LOGGER.error("[HTTP返回值]解析数据流异常，服务器返回：\n", resultStr);
//            return result;
//        }
//
//        try {
//            result = JSONObject.parseObject(resultStr);
//        } catch (JSONException e) {
//            if (request.getMethod() == HttpPost.METHOD_NAME) {
//                LOGGER.error("[HTTP返回值]解析JSON字符串错误,请求地址为：{},请求参数：{},字符串:\n{}",
//                        request.getRequestLine(), "预留",resultStr);
//            }
//            return result;
//        }
//        closeReturnInputStream(inputStream);
//        closeHttpResponse(response);
//        LOGGER.debug("[HTTP返回值] 如下：\n{}",result.toJSONString()); //格式化输出
//        long jsonNodeTime = System.currentTimeMillis() - start;
//        int size = result.toJSONString().getBytes(Charsets.UTF_8).length;
//        LOGGER.info("[HTTP返回值][阻塞耗时]发出请求->JsonNode:{} ms , 发出请求->统计字节:{} ms ,大小:{} byte",
//                jsonNodeTime,
//                System.currentTimeMillis()-start,size);
//        return result;
//
//    }
//
//    private static CloseableHttpClient getHttpClient(){
//        return httpConnectionManager.getHttpClient();
//    }
//
//    /**
//     * 关闭请求返回体
//     * @param closeableHttpResponse 请求返回体
//     */
//    private static void closeHttpResponse(CloseableHttpResponse closeableHttpResponse){
//        if (closeableHttpResponse != null) {
//            try {
//                closeableHttpResponse.close();
//            } catch (IOException e) {
//                LOGGER.error("CloseableHttpResponse 返回体 关闭异常",e);
//            }
//
//        }
//    }
//
//    /**
//     *
//     * @param inputStream 获取的IO流
//     */
//    private static void closeReturnInputStream(InputStream inputStream){
//        if (inputStream != null) {
//            try {
//                inputStream.close();
//            } catch (IOException e) {
//                LOGGER.error("远程获取的数据输入流关闭异常",e);
//            }
//        }
//    }
//
//
//    /**
//     * 格式化输出json字符串
//     * @param jsonNode
//     * @return
//     */
////    private static String prettyPrinter(JsonNode jsonNode){
////        return JacksonUtil.defaultPrettyPrinter(jsonNode); //格式化输出
////    }
//
//    /**
//     *  获取返回的大小分别以（B,KB,MB,GB）为后缀的字符串显示
//     * @param size byte数
//     */
//    private static String getPrintSize(long size) {
//        //如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
//        if (size < 1024) {
//            return String.valueOf(size) + "B";
//        } else {
//            size = size / 1024;
//        }
//        //如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
//        //因为还没有到达要使用另一个单位的时候
//        //接下去以此类推
//        if (size < 1024) {
//            return String.valueOf(size) + "KB";
//        } else {
//            size = size / 1024;
//        }
//        if (size < 1024) {
//            //因为如果以MB为单位的话，要保留最后1位小数，
//            //因此，把此数乘以100之后再取余
//            size = size * 100;
//            return String.valueOf((size / 100)) + "."
//                    + String.valueOf((size % 100)) + "MB";
//        } else {
//            //否则如果要以GB为单位的，先除于1024再作同样的处理
//            size = size * 100 / 1024;
//            return String.valueOf((size / 100)) + "."
//                    + String.valueOf((size % 100)) + "GB";
//        }
//    }
//
//    private static class HttpConnectionManager {
//
//        private final static int MAX_TOTAL_CONNECTIONS = 200;//最大连接数
//        private final static int MAX_ROUTE_CONNECTIONS = 20; //最大路由连接数
//
//        private PoolingHttpClientConnectionManager cm = null;
//
//        HttpConnectionManager() {
//            LayeredConnectionSocketFactory sslsf = null;
//            try {
//                sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault());
//            } catch (NoSuchAlgorithmException e) {
//                e.printStackTrace();
//            }
//
//            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
//                    .register("https", sslsf)
//                    .register("http", new PlainConnectionSocketFactory())
//                    .build();
//            cm =new PoolingHttpClientConnectionManager(socketFactoryRegistry);
//            cm.setMaxTotal(MAX_TOTAL_CONNECTIONS);
//            cm.setDefaultMaxPerRoute(MAX_ROUTE_CONNECTIONS);
//        }
//
//        CloseableHttpClient getHttpClient() {
//            CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
//            /*CloseableHttpClient httpClient = HttpClients.createDefault();//如果不采用连接池就是这种方式获取连接*/
//            return httpClient;
//        }
//    }
//
////    public static void main(String[] args) {
//////        JSONObject param = new JSONObject();
//////        param.put("id", "773f1314-08b8-489b-996c-5791dfea57e2");
//////        JSONObject jsonObject = HttpClientUtil.sendPost("http://47.104.252.234:8083/_framework/getfile",param);
////        JSONObject jsonObject = HttpClientUtil.sendGet("http://118.190.202.65:8083/torefund?&order_id=1547696449481d66c9c8ec6ba46d79b");
////        System.out.println(jsonObject);
////
////        System.out.println((int)(1+Math.random()*(5-1+1)));
////
////
////    }
//
//}
