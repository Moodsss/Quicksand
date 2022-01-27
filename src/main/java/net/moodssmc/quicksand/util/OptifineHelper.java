package net.moodssmc.quicksand.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class OptifineHelper
{
    private static final Logger LOGGER = LogManager.getLogger(OptifineHelper.class);

    public static boolean isShadersEnabled()
    {
        try
        {
            Class<?> aClass = Class.forName("net.optifine.Config");
            Method method = aClass.getMethod("isShaders");
            return (Boolean) method.invoke(null);
        }
        catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | IllegalAccessException ex)
        {
            LOGGER.error(ex);
        }
        return false;
    }

    public static void setFogNonStandard()
    {
        try
        {
            Class<?> aClass = Class.forName("net.minecraft.client.renderer.FogRenderer");
            Field field = aClass.getField("fogStandard");
            field.setAccessible(true);
            field.set(null, false);
        }
        catch (NoSuchFieldException | ClassNotFoundException | IllegalAccessException ex)
        {
             LOGGER.error(ex);
        }
    }

    public static void setShaderFogStart(float value)
    {
        try
        {
            Class<?> aClass = Class.forName("net.optifine.shaders.Shaders");
            Method method = aClass.getMethod("setFogStart", float.class);
            method.invoke(null, value);
        }
        catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | IllegalAccessException ex)
        {
            LOGGER.error(ex);
        }
    }

    public static void setShaderFogEnd(float value)
    {
        try
        {
            Class<?> aClass = Class.forName("net.optifine.shaders.Shaders");
            Method method = aClass.getMethod("setFogEnd", float.class);
            method.invoke(null, value);
        }
        catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | IllegalAccessException ex)
        {
            LOGGER.error(ex);
        }
    }

    public static void setShaderFogColor(float red, float green, float blue)
    {
        try
        {
            Class<?> aClass = Class.forName("net.optifine.shaders.Shaders");
            Method method = aClass.getMethod("setFogColor", float.class, float.class, float.class);
            method.invoke(null, red, green, blue);
        }
        catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | IllegalAccessException ex)
        {
            LOGGER.error(ex);
        }
    }
}
