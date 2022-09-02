package com.example.bmicalculatormvc;

public class ModelBMI {
    public static ModelService getService(String type){
        switch(type)
        {
            case("bmi"):
                return new ModelREST();

            default:
                return null;
        }
    }
}
