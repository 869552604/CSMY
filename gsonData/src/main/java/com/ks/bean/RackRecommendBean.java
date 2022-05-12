package com.ks.bean;

import java.util.List;

public class RackRecommendBean {

    /**
     * status : 200
     * data : [{"id":"51315","gender":"2","category":"虐恋","title":"一夜惊喜：顾少轻点爱","intro":"二十岁的霍念念，突然得知自己罹患脑癌，只想死前放纵一把的她，误打误撞的和帝国集团总裁顾廷深过了夜，从此，两人纠缠不断，而霍念念的人生也开始彻底反转\u2026\u2026","author":"天仙子","state":"连载","words":"3220640","chap_num":"3877","all_hits":"102094","last_cid":"0","last_chap":"","last_at":null,"updated_at":"2021-11-18 09:45:44","cover":"images/default_image.jpeg"},{"id":"54315","gender":"1","category":"异能","title":"都市之超神玩家","intro":"玩游戏没外挂怎么行？开一万个小号挂机刷怪！ 屌丝李风被电脑中毒，居然拥有了无限开小号能力！热血江湖玩家、WOW玩家、问道玩家、征服玩家等老游戏玩家必看\u2014\u2014挂机天王！","author":"砖家老李","state":"完本","words":"5943110","chap_num":"7095","all_hits":"872167","last_cid":"0","last_chap":"","last_at":null,"updated_at":"2021-11-18 09:45:44","cover":"images/default_image.jpeg"}]
     * message : 成功
     */

    public String status;
    public String message;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : 51315
         * gender : 2
         * category : 虐恋
         * title : 一夜惊喜：顾少轻点爱
         * intro : 二十岁的霍念念，突然得知自己罹患脑癌，只想死前放纵一把的她，误打误撞的和帝国集团总裁顾廷深过了夜，从此，两人纠缠不断，而霍念念的人生也开始彻底反转……
         * author : 天仙子
         * state : 连载
         * words : 3220640
         * chap_num : 3877
         * all_hits : 102094
         * last_cid : 0
         * last_chap : 
         * last_at : null
         * updated_at : 2021-11-18 09:45:44
         * cover : images/default_image.jpeg
         */

        public String id;
        public String gender;
        public String category;
        public String title;
        public String intro;
        public String author;
        public String state;
        public String words;
        public String chap_num;
        public String all_hits;
        public String last_cid;
        public String last_chap;
        public Object last_at;
        public String updated_at;
        public String cover;


        public boolean ifCheck =false;
    }
}
