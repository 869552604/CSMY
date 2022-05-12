package com.ks.bean;

public class UserBean {

    /**
     * status : 200
     * data : {"id":"1","nickName":"书友_888","avatar":"local:avatar","gender":"1","mobile":"13232323281","intro":"","birthday":"1999-01-01","type":"1","vip_expire":"","v":"1649930841","read_count":"0","read_time":"0","like_count":"0","personalise_ad":"1","personalise_recommend":"1"}
     * message : 成功
     */

    public String status;
    public DataBean data;
    public String message;


    public static class DataBean {
        /**
         * id : 1
         * nickName : 书友_888
         * avatar : local:avatar
         * gender : 1
         * mobile : 13232323281
         * intro :
         * birthday : 1999-01-01
         * type : 1
         * vip_expire :
         * v : 1649930841
         * read_count : 0
         * read_time : 0
         * like_count : 0
         * personalise_ad : 1
         * personalise_recommend : 1
         */

        public String id;
        public String nickName;
        public String avatar;
        public String gender;
        public String mobile;
        public String intro;
        public String birthday;
        public String type;
        public String vip_expire;
        public String v;
        public String read_count;
        public String read_time;
        public String like_count;
        public String personalise_ad;
        public String personalise_recommend;

    }
}
