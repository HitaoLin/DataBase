package com.example.admin.database;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Admin on 2019/1/4.
 */

public class ButterKnife {
    public static void bind(Activity activity){
        try {
            bindview(activity);
            onClick(activity);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private static void onClick(final Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        Method[] dM = aClass.getDeclaredMethods();
        for (final Method method: dM){
            method.setAccessible(true);
            OnClick annotation = method.getAnnotation(OnClick.class);
            if (annotation != null){
                int id = annotation.value();
                View view = activity.findViewById(id);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            method.invoke(activity,null);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    private static void bindview(Activity activity) throws IllegalAccessException {
        Class<? extends Activity> aClass= activity.getClass();
        Field[] dF = aClass.getDeclaredFields();
        for (Field field :dF){
            field.setAccessible(true);
            BindView an = field.getAnnotation(BindView.class);
            if (an != null){
                int id = an.value();
                View view = activity.findViewById(id);
                field.set(activity,view);
            }
        }
    }
}
