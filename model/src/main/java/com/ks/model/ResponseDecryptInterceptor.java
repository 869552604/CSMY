package com.ks.model;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Created by Administrator on 2022/5/7.
 * xfs
 * 解密拦截器
 */
public class ResponseDecryptInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        if (response.isSuccessful()) {
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                BufferedSource soure = responseBody.source();
                soure.request(Long.MAX_VALUE);
                Buffer buffer = soure.buffer();
            }

            if (responseBody != null) {
                /*开始解密*/
                try {
                    BufferedSource source = responseBody.source();
                    source.request(java.lang.Long.MAX_VALUE);
                    Buffer buffer = source.buffer();
                    Charset charset = Charset.forName("UTF-8");
                    MediaType contentType = responseBody.contentType();
                    if (contentType != null) {
                        charset = contentType.charset(charset);
                    }
                    String bodyString = buffer.clone().readString(charset);
                    String responseData = ManagerEncrypt.aesDncode(bodyString.substring(16), bodyString.substring(0, 16));
                    /*将解密后的明文返回*/
                    ResponseBody newResponseBody = ResponseBody.create(contentType, responseData.trim());
                    response = response.newBuilder().body(newResponseBody).build();
                } catch (Exception e) {
                    /*异常说明解密失败 信息被篡改 直接返回即可 */
                    return response;
                }
            }
        }


        return response;
    }

}

