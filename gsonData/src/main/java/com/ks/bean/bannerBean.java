package com.ks.bean;

import java.util.List;

public class bannerBean {

    /**
     * status : 200
     * data : [{"id":1,"cover":"images/default_image.jpeg","dump_type":"1","dump_address":""}]
     * message : 成功
     */

    public String status;
    public String message;
    public List<DataBean> data;


    public static class DataBean {
        /**
         * id : 1
         * cover : images/default_image.jpeg
         * dump_type : 1
         * dump_address :
         */

        public int id;
        public String cover;
        public String dump_type;
        public String dump_address;

    }
}
