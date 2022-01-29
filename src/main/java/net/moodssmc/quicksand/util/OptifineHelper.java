package net.moodssmc.quicksand.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class OptifineHelper
{
    public static boolean isShadersEnabled()
    {
        try
        {
            Class<?> aClass = Class.forName("net.optifine.Config");
            Method method = aClass.getMethod("isShaders");
            return (Boolean) method.invoke(null);
        }
        catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | IllegalAccessException ignored)
        {}
        return false;
    }

    public static void setFogNonStandard()
    {
        try
        {
            Class<?> aClass = Class.forName("net.minecraft.client.renderer.FogRenderer");
            //noinspection JavaReflectionMemberAccess
            Field field = aClass.getField("fogStandard");
            field.setAccessible(true);
            field.set(null, false);
        }
        catch (NoSuchFieldException | ClassNotFoundException | IllegalAccessException ignored)
        {}
    }

    public static void setShaderFogStart(float value)
    {
        try
        {
            Class<?> aClass = Class.forName("net.optifine.shaders.Shaders");
            Method method = aClass.getMethod("setFogStart", float.class);
            method.invoke(null, value);
        }
        catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | IllegalAccessException ignored)
        {}
    }

    public static void setShaderFogEnd(float value)
    {
        try
        {
            Class<?> aClass = Class.forName("net.optifine.shaders.Shaders");
            Method method = aClass.getMethod("setFogEnd", float.class);
            method.invoke(null, value);
        }
        catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | IllegalAccessException ignored)
        {}
    }

    public static void setShaderFogColor(float red, float green, float blue)
    {
        try
        {
            Class<?> aClass = Class.forName("net.optifine.shaders.Shaders");
            Method method = aClass.getMethod("setFogColor", float.class, float.class, float.class);
            method.invoke(null, red, green, blue);
        }
        catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | IllegalAccessException ignored)
        {}
    }
}
