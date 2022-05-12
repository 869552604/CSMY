package com.ks.bean;

import java.util.List;

public class RackBookSelfBean {

    /**
     * status : 200
     * data : [{"book_id":"201491","is_top":"2","read_chapter_id":"309616","page":"0","add_at":"2022-01-07 16:19:30","read_at":"","is_update":"1","book":{"id":"201491","is_main":"1","main_id":"0","gender":"1","category":"都市言情","title":"绝代天医","intro":"一代天医归来，医术通神，武力惊天，敌人，他部踩在脚下。","author":"忧之","actor":"秦飞江梦","state":"连载","words":"2246967","score":"5.4","chap_num":"3855","last_cid":"0","last_chap":"","last_at":"","last_in":"2021-04-14 17:10:35","updated_at":"2021-11-18 09:45:57","cover":"img/0/0/1637199957.jpg?test=test","read_user_count":"0","click_count":"1","v":"1637199957"},"chapter":{"id":"309616","title":"第5章  大胆挑衅","v":"1"}},{"book_id":"144190","is_top":"2","read_chapter_id":"0","page":"0","add_at":"2022-01-07 15:51:15","read_at":"2022-01-07 15:51:15","is_update":"1","book":{"id":"144190","is_main":"1","main_id":"0","gender":"1","category":"军事历史","title":"穿越了的学霸","intro":"21世纪名校学霸，魂穿古代，变成公主府上的一名家奴。\n没有空间，没有系统，没有白胡子老爷爷，连关于这个世界的记忆都没有\u2026\u2026\n不怕！中华上下五千年的劳动人民智慧汇集于一身，赛诗会、斗恶徒、娶美女，且看小人物如何在这个时代混的风生水起，走向人生巅峰。","author":"暗夜茗香","actor":"","state":"连载","words":"4043213","score":"5.8","chap_num":"1526","last_cid":"0","last_chap":"","last_at":"","last_in":"2021-01-18 11:20:14","updated_at":"2021-11-18 09:45:52","cover":"img/0/0/1637199952.jpg?test=test","read_user_count":"0","click_count":"1","v":"1637199952"},"chapter":null}]
     * message : 成功
     */

    public String status;
    public String message;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * book_id : 201491
         * is_top : 2
         * read_chapter_id : 309616
         * page : 0
         * add_at : 2022-01-07 16:19:30
         * read_at : 
         * is_update : 1
         * book : {"id":"201491","is_main":"1","main_id":"0","gender":"1","category":"都市言情","title":"绝代天医","intro":"一代天医归来，医术通神，武力惊天，敌人，他部踩在脚下。","author":"忧之","actor":"秦飞江梦","state":"连载","words":"2246967","score":"5.4","chap_num":"3855","last_cid":"0","last_chap":"","last_at":"","last_in":"2021-04-14 17:10:35","updated_at":"2021-11-18 09:45:57","cover":"img/0/0/1637199957.jpg?test=test","read_user_count":"0","click_count":"1","v":"1637199957"}
         * chapter : {"id":"309616","title":"第5章  大胆挑衅","v":"1"}
         */

        public String book_id;
        public String is_top;
        public String read_chapter_id;
        public String page;
        public String add_at;
        public String read_at;
        public String is_update;
        public BookBean book;
        public ChapterBean chapter;



        public static class BookBean {
            /**
             * id : 201491
             * is_main : 1
             * main_id : 0
             * gender : 1
             * category : 都市言情
             * title : 绝代天医
             * intro : 一代天医归来，医术通神，武力惊天，敌人，他部踩在脚下。
             * author : 忧之
             * actor : 秦飞江梦
             * state : 连载
             * words : 2246967
             * score : 5.4
             * chap_num : 3855
             * last_cid : 0
             * last_chap : 
             * last_at : 
             * last_in : 2021-04-14 17:10:35
             * updated_at : 2021-11-18 09:45:57
             * cover : img/0/0/1637199957.jpg?test=test
             * read_user_count : 0
             * click_count : 1
             * v : 1637199957
             */

            public String id;
            public String is_main;
            public String main_id;
            public String gender;
            public String category;
            public String title;
            public String intro;
            public String author;
            public String actor;
            public String state;
            public String words;
            public String score;
            public String chap_num;
            public String last_cid;
            public String last_chap;
            public String last_at;
            public String last_in;
            public String updated_at;
            public String cover;
            public String read_user_count;
            public String click_count;
            public String v;

        }

        public static class ChapterBean {
            /**
             * id : 309616
             * title : 第5章  大胆挑衅
             * v : 1
             */

            public String id;
            public String title;
            public String v;

        }
    }
}
