/*
 * Copyright (C), 2008-2021, Paraview All Rights Reserved.
 */
package org.liujun.learn.spring.factory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对象创建的工厂
 *
 * @author liujun
 * @since 2021/7/9
 */
public class BeansFactory {


    /**
     * 加载的bean工厂的文件
     */
    private static final String LOAD_FILENAME = "beans.xml";


    /**
     * xpath加载数据
     */
    private static final String XPATH_NAME = "//Bean";

    /**
     * 找到属性的引用
     */
    private static final String XPATH_PROPERTY = "//property";

    /**
     * 配制中使用的key
     */
    private static final String ID = "id";


    /**
     * 文件名称
     */
    private static final String CLASS_NAME = "class";


    /**
     * 属性的名称
     */
    private static final String PROPERTY_NAME = "name";

    /**
     * 属性的引用
     */
    private static final String PROPERTY_REF = "ref";

    /**
     * 实体对象的工厂容器
     */
    private static final Map<String, Object> OBJECT_MAP = new HashMap<>();


    static {
        load();
    }

    /**
     * 对象化加载操作
     */
    private static void load() {
        // 解析xml
        SAXReader saxReader = new SAXReader();

        try (InputStream beanInput = BeansFactory.class.getClassLoader().getResourceAsStream(LOAD_FILENAME);
             BufferedInputStream bufferInput = new BufferedInputStream(beanInput);) {
            Document document = saxReader.read(bufferInput);
            Element rootElement = document.getRootElement();

            //执行元素的加载操作
            List<Element> elementList = rootElement.selectNodes(XPATH_NAME);
            for (Element item : elementList) {
                String key = item.attributeValue(ID);
                String value = item.attributeValue(CLASS_NAME);

                OBJECT_MAP.put(key, classToObject(value));
            }


            //对属性进行设置引用
            List<Element> propertyList = rootElement.selectNodes(XPATH_PROPERTY);
            for (Element propertyItem : propertyList) {
                String key = propertyItem.attributeValue(PROPERTY_NAME);
                String ref = propertyItem.attributeValue(PROPERTY_REF);

                Element parentObject = propertyItem.getParent();
                String baseParent = parentObject.attributeValue(ID);
                String classInfo = parentObject.attributeValue(CLASS_NAME);
                Object instance = OBJECT_MAP.get(baseParent);
                Object refInstance = OBJECT_MAP.get(ref);

                //执行的方法
                Method runMethod = findMethod(key, classInfo);
                //执行对象的设置操作
                if (null != runMethod) {
                    runMethod.invoke(instance, refInstance);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    private static Method findMethod(String methodName, String classInfo) throws ClassNotFoundException {
        Class classData = Class.forName(classInfo);
        Method[] methods = classData.getDeclaredMethods();

        String setName = "set" + methodName.substring(0, 1).toUpperCase() + methodName.substring(1);

        for (Method methodItem : methods) {
            if (methodItem.getName().equals(setName)) {
                return methodItem;
            }
        }

        return null;
    }


    /**
     * 执行对象的加载
     *
     * @param className 类名
     * @return 返回的加载对象
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private static Object classToObject(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class instanceObj = Class.forName(className);
        return instanceObj.newInstance();
    }


    /**
     * 获取实例对象信息
     *
     * @param key 获取数据的key
     * @return 获取的实体对象
     */
    public static <T> T getObject(String key) {
        return (T) OBJECT_MAP.get(key);
    }
}
