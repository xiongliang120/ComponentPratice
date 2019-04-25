package com.xiongliang.module_news.bean;

import java.util.LinkedList;

public class QuizzesData {

    public LinkedList<QuizzesEntity> quizzes;

    public boolean hasQuizzes() {
        return quizzes != null && !quizzes.isEmpty();
    }

}
