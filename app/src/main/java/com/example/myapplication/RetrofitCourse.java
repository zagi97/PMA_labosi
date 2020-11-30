package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitCourse
{
    @GET("/v1/courses")
    Call<CourseResponse> getCourses();
}

