package com.ks.bean;

public class LoginPhoneBean {

    /**
     * status : 200
     * data : {"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJodHRwOi8vMTI3LjAuMC4xL3YxL3VzZXJzL2xvZ2luIiwiaWF0IjoxNjQzMDA1NTc2LCJleHAiOjIwMDMwMDU1NzYsIm5iZiI6MTY0MzAwNTU3NiwianRpIjoiYmpqR0tJd0QwV2hTRHJWRCIsInN1YiI6IjI2IiwicHJ2IjoiZjZiNzE1NDlkYjhjMmM0MmI3NTgyN2FhNDRmMDJiN2VlNTI5ZDI0ZCJ9.8AFO9KL_2WfWDjZyeHT8yeXSZIog0MDAalQhUFV43tLY98Qm8qmRlobAFQ30vk-h2UyQGoPq60L_LEMPYT9quw","userInfo":{"isOld":0,"id":"26","tourist_id":"书友_888","nickName":"书友_888","avatarUrl":"avatar/1e2b9bb3d75c103589dac45c817252dd/1643081578.jpeg","type":"1","vip_expire":"2022-01-22 11:11:11","v":"1643005576"}}
     * message : 成功
     */

    public String status;
    public DataBean data;
    public String message;

    public static class DataBean {
        /**
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJodHRwOi8vMTI3LjAuMC4xL3YxL3VzZXJzL2xvZ2luIiwiaWF0IjoxNjQzMDA1NTc2LCJleHAiOjIwMDMwMDU1NzYsIm5iZiI6MTY0MzAwNTU3NiwianRpIjoiYmpqR0tJd0QwV2hTRHJWRCIsInN1YiI6IjI2IiwicHJ2IjoiZjZiNzE1NDlkYjhjMmM0MmI3NTgyN2FhNDRmMDJiN2VlNTI5ZDI0ZCJ9.8AFO9KL_2WfWDjZyeHT8yeXSZIog0MDAalQhUFV43tLY98Qm8qmRlobAFQ30vk-h2UyQGoPq60L_LEMPYT9quw
         * userInfo : {"isOld":0,"id":"26","tourist_id":"书友_888","nickName":"书友_888","avatarUrl":"avatar/1e2b9bb3d75c103589dac45c817252dd/1643081578.jpeg","type":"1","vip_expire":"2022-01-22 11:11:11","v":"1643005576"}
         */

        public String token;
        public UserInfoBean userInfo;


        public static class UserInfoBean {
            /**
             * isOld : 0
             * id : 26
             * tourist_id : 书友_888
             * nickName : 书友_888
             * avatarUrl : avatar/1e2b9bb3d75c103589dac45c817252dd/1643081578.jpeg
             * type : 1
             * vip_expire : 2022-01-22 11:11:11
             * v : 1643005576
             */

            public int isOld;
            public String id;
            public String tourist_id;
            public String nickName;
            public String avatarUrl;
            public String type;
            public String vip_expire;
            public String v;

        }
    }
}
